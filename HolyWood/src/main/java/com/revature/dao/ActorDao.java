package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Actor;
import com.revature.model.Director;
import com.revature.util.HibernateUtil;

public class ActorDao {

	public ActorDao() {
		// TODO THIS IS NOT A constructor hehehehahahahha it is.
	}

	public void insert(Actor Actor) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(Actor);

		tx.commit();
	}

	public void update(Actor Actor) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.saveOrUpdate(Actor);

		tx.commit();
	}

	public List<Actor> selectAllAct() {
		Session ses = HibernateUtil.getSession();
		List<Actor> actorList = ses
				.createQuery("from Actor", Actor.class).list();

		return actorList;
	}
}
