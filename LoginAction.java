package com.Talent4Assure.WeBlogger.actions;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.Talent4Assure.WeBlogger.beans.User;
import com.Talent4Assure.WeBlogger.forms.User_Login;
import com.Talent4Assure.WeBlogger.services.UserService;
import com.Talent4Assure.WeBlogger.services.Impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class LoginAction extends ActionSupport implements SessionAware,ModelDriven<User_Login>{
	
	
	private static final long serialVersionUID = 1L;

	User_Login user_Login=new User_Login();
	
	UserService userService=new UserServiceImpl();
	

	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/weblogger/configuration.properties");
	 static Properties prop = new Properties();
	    
	
public String login() throws Exception 
{
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	System.out.println(user_Login.getEmail());
	System.out.println(user_Login.getPassword());
	
	try{
    User user =userService.login(user_Login.getEmail(),user_Login.getPassword());
	if(user!=null){
		  request.getSession().setAttribute("USER", user);
		/*  HttpSession session = request.getSession();
		  session.invalidate();*/
		
	}
	if(user==null)
	{
		return "ERROR";
	}
	}catch (Exception e){
		System.out.println(e);
	}
	return "SUCCESS";
}

public String logout() throws Exception 
{
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpSession session=request.getSession();
	session.invalidate();
	return "SUCCESS";
}


@Override
public void setSession(Map<String, Object> arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public User_Login getModel() {
	// TODO Auto-generated method stub
	return user_Login;
}
		

}
