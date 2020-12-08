/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.dao;

import com.myproject.livebloodmanagement.dto.AdminUserDTO;
import com.myproject.livebloodmanagement.dto.BloodBankDTO;
import com.myproject.livebloodmanagement.dto.UserDTO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author parth
 */
public class RegisterDAO {
    
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
    
    public int insertBloodBankInDB(BloodBankDTO bloodbank){
    
        int result = 0;
        try {
            //save to database
            beginTransaction();
            getSession().save(bloodbank);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } finally {
            close();
        }
        return  result;
        
    }

    public int insertAppUserInDB(UserDTO user){
    
        int result = 0;
        try {
            //save to database
            beginTransaction();
            getSession().save(user);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return  result;
        
    }
    
    
public int insertBloodBankAdminUserInDB(AdminUserDTO adminUser){
    
        int result = 0;
        try {
            //save to database
            beginTransaction();
            getSession().save(adminUser);
            commit();
            result = 1;
        } catch (HibernateException e) {
            rollbackTransaction();
            throw e;
        } finally {
            close();
        }
        return  result;
        
    }
    
}
