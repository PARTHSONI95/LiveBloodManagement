/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author parth
 */
public class RegexUtil {
    
    public static boolean checkAlpha(String check){
    
        String reg = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(reg);
        
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean checkMultiAlpha(String check){
    
        String reg = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(reg);
        
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
     public static boolean checkMultiAlphaNumeric(String check){
    
        String reg = "^[a-zA-Z0-9 ]+$";
        Pattern pattern = Pattern.compile(reg);
        
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean checkNumeric(String check){
    
        String reg = "^[0-9]+$";
        Pattern pattern = Pattern.compile(reg);
        
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean checkAlphaNumeric(String check){
    
        String reg = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(reg);
        
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean checkAlphaNumericSpecial(String check){
    
        String reg = "^[a-zA-Z0-9!@#$%&*]+$";
        Pattern pattern = Pattern.compile(reg);
        
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean checkMultiAlphaNumericSpecial(String check){
    
        String reg = "^[a-zA-Z0-9!@#$%&* ]+$";
        Pattern pattern = Pattern.compile(reg);
        
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
    
    public static boolean checkEmailId(String check){
    
        String reg = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(reg);
        
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean checkPhoneNo(String check){
    
        String reg = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern pattern = Pattern.compile(reg);
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            System.out.println("Phone match");
            return true;
        }else
        {
            System.out.println("No phone match");
        }
        
        return false;
    }
    
    public static boolean checkPostalCode(String check){
    
        String reg = "^\\d{5}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher mat = pattern.matcher(check);
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
}
