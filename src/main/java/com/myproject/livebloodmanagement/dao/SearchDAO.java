/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.dao;

import com.myproject.livebloodmanagement.dto.BloodBankDTO;
import com.myproject.livebloodmanagement.dto.BloodCountDTO;
import java.util.ArrayList;
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
public class SearchDAO {

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

    public List<BloodBankDTO> selectBloodBankBasedOnStateLocation(String state) {
        Query q = null;
        List<BloodBankDTO> bloodBankList = new ArrayList<BloodBankDTO>();

        try {
            beginTransaction();
            q = getSession().createQuery("from BloodBankDTO where bState = :state and bApproved =:status");
            q.setParameter("state", state);
            q.setParameter("status","Yes");
            //q.setString("status","Yes");
            bloodBankList = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return bloodBankList;
    }
    
        public BloodBankDTO selectBloodBankBasedOnId(int bloodbankId) {
        Query q = null;
        List<BloodBankDTO> bloodBankList = new ArrayList<BloodBankDTO>();
        try {
            beginTransaction();
            q = getSession().createQuery("from BloodBankDTO where bId = : bloodID");
            q.setInteger("bloodID",bloodbankId);
            bloodBankList = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        if(!bloodBankList.isEmpty())
            return bloodBankList.get(0);
        else
            return null;
    }
        
        public BloodCountDTO viewBloodCountOfBBUsingBloodBankIdForUser(int bloodbankId){
        
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
}
