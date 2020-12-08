/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author parth
 */

@Entity
@Table(name="enterprisedb.blood_bank_details")
public class BloodBankDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private int bId;
        
    @Column(name = "b_name")
    private String bName;
    @Column(name = "b_address")
    private String bAddress;
    @Column(name = "b_city")
    private String bCity;
    @Column(name = "b_countyname")
    private String bCounty;
    @Column(name = "b_state")
    private String bState;
    @Column(name = "b_postalcode")
    private String bPostalCode;
    @Column(name = "b_phone_no")    
    private String bPhoneNo;
    @Column(name = "b_latitude")
    private double bLat;
    @Column(name = "b_longitude")
    private double bLong;
    @Column(name = "b_approved")
    private String bApproved;

    @OneToOne(mappedBy = "bloodBank",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private AdminUserDTO adminUser;
    
    @OneToOne(mappedBy = "bloodBankForCount")
    private BloodCountDTO bloodDto;
    
    
    public BloodBankDTO() {
    }
    
    public int getbId() {
        return bId;
    }
    
    public String getbPhoneNo() {
        return bPhoneNo;
    }

    public void setbPhoneNo(String bPhoneNo) {
        this.bPhoneNo = bPhoneNo;
    }
    
    

    public String getbState() {
        return bState;
    }

    public void setbState(String bState) {
        this.bState = bState;
    }

    
    
    public String getbApproved() {
        return bApproved;
    }

    public void setbApproved(String bApproved) {
        this.bApproved = bApproved;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbAddress() {
        return bAddress;
    }

    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
    }

    public String getbCity() {
        return bCity;
    }

    public void setbCity(String bCity) {
        this.bCity = bCity;
    }

    public String getbCounty() {
        return bCounty;
    }

    public void setbCounty(String bCounty) {
        this.bCounty = bCounty;
    }

    public String getbPostalCode() {
        return bPostalCode;
    }

    public void setbPostalCode(String bPostalCode) {
        this.bPostalCode = bPostalCode;
    }

    public double getbLat() {
        return bLat;
    }

    public void setbLat(double bLat) {
        this.bLat = bLat;
    }

    public double getbLong() {
        return bLong;
    }

    public void setbLong(double bLong) {
        this.bLong = bLong;
    }

    
}
