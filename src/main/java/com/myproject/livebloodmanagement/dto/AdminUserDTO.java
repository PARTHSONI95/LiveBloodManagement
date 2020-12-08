/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author parth
 */
@Entity
@Table(name = "admin_user_details")
public class AdminUserDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    
    private String userName;
    
    private String userPassword;

    @JoinColumn(name = "bId")
    @OneToOne
    private BloodBankDTO bloodBank;

    public BloodBankDTO getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBankDTO bloodBank) {
        this.bloodBank = bloodBank;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
}
