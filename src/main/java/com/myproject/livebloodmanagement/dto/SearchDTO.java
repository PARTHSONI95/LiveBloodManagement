/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.dto;

/**
 *
 * @author parth
 */
public class SearchDTO {
    
    private BloodBankDTO bloodBank;
    
    private BloodCountDTO bloodCount;

    public BloodBankDTO getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBankDTO bloodBank) {
        this.bloodBank = bloodBank;
    }

    public BloodCountDTO getBloodCount() {
        return bloodCount;
    }

    public void setBloodCount(BloodCountDTO bloodCount) {
        this.bloodCount = bloodCount;
    }
    
   
    
}
