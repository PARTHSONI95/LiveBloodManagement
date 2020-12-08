/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.controller;

import com.myproject.livebloodmanagement.dao.RegisterDAO;
import com.myproject.livebloodmanagement.dto.AdminUserDTO;
import com.myproject.livebloodmanagement.dto.BloodBankDTO;
import com.myproject.livebloodmanagement.dto.UserDTO;
import com.myproject.livebloodmanagement.service.RegexUtil;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author parth
 */
@Controller
public class RegisterController {

    @RequestMapping(value = "/registerUser.htm", method = RequestMethod.POST)
    protected ModelAndView userRegistration(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("Register User Controller");

        String userName = request.getParameter("username");
        String passWord = request.getParameter("userpassword");
        String contactNo = request.getParameter("contact");

        if (userName == null || userName.isEmpty() || !RegexUtil.checkAlphaNumericSpecial(userName)) {
            request.setAttribute("errorMessage", "Problem with the username entered");
            return new ModelAndView("error");
        }
        if (passWord == null || passWord.isEmpty() || !RegexUtil.checkAlphaNumericSpecial(passWord)) {
            request.setAttribute("errorMessage", "Problem with the password entered");
            return new ModelAndView("error");
        }
        if (contactNo == null || contactNo.isEmpty() || !RegexUtil.checkPhoneNo(contactNo)) {
            request.setAttribute("errorMessage", "Problem with the user contact");
            return new ModelAndView("error");
        }

        UserDTO user = new UserDTO();
        user.setUserName(userName);
        user.setUserPassword(passWord);
        user.setUserContactInfo(contactNo);

        RegisterDAO registerDao = new RegisterDAO();
        registerDao.insertAppUserInDB(user);

        System.out.println("User Creation Successful");

        return new ModelAndView("registrationSuccessPage");
    }

    @RequestMapping(value = "/registerBloodBank.htm", method = RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("Register Controller");

        String hiddenField = request.getParameter("register");
        Double latitude = null, longitude = null;
        String completeAddress = "", city = null, county = null, state = null, postalCode = null;

        if (hiddenField != null) {
            System.out.println(hiddenField);
            System.out.println("Blood Bank address" + request.getParameter("bloodbankaddress"));

            int responseCode = 0;
            String api = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyDnAwL_NaAcFnNBnP2OaDJS_L8vdAVGSGw&address=" + URLEncoder.encode(request.getParameter("bloodbankaddress"), "UTF-8") + "&sensor=true";
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
                JSONObject jobj = (JSONObject) parse.parse(inline);

                JSONArray jsonarr_1 = (JSONArray) jobj.get("results");

                for (int i = 0; i < jsonarr_1.size(); i++) {
                    //Store the JSON objects in an array
                    //Get the index of the JSON object and print the values as per the index
                    JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);

                    JSONArray jsonAddressComponents = (JSONArray) jsonobj_1.get("address_components");
                    System.out.println("Inside address components...");
                    for (int j = 0; j < jsonAddressComponents.size(); j++) {
                        //System.out.println(jsonAddressComponents.get(j));
                        JSONObject jobjAddressComponents = (JSONObject) jsonAddressComponents.get(j);
                        JSONArray jsonTypes = (JSONArray) jobjAddressComponents.get("types");
                        if (jsonTypes.contains("postal_code")) {
                            postalCode = String.valueOf(jobjAddressComponents.get("long_name"));
                        }
                        if (jsonTypes.contains("administrative_area_level_1")) {
                            state = String.valueOf(jobjAddressComponents.get("short_name"));
                        }
                        if (jsonTypes.contains("locality")) {
                            city = String.valueOf(jobjAddressComponents.get("long_name"));
                        }
                        if (jsonTypes.contains("street_number")) {
                            completeAddress += jobjAddressComponents.get("long_name") + " ";
                        }
                        if (jsonTypes.contains("route")) {
                            completeAddress += jobjAddressComponents.get("long_name") + " ";
                        }
                        if (jsonTypes.contains("administrative_area_level_2")) {
                            county = String.valueOf(jobjAddressComponents.get("long_name"));
                        }
                    }
                    System.out.println("Complete Address" + completeAddress);
                    System.out.println("Elements under results array");
                    System.out.println("Types: " + jsonobj_1.get("types"));
                    JSONObject jsonarr_geo = (JSONObject) jsonobj_1.get("geometry");

                    JSONObject jsonarr_ge = (JSONObject) jsonarr_geo.get("location");
                    //System.out.println(jsonarr_geo.get("location"));
                    longitude = (double) jsonarr_ge.get("lng");
                    latitude = (double) jsonarr_ge.get("lat");
                }

                httpConnection.disconnect();
            }
        }
        String bname = null;
        BloodBankDTO bloodBankDetails = new BloodBankDTO();
        bname = request.getParameter("bloodbankname");
        String phoneNo = request.getParameter("contact");

        System.out.println("Value of lat" + latitude + "\t Value of lon" + longitude);
        System.out.println(" B name" + bname);
        if (latitude == null || longitude == null) {
            request.setAttribute("errorMessage", "Problem with the entered location");
            return new ModelAndView("error");
        }
        if (bname.isEmpty() || bname == null || !RegexUtil.checkMultiAlphaNumericSpecial(bname)) {
            request.setAttribute("errorMessage", "Enter valid blood bank name");
            return new ModelAndView("error");
        }
        System.out.println("Phone " + phoneNo);
        if (phoneNo.isEmpty() || phoneNo == null || !RegexUtil.checkPhoneNo(phoneNo)) {
            request.setAttribute("errorMessage", "Phone number not valid");
            return new ModelAndView("error");
        }
        System.out.println("City value -- " + city);

        String userName = request.getParameter("adminuser");
        String passWord = request.getParameter("adminpassword");

        if (userName == null || userName.isEmpty() || !RegexUtil.checkAlphaNumericSpecial(userName)) {
            request.setAttribute("errorMessage", "Username is Invalid!");
            return new ModelAndView("error");
        }
        if (passWord == null || passWord.isEmpty() || !RegexUtil.checkAlphaNumericSpecial(passWord)) {
            request.setAttribute("errorMessage", "Password is Invalid!");
            return new ModelAndView("error");
        }

        bloodBankDetails.setbName(bname);
        bloodBankDetails.setbAddress(completeAddress);
        bloodBankDetails.setbCity(city);
        bloodBankDetails.setbCounty(county);
        bloodBankDetails.setbState(state);
        bloodBankDetails.setbLat(latitude);
        bloodBankDetails.setbLong(longitude);
        bloodBankDetails.setbPostalCode(postalCode);
        bloodBankDetails.setbPhoneNo(phoneNo);
        bloodBankDetails.setbApproved("No");

        AdminUserDTO adminUser = new AdminUserDTO();
        adminUser.setUserName(userName);
        adminUser.setUserPassword(passWord);
        adminUser.setBloodBank(bloodBankDetails);

        RegisterDAO registerBloodBank = new RegisterDAO();
        registerBloodBank.insertBloodBankInDB(bloodBankDetails);
        registerBloodBank.insertBloodBankAdminUserInDB(adminUser);

        return new ModelAndView("registrationSuccessPage");

    }
}
