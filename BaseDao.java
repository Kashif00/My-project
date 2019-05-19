package com.Talent4Assure.WeBlogger.dao;


import java.io.InputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class BaseDao {
	
	static InputStream inputStream = null;
	static Properties prop = new Properties();
	
	private static SessionFactory factory;
	
	static{
		factory=initSessionFactory("/com/hibernate.cfg.xml");
		
	}
	
	public static SessionFactory initSessionFactory(String filePath) {
		SessionFactory sessionFactory=null;
		try {
			Configuration configuration = new Configuration();
			
			configuration=configuration.configure("/com/hibernate.cfg.xml");
			 inputStream = BaseDao.class.getClassLoader().getResourceAsStream("/weblogger/configuration.properties");
			prop.load(inputStream);
			
			String jdbcDriver=prop.getProperty("hibernate.jdbc.driver");
			String host=prop.getProperty("hibernate.connection.host");
			String port=prop.getProperty("hibernate.connection.port");
			String db=prop.getProperty("hibernate.connection.db");
			String maxPoolSize=prop.getProperty("hibernate.maxpool.size");
			String minPoolSize=prop.getProperty("hibernate.minpool.size");
			String timeOut = prop.getProperty("hibernate.timeout");
			String maxStatement = prop.getProperty("hibernate.max.statements");
			String dbUser=prop.getProperty("hibernate.connection.username");
			String dbPassword=prop.getProperty("hibernate.connection.password");
			
			String connectionString="jdbc:mysql://"+host+":"+port+"/"+db+"?useUnicode=true&amp;characterEncoding=UTF-8";	
			
			
			configuration.setProperty("hibernate.connection.driver_class", jdbcDriver);
			configuration.setProperty("hibernate.connection.url", connectionString);
			configuration.setProperty("hibernate.c3p0.max_size", maxPoolSize);
			configuration.setProperty("hibernate.c3p0.min_size", minPoolSize);
			configuration.setProperty("hibernate.c3p0.timeout", timeOut);//
			configuration.setProperty("hibernate.c3p0.max_statements", maxStatement);
			configuration.setProperty("hibernate.connection.username", dbUser);
			configuration.setProperty("hibernate.connection.password", dbPassword);
			configuration.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
			
			sessionFactory=configuration.buildSessionFactory();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		
		return sessionFactory;
	}
	protected void releaseSession(Session session) {
		try {
			
			if(session!=null)
			{
				if(session.isOpen()){
					synchronized (session) {
						session.flush();
						session.close();	
					}
				}
			}
		} catch (Exception ex) {
		}
	}
	
	protected Session getSession()
	{
		return factory.openSession();
	}
}
