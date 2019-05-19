package com.Talent4Assure.WeBlogger.services;

import java.util.List;

import com.Talent4Assure.WeBlogger.beans.Blog;
import com.Talent4Assure.WeBlogger.beans.Comments;
import com.Talent4Assure.WeBlogger.beans.User;

public interface BlogService
{

	boolean createBlog(Blog blog);

	List<Blog> getAllPosts();

	boolean deleteBlogByBlogId(int parseInt);

	boolean editBlogByBlogId(int parseInt);

	com.Talent4Assure.WeBlogger.beans.Blog getBlogDetails(int i);



	
	List<Comments> getAllCommentsByBlogId(
			int posting_id);

	boolean saveComments(Comments comments);

}