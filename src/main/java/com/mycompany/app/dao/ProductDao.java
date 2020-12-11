package com.mycompany.app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.mycompany.app.entity.Product;

public class ProductDao {
	SessionFactory sessionFactory = null;

  // creates a session for when a connection to the database is required by all the threads
	public void save(Product product) {

		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		session.save(product);
		txn.commit();
		session.close();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
