package com.Talent4Assure.WeBlogger.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Talent4Assure.WeBlogger.beans.Blog;
import com.Talent4Assure.WeBlogger.beans.Comments;
import com.Talent4Assure.WeBlogger.dao.BaseDao;
import com.Talent4Assure.WeBlogger.dao.BlogDao;
import com.Talent4Assure.WeBlogger.exceptions.BusinessException;

public class BlogDaoImpl extends BaseDao implements BlogDao
{

	@Override
	public boolean createBlog(Blog blog) {
		
		boolean done=false;
		Session session=null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(blog);
			// committing at database
			tx.commit();
			done=true;
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			releaseSession(session);
		}
		return done;
		
	}

	@Override
	public List<Blog> getAllPosts()
	{
		List<Blog> blogs=null;
		Session session=null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
		Query query=session.createQuery("from Blog");
		blogs = (List<Blog>)query.list();
			// committing at database
			tx.commit();
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			releaseSession(session);
		}
		return blogs;
	}

	@Override
	public boolean deleteBlogByBlogId(int parseInt) {
				
		boolean done=false;
		Session session=null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			Query query=session.createQuery("delete from Blog where blog_id=:parseInt");
			query.setParameter("parseInt", parseInt);
			query.executeUpdate();
			// committing at database
			tx.commit();
			done=true;
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			releaseSession(session);
		}
		return done;
	}

	/*@Override
	public boolean editBlogByBlogId(int parseInt) {
		boolean done=false;
		Session session=null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(Blog);
			// committing at database
			tx.commit();
			done=true;
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			releaseSession(session);
		}
		return done;
	}*/

	@Override
	public Blog getBlogDetails(int string) {
		// TODO Auto-generated method stub
		
		Blog blogs=null;
		Session session=null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			Query query=session.createQuery("from Blog where blog_id=:string");
			query.setParameter("string", string);
			blogs = (Blog)query.uniqueResult();
		
			// committing at database
			tx.commit();
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			releaseSession(session);
		}
		return blogs;
	}

	@Override
	public boolean editBlogByBlogId(int parseInt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Comments> getAllCommentsByBlogId(int postingId) {
		
		List<Comments> comments=null;
		Session session=null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			
		Query query=session.createQuery("from Comments where comment_blog_id=:postingId");
		query.setParameter("postingId", postingId);
		comments = (List<Comments>)query.list();
		
		System.out.println("size of comments : "+comments.size());
			// committing at database
			tx.commit();
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			releaseSession(session);
		}
		return comments;
		
	}

	@Override
	public boolean saveComments(Comments comment) {
		
		boolean done=false;
		Session session=null;
		Transaction tx=null;
		try {
			session = getSession();
			tx = (Transaction)session.beginTransaction();
			session.save(comment);
			tx.commit();
			done=true;
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			releaseSession(session);
		}
		return done;		
	}
	
}


	

