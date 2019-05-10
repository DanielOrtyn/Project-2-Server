package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * Configuration (class)
 * Configuration's job is to gather information from hibernate.cfg.xml and
 *  use that information to create a SessionFactory
 * 
 * SessionFactory (interface)
 * SessionFactory's job is to create sessions and store information on 
 * how to make connections to your database. Once it configures it's immutable
 * 
 * Session (interface)
 * Session manages the connection to your DB and provides
 * CREATE, READ, UPDATE, AND DELETE operations
 * 
 * Transaction (interface)
 * Transaction manages your transaction and cache. Must be ACID.
 * ACID:
 * Atomicity: All or nothing transaction
 * Consistency: After transaction DB schema remains intact
 * Isolation: Transactions cannot interfere with each other(concurrent transactions allowed)
 * Durability: Data will persist
 * 
 * 
 * 
 */

public class HibernateUtil {
	private static Session ses;
	private static SessionFactory sf = new Configuration()
			.configure("hibernate.cfg.xml").buildSessionFactory();

	public static Session getSession() {
		if (ses == null) {
			ses = sf.openSession();
		}
		return ses;
	}

	public static void closeSes() {
		ses.close();
	}
}
