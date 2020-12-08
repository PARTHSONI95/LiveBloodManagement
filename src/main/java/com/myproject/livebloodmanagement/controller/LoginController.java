/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.controller;

import com.myproject.livebloodmanagement.dao.AdminDAO;
import com.myproject.livebloodmanagement.dao.LoginDAO;
import com.myproject.livebloodmanagement.dto.BloodBankDTO;
import com.myproject.livebloodmanagement.dto.UserDTO;
import java.util.List;
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
public class LoginController {

    public LoginController() {
    }

    @RequestMapping(value = "/loginCheck.htm", method = RequestMethod.GET)
    protected ModelAndView afterRootApproval(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        if(request.getSession().getAttribute("role").equals("root")){
            LoginDAO loginDao = new LoginDAO();
            List<BloodBankDTO> list = loginDao.getListForApproval();
            request.setAttribute("bloodBankList", list);
            return new ModelAndView("root");
        }else
            return new ModelAndView("error");
        
    }
    
    
    @RequestMapping(value = "/loginCheck.htm", method = RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("In Login Controller");

        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");

        HttpSession session = request.getSession();

        // Root Level
        LoginDAO loginDao = new LoginDAO();
        if (userName.equals("root") && passWord.equals("root")) {

            System.out.println("Root Level Access");

            session.setAttribute("user", userName);
            session.setAttribute("role", "root");
            List<BloodBankDTO> list = loginDao.getListForApproval();
            request.setAttribute("bloodBankList", list);

            return new ModelAndView("root");
        }

        BloodBankDTO bloodBankConAdmin = loginDao.checkIfUserIsAdmin(userName, passWord);
        System.out.println("Login Admin : " + bloodBankConAdmin);
        //System.out.println(" ---- " + bloodBankConAdmin.getbAddress());
        if (bloodBankConAdmin != null) {
            session.setAttribute("user", userName);
            session.setAttribute("role", "admin");
            session.setAttribute("bloodBankAdminUserLogin", bloodBankConAdmin);

            System.out.println("Open Requested blood bank and can update blood count");

            return new ModelAndView("adminBBPage");
        }

        UserDTO userAppDetails = loginDao.checkIfUserIsAppUser(userName, passWord);

        if (userAppDetails != null) {

            System.out.println("App User Login");
            session.setAttribute("user", userName);

            return new ModelAndView("search");
        }
        
        request.setAttribute("errorMessage","Invalid Login Credentials");
        return new ModelAndView("error");
    }

    @RequestMapping(value = "/approved.htm", method = RequestMethod.POST)
    protected ModelAndView provideApprovalRights(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String appdeny = request.getParameter("appdeny");
        
        if(appdeny == null || appdeny.isEmpty()){
            request.setAttribute("errorMessage", "No value is selected");
            return new ModelAndView("error");
        }
        Integer bloodbankId = Integer.parseInt(appdeny);
        String action = request.getParameter("action");
        
        
        
        System.out.println(" Blood Bank ID to be Approved " + bloodbankId);
        
        AdminDAO adminDao = new AdminDAO();
        
        if(action.equals("approve")){
        adminDao.approvalStatusUpdateForBB(bloodbankId);
        System.out.println("Approved Success");
        }else{
            
            adminDao.denyBBApprovalById(bloodbankId);
        }
        
        return new ModelAndView("rootApproveSuccessPage");
    }

    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    protected ModelAndView loggingOut(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("In Logout Function");
        HttpSession session = request.getSession(false);
        System.out.println(" User ---" + session.getAttribute("user"));
        session.removeAttribute("user");
        System.out.println("After Logout -  User --- " + session.getAttribute("user"));
        session.invalidate();
        return new ModelAndView("logOutSuccess");
    }
}
