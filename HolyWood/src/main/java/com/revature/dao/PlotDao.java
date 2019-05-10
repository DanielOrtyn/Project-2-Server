package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Plot;
import com.revature.util.HibernateUtil;

public class PlotDao {
	public PlotDao() {
		// TODO Auto-generated constructor stub
	}

	public void insert(Plot plot) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(plot);

		tx.commit();
		/* ses.close(); */
	}

	public void update(Plot plot) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(plot);

		tx.commit();
		/* ses.close(); */
	}

	public Plot selectById(int id) {
		Session ses = HibernateUtil.getSession();

		Plot plot = ses.get(Plot.class, id);

		/* ses.close(); */

		return plot;
	}

	public List<Plot> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<Plot> plotList = ses
				.createQuery("from Plot", Plot.class).list();

		/* ses.close(); */

		return plotList;
	}
}
