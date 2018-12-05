package com.luv2code.hibernate.demo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class HibernateRetrieve {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Student.class)
					.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			Student stu1 = new Student("Nidhi", "Singh", "nsingh6@gmail.com");
			session.beginTransaction();
			session.save(stu1);
			session.getTransaction().commit();
			
			
		}catch(Exception exc){
			exc.printStackTrace();
		}finally {
			factory.close();
		}

	}

}
