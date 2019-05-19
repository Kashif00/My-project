package com.Talent4Assure.WeBlogger.services.Impl;
import java.util.List;

import com.Talent4Assure.WeBlogger.beans.Blog;
import com.Talent4Assure.WeBlogger.beans.Comments;
import com.Talent4Assure.WeBlogger.dao.BlogDao;
import com.Talent4Assure.WeBlogger.dao.Impl.BlogDaoImpl;
import com.Talent4Assure.WeBlogger.services.BlogService;

public class BlogServiceImpl implements BlogService
{
	BlogDao blogDao=new BlogDaoImpl(); 
	
	@Override
	public boolean createBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.createBlog(blog);
	}

	@Override
	public List<Blog> getAllPosts() {
		// TODO Auto-generated method stub
		return blogDao.getAllPosts() ;
	}

	public boolean deleteBlogByBlogId(int parseInt) {
		// TODO Auto-generated method stub
		return blogDao.deleteBlogByBlogId(parseInt);
	}
	
	public boolean editBlogByBlogId(int parseInt) {
		// TODO Auto-generated method stub
		return blogDao.editBlogByBlogId(parseInt);
	}

	
	public Blog getBlogDetails(int string) {
		// TODO Auto-generated method stub
		return blogDao.getBlogDetails(string);
	}





	@Override
	public List<Comments> getAllCommentsByBlogId(int posting_id) {
		// TODO Auto-generated method stub
		return blogDao.getAllCommentsByBlogId(posting_id);
	}

	@Override
	public boolean saveComments(Comments comments) {
		// TODO Auto-generated method stub
		return blogDao.saveComments(comments);
	}
	
}
