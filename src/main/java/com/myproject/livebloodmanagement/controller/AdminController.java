/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.controller;

import com.myproject.livebloodmanagement.dao.AdminDAO;
import com.myproject.livebloodmanagement.dto.BloodBankDTO;
import com.myproject.livebloodmanagement.dto.BloodCountDTO;
import com.myproject.livebloodmanagement.service.RegexUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author parth
 */
@Controller
public class AdminController {

    public AdminController() {
    }

    @RequestMapping(value = "/backToAdmin.htm", method = RequestMethod.GET)
    protected ModelAndView adminPage(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        return new ModelAndView("adminBBPage");
        
    }
    @RequestMapping(value = "/viewBloodCount.htm", method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        if (request.getSession().getAttribute("role").equals("admin")) {
            System.out.println("Person is having Admin Access");

            //Check for blood count values
            AdminDAO adminDao = new AdminDAO();
            BloodBankDTO bloodBankOfAdmin = (BloodBankDTO) request.getSession().getAttribute("bloodBankAdminUserLogin");
            BloodCountDTO viewCount = adminDao.viewBloodCountOfBBUsingBloodBankId(bloodBankOfAdmin.getbId());

            if (viewCount == null) {
                System.out.println("No Count record found");
                System.out.println("Going to create new...");
                return new ModelAndView("createNewBBCount");
            } else {
                request.setAttribute("BBCount", viewCount);
            }

            return new ModelAndView("viewBBCount");

        }

        return new ModelAndView("error");
    }

    @RequestMapping(value = "/createBloodCount.htm", method = RequestMethod.POST)
    protected ModelAndView createBloodCount(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        BloodBankDTO bloodBank = (BloodBankDTO) request.getSession().getAttribute("bloodBankAdminUserLogin");

        String aCount = request.getParameter("ACount");
        String bCount = request.getParameter("BCount");
        String oCount = request.getParameter("OCount");
        String abCount = request.getParameter("ABCount");

        if (aCount == null || aCount.isEmpty() || !RegexUtil.checkNumeric(aCount)) {
            request.setAttribute("errorMessage", "A Blood Count is Invalid!");
            return new ModelAndView("error");
        }
        if (bCount == null || bCount.isEmpty() || !RegexUtil.checkNumeric(bCount)) {
            request.setAttribute("errorMessage", "B Blood Count is Invalid!");
            return new ModelAndView("error");
        }
        if (oCount == null || oCount.isEmpty() || !RegexUtil.checkNumeric(oCount)) {
            request.setAttribute("errorMessage", "O Blood Count is Invalid!");
            return new ModelAndView("error");
        }
        if (abCount == null || abCount.isEmpty() || !RegexUtil.checkNumeric(abCount)) {
            request.setAttribute("errorMessage", "AB Blood Count is Invalid!");
            return new ModelAndView("error");
        }
        
        int ABloodCount = Integer.parseInt(aCount);
        int BBloodCount = Integer.parseInt(bCount);
        int OBloodCount = Integer.parseInt(oCount);
        int ABBloodCount = Integer.parseInt(abCount);

        BloodCountDTO bloodCountCreate = new BloodCountDTO();
        bloodCountCreate.setBloodGroupAUnits(ABloodCount);
        bloodCountCreate.setBloodGroupBUnits(BBloodCount);
        bloodCountCreate.setBloodGroupOUnits(OBloodCount);
        bloodCountCreate.setBloodGroupABUnits(ABBloodCount);
        bloodCountCreate.setBloodId(bloodBank.getbId());
        bloodCountCreate.setBloodBankForCount(bloodBank);

        AdminDAO adminDao = new AdminDAO();

        System.out.println(" Result - " + adminDao.insertNewBloodCountForBB(bloodCountCreate));
        System.out.println("Blood Count has been updated!!");

        return new ModelAndView("adminBBPage");
    }

    @RequestMapping(value = "/updateNewBloodCountdetails.htm", method = RequestMethod.POST)
    protected ModelAndView updateBloodCountMethod(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //Fetch blood count details for that particular countId
        AdminDAO adminDAO = new AdminDAO();

        int bloodBankId = Integer.parseInt(request.getParameter("updateBBC"));
        BloodCountDTO bloodCountToBeUpdated = adminDAO.viewBloodCountOfBBUsingBloodBankId(bloodBankId);
        if (bloodCountToBeUpdated == null) {
            return new ModelAndView("error");
        }
        request.getSession().setAttribute("bloodBankAdminUserLogin", bloodCountToBeUpdated.getBloodBankForCount());

        return new ModelAndView("updateBloodCount", "bloodCountToBeUpdated", bloodCountToBeUpdated);
    }

    @RequestMapping(value = "/confirmToUpdate.htm", method = RequestMethod.POST)
    protected ModelAndView toBeUpdatedBloodCount(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // Update the set details in input form
        System.out.println("In to be Update");
        BloodBankDTO bloodBank = (BloodBankDTO) request.getSession().getAttribute("bloodBankAdminUserLogin");

        String aCount = request.getParameter("ACount");
        String bCount = request.getParameter("BCount");
        String oCount = request.getParameter("OCount");
        String abCount = request.getParameter("ABCount");

        if (aCount == null || aCount.isEmpty() || !RegexUtil.checkNumeric(aCount)) {
            request.setAttribute("errorMessage", "A Blood Count is Invalid!");
            return new ModelAndView("error");
        }
        if (bCount == null || bCount.isEmpty() || !RegexUtil.checkNumeric(bCount)) {
            request.setAttribute("errorMessage", "B Blood Count is Invalid!");
            return new ModelAndView("error");
        }
        if (oCount == null || oCount.isEmpty() || !RegexUtil.checkNumeric(oCount)) {
            request.setAttribute("errorMessage", "O Blood Count is Invalid!");
            return new ModelAndView("error");
        }
        if (abCount == null || abCount.isEmpty() || !RegexUtil.checkNumeric(abCount)) {
            request.setAttribute("errorMessage", "AB Blood Count is Invalid!");
            return new ModelAndView("error");
        }

        int ABloodCount = Integer.parseInt(aCount);
        int BBloodCount = Integer.parseInt(bCount);
        int OBloodCount = Integer.parseInt(oCount);
        int ABBloodCount = Integer.parseInt(abCount);

        BloodCountDTO bloodCountUpdate = new BloodCountDTO();
        bloodCountUpdate.setBloodGroupAUnits(ABloodCount);
        bloodCountUpdate.setBloodGroupBUnits(BBloodCount);
        bloodCountUpdate.setBloodGroupOUnits(OBloodCount);
        bloodCountUpdate.setBloodGroupABUnits(ABBloodCount);
        bloodCountUpdate.setBloodBankForCount(bloodBank);
        bloodCountUpdate.setBloodId(bloodBank.getbId());

        AdminDAO adminDao = new AdminDAO();
        adminDao.updateNewBloodCountForBB(bloodCountUpdate);

        HttpSession session = request.getSession();
        session.setAttribute("bloodCountUpdate", bloodCountUpdate);
        //request.getSession().setAttribute("bloodCountUpdate", bloodCountUpdate);
        System.out.println("Old Blood Count has been given to update!!");

        return new ModelAndView("updateSuccessful");

    }

}
