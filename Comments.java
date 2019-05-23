package com.Talent4Assure.WeBlogger.beans;

public class Comments {
	public int comment_id;
	public int comment_blog_id;
    public String comment_text;	 
    public int comment_status;
    public String comment_created_on;
    
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getComment_blog_id() {
		return comment_blog_id;
	}
	public void setComment_blog_id(int comment_blog_id) {
		this.comment_blog_id = comment_blog_id;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	
	public int getComment_status() {
		return comment_status;
	}
	public void setComment_status(int comment_status) {
		this.comment_status = comment_status;
	}
	public String getPosting_comments_id() {
		// TODO Auto-generated method stub
		return null;
	}
	public String get_comment_on() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getComment_created_on() {
		return comment_created_on;
	}
	public void setComment_created_on(String comment_created_on) {
		this.comment_created_on = comment_created_on;
	}
	
	
}