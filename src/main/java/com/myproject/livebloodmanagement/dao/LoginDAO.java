/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.livebloodmanagement.dao;

import com.myproject.livebloodmanagement.dto.AdminUserDTO;
import com.myproject.livebloodmanagement.dto.BloodBankDTO;
import com.myproject.livebloodmanagement.dto.UserDTO;
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
public class LoginDAO {

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

    public List<BloodBankDTO> getListForApproval() {
        Query q = null;
        List<BloodBankDTO> bloodBankList = new ArrayList<BloodBankDTO>();

        try {
            beginTransaction();
            Criteria cr = session.createCriteria(BloodBankDTO.class);
            cr.add(Restrictions.eq("bApproved", "No"));
            bloodBankList = cr.list();
            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return bloodBankList;

    }

    public UserDTO checkIfUserIsAppUser(String userName, String password) {
        Query q = null;
        List<UserDTO> userList = new ArrayList<>();

        try {
            beginTransaction();
            q = getSession().createQuery("from UserDTO where userName =:username AND userPassword =:password");
            q.setString("username", userName);
            q.setString("password", password);
            userList = q.list();
            commit();
           
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        if(!userList.isEmpty())
            return userList.get(0);
        else
            return null;
    }

    public BloodBankDTO checkIfUserIsAdmin(String userName, String password) {


        Query q = null;
        List<AdminUserDTO> adminList = new ArrayList<>();

        try {
            beginTransaction();
            q = getSession().createQuery("from AdminUserDTO where userName =:username AND userPassword =:password");
            q.setString("username", userName);
            q.setString("password", password);
            adminList = q.list();
            commit();
            
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        if(!adminList.isEmpty())
            return adminList.get(0).getBloodBank();
        else
            return null;
    }

}
