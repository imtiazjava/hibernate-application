package com.cgi.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.cgi.main.entity.Product;

public class ClientCode {

	public static void main(String[] args) {
		// Step1: Create the configuration object
		Configuration cfg = new Configuration();
		// step2: invoke the configure the method to fetch for the hibernate.cfg.xml
		// file
		cfg.configure("hibernate1.cfg.xml");
		System.out.println(cfg);

		// step3: get the session factory object
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		// It will take driverclass,url,username&password from the configuration object
		// and passed this information to the connection pool program i.e c3po which
		// is used by hibernate internally in order to get the connection
		// from the database
		// plus if required it will create a tables internally.
		System.out.println(sessionFactory);

		// step4: get the session object
		Session session = sessionFactory.openSession();

		// step5: get the transaction object
		// insertion, updation, deletion we need transaction object
		// retrieve transaction object is not required

		// Transaction tx=session.beginTransaction();
		//retreiveRecord(session, 21);
        getAllRecords(session);
		// tx.commit();
		session.close();
		sessionFactory.close();
	}

	private static void getAllRecords(Session session) {
		//HQL queries
		Query query=session.createQuery("from com.cgi.main.entity.Product");
		//select operation
		List<Product> list = query.list();
		list.forEach(product->System.out.println(product));
		
	}

	private static void retreiveRecord(Session session, int pid) {
		// Product p=session.load(Product.class,pid);
		Object o = session.get(Product.class, pid);
		if (o == null) {
			System.out.println("No Record is found");
		} else {
			Product p = (Product) o;
			System.out.println(p.getPid() + "\t" + p.getPname() + "\t" + p.getPrice());
		}

	}

	private static void insert(Session session, Product product) {
		// step7:persistent the object
		session.save(product);
	}

}
