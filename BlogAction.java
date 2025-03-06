package com.Talent4Assure.WeBlogger.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.catalina.connector.Request;
import org.apache.http.protocol.HTTP;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import org.hibernate.HibernateException;

import com.Talent4Assure.WeBlogger.beans.Blog;
import com.Talent4Assure.WeBlogger.beans.Comments;
import com.Talent4Assure.WeBlogger.beans.User;

import com.Talent4Assure.WeBlogger.forms.Blog_Form;
import com.Talent4Assure.WeBlogger.forms.User_Login;
import com.Talent4Assure.WeBlogger.services.BlogService;
import com.Talent4Assure.WeBlogger.services.UserService;
import com.Talent4Assure.WeBlogger.services.Impl.BlogServiceImpl;
import com.Talent4Assure.WeBlogger.services.Impl.UserServiceImpl;
import com.Talent4Assure.WeBlogger.utils.CommonUtils;

import com.itextpdf.text.log.SysoCounter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class BlogAction extends ActionSupport implements SessionAware,ModelDriven<Blog_Form> {
	 
	
	
	private static final long serialVersionUID = 1L;
	Blog_Form blog_Form = new Blog_Form();
	BlogService blogService=new BlogServiceImpl();
	private String blog_title;
	private String blog_text;
	private String blog_created_by;
	public String getBlog_created_by() {
		
		return blog_created_by;
	}

	public void setBlog_created_by(String blog_created_by) {
		this.blog_created_by = blog_created_by;
	}

	private String comment;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBlog_title() {
		return blog_title;
	}

	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}

	public String getBlog_text() {
		return blog_text;
	}

	public void setBlog_text(String blog_text) {
		this.blog_text = blog_text;
	}

	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/weblogger/configuration.properties");
	 static Properties prop = new Properties();
	private ServletRequest session;

	public String blogAction() throws Exception 
	{
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		/* byte[] bFile = new byte[(int) filePart.getSize()];
		 InputStream fileContent = filePart.getInputStream();*/
		
		Date dNow = CommonUtils.getISTdate();
	
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String createdTime = ft.format(dNow);
		System.out.println(createdTime);
		
		//System.out.println(blog_Form.getPostTitle());
		System.out.println(blog_Form.getPost_content());
	try{
		User user=(User)request.getSession().getAttribute("USER");
		Blog blog=new Blog();
		blog.setBlog_title(blog_Form.getPostTitle());
		blog.setBlog_text(blog_Form.getPost_content());
		blog.setBlog_created_by(user);
		blog.setBlog_active_status(true);
		blog.setBlog_created_on(createdTime);
		
		
		boolean done =blogService.createBlog(blog);

	 
		if(done)
		{
			request.getSession().setAttribute("BLOG",blog);
			
			System.out.println("Post created successfully....");
			return "SUCCESS";			
		}
	}catch (Exception e){
		System.out.println(e);
	}
		return "ERROR";
	}
	
	public String seePost()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		List<Blog> list=blogService.getAllPosts();
		request.getSession().setAttribute("SHOWPOSTING", list);
		System.out.println("No of post"+list.size());
		
		return "SUCCESS";
	}
		
	public String deleteBlog()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
	String blogid =request.getParameter("xyz");
	boolean done=blogService.deleteBlogByBlogId(Integer.parseInt(blogid));
	if(done!=false){
		List<Blog> list =blogService.getAllPosts();
		
		if(list!=null){
			request.getSession().removeAttribute("SHOWPOSTING");
			request.getSession().setAttribute("SHOWPOSTING",list);
		}
	}
	return "SUCCESS";
		
	}
	
	public String editBlog(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		String blogid = request.getParameter("abc");
		Blog blog=blogService.getBlogDetails(Integer.parseInt(blogid));
		System.out.println("Blog Created");
		request.getSession().setAttribute("SAVEDBLOG", blog);
		
		return "SUCCESS";
	}
	
	public String updateBlogAction(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		Blog blog=(Blog)request.getSession().getAttribute("SAVEDBLOG");
		
		/*String blogid = request.getParameter("abc");
		Blog blog=blogService.getBlogDetails(Integer.parseInt(blogid));
		*/System.out.println("Blog Created");
		*/System.out.println("Kashif Blog is created");
		
		String title=request.getParameter("postTitle");
		String text=request.getParameter("post_content");
		blog.setBlog_title(title);
		blog.setBlog_text(text);
		boolean done =blogService.createBlog(blog);
		if(done){
			List<Blog> list=blogService.getAllPosts();
		request.getSession().setAttribute("SHOWPOSTING", list);
		}
		return "SUCCESS";
	}	
	@Override
	public Blog_Form getModel() {
		// TODO Auto-generated method stub
		return blog_Form;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void Comments(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		User user = (User)request.getSession().getAttribute("USER");
	    String commentId = request.getParameter("count");
	    String comment = request.getParameter("count2");

		
		
		Date dNow = CommonUtils.getISTdate();
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String createdTime = ft.format(dNow);
		System.out.println(createdTime);
		
		
		try{
			Comments comments=new Comments();
			comments.setComment_blog_id(Integer.parseInt(commentId));
			comments.setComment_text(comment);
			comments.setComment_status(1);
			comments.setComment_created_on(createdTime);
			
			boolean done=blogService.saveComments(comments);
			if(done=true)
			{
				request.getSession().setAttribute("COMMENT", comments);
				Comments comment1=null;
				System.out.println("comment Idddd :"+commentId);
				List<Comments> seqList = blogService.getAllCommentsByBlogId(Integer.parseInt(commentId));
				System.out.println("Comments seqList :"+seqList.size());
				String buffer =  "<html><div>";
	          for (Iterator iterator = seqList.iterator(); iterator.hasNext();){
	        	  comment1 = (Comments)iterator.next();
	        	
	        	  
	        		
	        	  	
	      	  
           buffer=buffer.concat("<div class='panel-heading clearfix'><h3 class='panel-title pull-left'>"+user.user_email_id+"</h3>"
           
           +"<h3 class='panel-title pull-right'>"+comment1.getComment_created_on()+"</h3>"+comments.getComment_status()+"<br/>");
            buffer=buffer.concat("<div class='list-group'>"+
	        	      "<div class='list-group-item'>"
     			   +" <h4 class='list-group-item-heading pull-center'>"+comment1.getComment_text()+"</h4>"+
	        	    " </div>"+
     			   "</div>"+
	        	   "</div>");
		      }
	         buffer=buffer.concat("</div></html>");
			 response.getWriter().println(buffer);
			}		    
		}
			catch (Exception e){
				System.out.println(e);
			}			
	}

}
	
	

