package com.Talent4Assure.WeBlogger.forms;


public class Blog_Form {
	public String postTitle;
   
    public String post_content;
    
    public int blog_created_by;
    public String blog_created_on;
    public String blog_comment;
    

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getBlog_text() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBlog_created_by() {
		return blog_created_by;
	}

	public void setBlog_created_by(int blog_created_by) {
		this.blog_created_by = blog_created_by;
	}

	public String getBlog_created_on() {
		return blog_created_on;
	}

	public void setBlog_created_on(String blog_created_on) {
		this.blog_created_on = blog_created_on;
	}

	public String getBlog_comment() {
		return blog_comment;
	}

	public void setBlog_comment(String blog_comment) {
		this.blog_comment = blog_comment;
	}
	
	
}
