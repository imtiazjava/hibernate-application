package com.cgi.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cgi.main.entity.Product;

public class ClientCode {

	public static void main(String[] args) {
		 //Step1: Create the configuration object
		Configuration cfg=new Configuration();
		//step2: invoke the configure the method to fetch for the hibernate.cfg.xml file
		cfg.configure("hibernate1.cfg.xml");
		System.out.println(cfg);
		
		//step3: get the session factory object
		SessionFactory sessionFactory=cfg.buildSessionFactory();
		//It will take driverclass,url,username&password from the configuration object
		// and passed this information to the connection pool program i.e c3po which
		// is used by hibernate internally in order to get the connection
		// from the database
		//plus if required it will create a tables internally.
		System.out.println(sessionFactory);
		
		//step4: get the session object
		Session session=sessionFactory.openSession();
		
		//step5: get the transaction object
			// insertion, updation, deletion we need transaction object
		    // retrieve transaction object is not required
		
		Transaction tx=session.beginTransaction();
		
		//step6: create pojo class object and store the data into that
		Product product=new Product();
		//product.setPid(1);
		product.setPname("ipad");
		product.setPrice(30000.00);
		
		//step7:persistent the object
		session.save(product);
		
		//step8:commit the transaction
		tx.commit();

		//step9: close the session and session factory object
		session.close();
		sessionFactory.close();
	}

}
