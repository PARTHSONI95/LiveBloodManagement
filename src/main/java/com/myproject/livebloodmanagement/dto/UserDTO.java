/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author parth
 */
@Entity
@Table(name = "application_user_details")
public class UserDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    
    @Column(name="user_name")
    private String userName;
    
    @Column(name="user_password")
    private String userPassword;
    
    @Column(name="user_contact")
    private String userContactInfo;

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

    public String getUserContactInfo() {
        return userContactInfo;
    }

    public void setUserContactInfo(String userContactInfo) {
        this.userContactInfo = userContactInfo;
    }
    
    
    
    
}
