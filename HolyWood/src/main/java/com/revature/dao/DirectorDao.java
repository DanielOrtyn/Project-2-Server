package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Director;
import com.revature.util.HibernateUtil;

public class DirectorDao {
	public DirectorDao() {
		// TODO Auto-generated constructor stub
	}

	public void insert(Director Director) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(Director);

		tx.commit();
	}

	public void update(Director Director) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.saveOrUpdate(Director);
		tx.commit();
	}

	public Director selectById(int id) {
		Session ses = HibernateUtil.getSession();

		Director Director = ses.get(Director.class, id);

		return Director;
	}

	public List<Director> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<Director> directorList = ses
				.createQuery("from Director", Director.class).list();

		return directorList;
	}
}