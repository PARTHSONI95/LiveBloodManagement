/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.controller;

import com.myproject.livebloodmanagement.dao.SearchDAO;
import com.myproject.livebloodmanagement.dto.BloodBankDTO;
import com.myproject.livebloodmanagement.dto.BloodCountDTO;
import com.myproject.livebloodmanagement.dto.SearchDTO;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author parth
 */
@Controller
public class SearchController {

    public SearchController() {
    }
    
    @RequestMapping(value = "/livebloodcount.htm", method = RequestMethod.GET, params = {"bName"})
    public ModelAndView liveBloodDetailSearch(@RequestParam(value = "bName") String bloodBankName, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    
        System.out.println("In live Blood details function");
        System.out.println("" + bloodBankName);
        return null;
    }
    
    
    @RequestMapping(value = "/searchBloodBank.htm", method = RequestMethod.POST)
    protected ModelAndView handleRequestInternal1(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("Search Controller");
        
        double latitude = 0.0;
        double longitude = 0.0;
        String searchField = request.getParameter("searchfield");
        String state=null;
        int responseCode = 0;
        String api = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyDnAwL_NaAcFnNBnP2OaDJS_L8vdAVGSGw&address=" + URLEncoder.encode(searchField, "UTF-8") + "&sensor=true";
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.connect();
        
        responseCode = httpConnection.getResponseCode();
        if (responseCode == 200) {
            String inline = "";
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();

            }
         
       JSONParser parse = new JSONParser();
       JSONObject jobj = (JSONObject)parse.parse(inline); 
       
                       JSONArray jsonarr_1 = (JSONArray) jobj.get("results");

                for (int i = 0; i < jsonarr_1.size(); i++) {
                    //Store the JSON objects in an array
                    //Get the index of the JSON object and print the values as per the index
                    JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
                    
                    JSONArray jsonAddressComponents = (JSONArray) jsonobj_1.get("address_components");
                    
                    System.out.println("Inside address components...");
                    for(int j=0; j< jsonAddressComponents.size(); j++){
                        //System.out.println(jsonAddressComponents.get(j));
                        JSONObject jobjAddressComponents = (JSONObject) jsonAddressComponents.get(j);
                        JSONArray jsonTypes = (JSONArray) jobjAddressComponents.get("types");
                        if(jsonTypes.contains("administrative_area_level_1")){
                         state =  String.valueOf(jobjAddressComponents.get("short_name"));
                        }
                        }                        
                    
                    System.out.println("Types: " + jsonobj_1.get("types"));
                    JSONObject jsonarr_geo = (JSONObject) jsonobj_1.get("geometry");
                    JSONObject jsonarr_ge = (JSONObject) jsonarr_geo.get("location");
                    //System.out.println(jsonarr_geo.get("location"));
                    longitude =  (double)jsonarr_ge.get("lng");
                    latitude = (double)jsonarr_ge.get("lat");
                }
       
            System.out.println("State needed to be search to decrease complexity");
            System.out.println(state);
            
            SearchDAO searchDao = new SearchDAO();
            List<BloodBankDTO> bloodBankList = searchDao.selectBloodBankBasedOnStateLocation(state);
            int count = 0;
            
            //Store search results
            Map<Double,SearchDTO> searchResult = new TreeMap<Double,SearchDTO>();
            List<Double> latitudeList = new ArrayList<Double>();
            List<Double> longitudeList = new ArrayList<Double>();
            
            
            
            BloodCountDTO bankCount;
            
       
            for(BloodBankDTO b : bloodBankList){
                
                double dist = distance(latitude, longitude,b.getbLat() ,b.getbLong());
                if(dist < 10){
                    count++;
                    SearchDTO search = new SearchDTO();
                    search.setBloodBank(b);
                    bankCount = searchDao.viewBloodCountOfBBUsingBloodBankIdForUser(b.getbId());
                    //bankCount = new BloodCountDTO();
                    if(bankCount == null){
                        bankCount = new BloodCountDTO();
                        bankCount.setBloodGroupAUnits(100);
                        bankCount.setBloodGroupBUnits(1000);
                        bankCount.setBloodGroupOUnits(1000);
                        bankCount.setBloodGroupABUnits(800);
                    }
                    search.setBloodCount(bankCount);
                    searchResult.put(dist,search);
                    latitudeList.add(b.getbLat());
                    longitudeList.add(b.getbLong());
                }
            }
            request.setAttribute("searchResults", searchResult);
            request.setAttribute("latitude",latitudeList);
            request.setAttribute("longitude", longitudeList);
            System.out.println("Counter ..." + count);
        } 
            
            // Finding nearest blood bank
//            if (distance(lat1, lng1, lat2, lng2) < 0.1) { // if distance < 0.1 miles we take locations as equal
//                //do what you want to do...
//            }

            return new ModelAndView("bloodBankSearch");
        }
    
        private double distance(double lat1, double lng1, double lat2, double lng2) {

            double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

            double dLat = Math.toRadians(lat2-lat1);
            double dLng = Math.toRadians(lng2-lng1);

            double sindLat = Math.sin(dLat / 2);
            double sindLng = Math.sin(dLng / 2);

            double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            double dist = earthRadius * c;
            System.out.println("Distance : " + dist);
            return dist; // output distance, in MILES
        }
        
        
    @RequestMapping(value = "/getLiveCount.htm", method = RequestMethod.GET)
    protected ModelAndView getLiveBloodCount(@RequestParam(value = "b_Id") int b_id,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        System.out.println("Getting BloodBank Details");
        
        SearchDAO searchDao = new SearchDAO();
        BloodBankDTO bank = searchDao.selectBloodBankBasedOnId(b_id);
        
        BloodCountDTO bankCount = searchDao.viewBloodCountOfBBUsingBloodBankIdForUser(b_id);
       
        System.out.println("count-" + bankCount.getBloodGroupAUnits() + ","+ bankCount.getBloodGroupBUnits());
        
        
        request.setAttribute("count",bankCount);
        
        return new ModelAndView("searchCount");
        
    
    }

    }
