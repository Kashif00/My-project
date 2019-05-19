package com.Talent4Assure.WeBlogger.dao.Impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Talent4Assure.WeBlogger.beans.User;
import com.Talent4Assure.WeBlogger.dao.BaseDao;
import com.Talent4Assure.WeBlogger.dao.UserDao;
import com.Talent4Assure.WeBlogger.exceptions.BusinessException;

public class UserDaoImpl extends BaseDao implements UserDao 
{

	@Override
	public User login(String email, String password) {
	
		User user = null;
		Transaction tx = null;
		Session session=null;
		try
		{
			session = getSession();
			tx = ((Session) session).beginTransaction();
			System.out.println("Email Id : "+email+"paswd"+ password);
			
			Query query = session.createQuery("from User where user_email_id = :emailId and user_password =:password");
			query.setParameter("emailId", email);
			query.setParameter("password", password);
			user = (User) query.uniqueResult();
			if(user==null){
				System.out.println("User is null");
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new BusinessException("Authentication failed", ex);
		} finally
		{
			tx.commit();
			releaseSession(session);
		}
		return user;
	}

	
}
