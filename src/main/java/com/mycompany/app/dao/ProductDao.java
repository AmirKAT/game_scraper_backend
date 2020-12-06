package com.mycompany.app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.app.config.AppBean;
import com.mycompany.app.entity.Product;

public class ProductDao {
	SessionFactory sessionFactory = null;

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
