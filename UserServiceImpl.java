package com.Talent4Assure.WeBlogger.services.Impl;

import com.Talent4Assure.WeBlogger.beans.User;
import com.Talent4Assure.WeBlogger.services.*;
import com.Talent4Assure.WeBlogger.dao.*;
import com.Talent4Assure.WeBlogger.dao.Impl.*;

	

public class UserServiceImpl implements UserService {

	UserDao userDao=new UserDaoImpl();
	
	@Override
	public User login(String email, String password) throws Exception
	{
		return userDao.login(email,password);
	}

	
}
