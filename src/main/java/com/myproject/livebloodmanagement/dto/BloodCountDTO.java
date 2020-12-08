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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author parth
 */

@Entity
@Table(name = "blood_availableDetails")
public class BloodCountDTO{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int countId;
    
    @Column(name ="blood_Id")
    private int bloodId;
    
    @Column(name = "A_Units")
    private int bloodGroupAUnits;
    
    @Column(name = "B_Units")
    private int bloodGroupBUnits;
    
    @Column(name = "O_Units")
    private int bloodGroupOUnits;
    
    @Column(name = "AB_Units")
    private int bloodGroupABUnits;

    @JoinColumn(name = "bId")
    @OneToOne
    private BloodBankDTO bloodBankForCount;

    public BloodCountDTO() {
    }

    public int getCountId() {
        return countId;
    }

    public void setCountId(int countId) {
        this.countId = countId;
    }

    public int getBloodId() {
        return bloodId;
    }

    public void setBloodId(int bloodId) {
        this.bloodId = bloodId;
    }
    
    
    
    public int getBloodGroupAUnits() {
        return bloodGroupAUnits;
    }

    public void setBloodGroupAUnits(int bloodGroupAUnits) {
        this.bloodGroupAUnits = bloodGroupAUnits;
    }

    public int getBloodGroupBUnits() {
        return bloodGroupBUnits;
    }

    public void setBloodGroupBUnits(int bloodGroupBUnits) {
        this.bloodGroupBUnits = bloodGroupBUnits;
    }

    public int getBloodGroupOUnits() {
        return bloodGroupOUnits;
    }

    public void setBloodGroupOUnits(int bloodGroupOUnits) {
        this.bloodGroupOUnits = bloodGroupOUnits;
    }

    public int getBloodGroupABUnits() {
        return bloodGroupABUnits;
    }

    public void setBloodGroupABUnits(int bloodGroupABUnits) {
        this.bloodGroupABUnits = bloodGroupABUnits;
    }

    public BloodBankDTO getBloodBankForCount() {
        return bloodBankForCount;
    }

    public void setBloodBankForCount(BloodBankDTO bloodBankForCount) {
        this.bloodBankForCount = bloodBankForCount;
    }

    
    
}
