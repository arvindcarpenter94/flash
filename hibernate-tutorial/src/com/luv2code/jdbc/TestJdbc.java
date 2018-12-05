package com.luv2code.jdbc;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.luv2code.hibernate.demo.entity.Student;
import com.mysql.cj.xdevapi.JsonArray;

public class TestJdbc {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Student.class)
					.buildSessionFactory();
		
		try {
			Session se = factory.getCurrentSession();
			se.beginTransaction();
			ArrayList<Student> newStudent = (ArrayList<Student>) se.createQuery("from Student s where s.lastName='Carpenter' OR s.firstName='Nidhi'").getResultList();
			JSONObject student = new JSONObject();
			JSONArray stArray = new JSONArray();
			for(Student s : newStudent) {
				JSONObject obj = new JSONObject();
				obj.put("ID", s.getId());
				obj.put("firstName", s.getFirstName());
				obj.put("lastName", s.getLastName());
				obj.put("email", s.getEmail());
				stArray.add(obj);
			}
			student.put("Student", stArray);
			System.out.println(student);
			se.getTransaction().commit();
			
			
		}catch(Exception exc){
			exc.printStackTrace();
		}finally {
			factory.close();
		}

	}

}
