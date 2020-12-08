/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.dao;

import com.myproject.livebloodmanagement.dto.BloodBankDTO;
import com.myproject.livebloodmanagement.dto.BloodCountDTO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author parth
 */
public class AdminDAO {
private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;

    private Session getSession() {
        if (session == null || !session.isOpen()) {
            session = sf.openSession();
        }
        return session;
    }

    private void beginTransaction() {
        getSession().beginTransaction();
    }

    private void commit() {
        getSession().getTransaction().commit();;
    }

    private void close() {
        getSession().close();
    }

    private void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }
    
    public BloodCountDTO viewBloodCountOfBBUsingBloodBankId(int bloodbankId){
        
        List<BloodCountDTO> bloodCountList=null;
        try {
            beginTransaction();
            Criteria cr = getSession().createCriteria(BloodCountDTO.class);
            cr.add(Restrictions.eq("bloodId",bloodbankId));
            
            bloodCountList = cr.list();
            commit();
            if(bloodCountList.isEmpty()){
                System.out.println("Not Updated any record");
                return null;
            }
            else{
                return bloodCountList.get(0);
            }
            
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return null;
    }
    
public int updateNewBloodCountForBB(BloodCountDTO bloodCount){
    
        int result = 0;
        try {
            //save to database
            beginTransaction();
            Query query = getSession().createQuery("update BloodCountDTO set bloodGroupAUnits =: AUnits, bloodGroupBUnits =: BUnits, bloodGroupOUnits =: OUnits,bloodGroupABUnits =: ABUnits where bloodId=:bankId");
            query.setInteger("AUnits", bloodCount.getBloodGroupAUnits());
            query.setInteger("BUnits", bloodCount.getBloodGroupBUnits());
            query.setInteger("OUnits",bloodCount.getBloodGroupOUnits());
            query.setInteger("ABUnits", bloodCount.getBloodGroupABUnits());
            query.setInteger("bankId",bloodCount.getBloodId());
            query.executeUpdate();
            commit();
            result = 1;
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return  result;
        
    }

public int approvalStatusUpdateForBB(int bloodBankId){
    
        int result = 0;
        try {
            //save to database
            beginTransaction();
            Query query = getSession().createQuery("update BloodBankDTO set bApproved=:status where bId=: bankId");
            query.setString("status","Yes");
            query.setInteger("bankId",bloodBankId);
            query.executeUpdate();
            commit();
            result = 1;
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return  result;
        
    }


public int insertNewBloodCountForBB(BloodCountDTO bloodCount){
    
        int result = 0;
        try {
            //save to database
            beginTransaction();
            getSession().save(bloodCount);
            commit();
            result = 1;
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
        
    }
     public BloodCountDTO viewBloodCountOfBBUsingCountId(int countId){
        
        List<BloodCountDTO> bloodCountList=null;
        try {
            beginTransaction();
            Criteria cr = session.createCriteria(BloodCountDTO.class);
            cr.add(Restrictions.eq("countId",countId));
            bloodCountList = cr.list();
            commit();
            if(bloodCountList.isEmpty()){
                System.out.println("Not Updated Any Record");
                return null;
            }
            else{
                return bloodCountList.get(0);
            }
            
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return null;
    }
     
     public void denyBBApprovalById(int bloodBankId){
        try {
            beginTransaction();
            Query q = getSession().createQuery("from BloodBankDTO where bId=:id");
            q.setInteger("id",bloodBankId);
            BloodBankDTO bbToDelete = (BloodBankDTO)q.uniqueResult();
            if(bbToDelete !=null){
            getSession().delete(bbToDelete);
            commit();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
    }
}
