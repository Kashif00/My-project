package com.Talent4Assure.WeBlogger.services;

import java.util.List;

import com.Talent4Assure.WeBlogger.beans.User;

public interface UserService {


	User login(String email, String password) throws Exception;

	

}
