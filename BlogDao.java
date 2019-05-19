package com.Talent4Assure.WeBlogger.dao;

import java.util.List;

import com.Talent4Assure.WeBlogger.beans.Blog;
import com.Talent4Assure.WeBlogger.beans.Comments;
import com.Talent4Assure.WeBlogger.beans.User;

public interface BlogDao {

	boolean createBlog(Blog blog);
	List<Blog> getAllPosts();
	boolean deleteBlogByBlogId(int parseInt);
	boolean editBlogByBlogId(int parseInt);
	Blog getBlogDetails(int string);
	List<Comments> getAllCommentsByBlogId(int postingId);
	boolean saveComments(Comments comments);
	
}
