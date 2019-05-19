package com.Talent4Assure.WeBlogger.beans;

public class Blog {
	private int blog_id;
	private String blog_title;
    private User blog_created_by;	
    private String blog_created_on;
    private String blog_text;
	private boolean  blog_active_status;
	
	
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public User getBlog_created_by() {
		return blog_created_by;
	}
	public void setBlog_created_by(User blog_created_by) {
		this.blog_created_by = blog_created_by;
	}
	public String getBlog_created_on() {
		return blog_created_on;
	}
	public void setBlog_created_on(String blog_created_on) {
		this.blog_created_on = blog_created_on;
	}
	public String getBlog_text() {
		return blog_text;
	}
	public void setBlog_text(String blog_text) {
		this.blog_text = blog_text;
	}
	public boolean getBlog_active_status() {
		return blog_active_status;
	}
	public void setBlog_active_status(boolean blog_active_status) {
		this.blog_active_status = blog_active_status;
	}
	
}