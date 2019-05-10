package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Movie;
import com.revature.util.HibernateUtil;

public class MovieDao {
	public MovieDao() {
		// TODO Auto-generated constructor stub
	}

	public void insert(Movie movie) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(movie);

		tx.commit();
		/* ses.close(); */
	}

	public void update(Movie movie) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(movie);

		tx.commit();
		/* ses.close(); */
	}

	public Movie selectById(int id) {
		Session ses = HibernateUtil.getSession();

		Movie movie = ses.get(Movie.class, id);

		/* ses.close(); */

		return movie;
	}

	public List<Movie> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<Movie> movieList = ses
				.createQuery("from Movie", Movie.class).list();

		/* ses.close(); */

		return movieList;
	}
}
