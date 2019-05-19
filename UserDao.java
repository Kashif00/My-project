package com.Talent4Assure.WeBlogger.dao;

import com.Talent4Assure.WeBlogger.beans.User;

public interface UserDao {

	User login(String email, String password);
	

}
