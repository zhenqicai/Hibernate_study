package com.zhenqi.test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zhenqi.entity.single.many2many.Student;
import com.zhenqi.entity.single.many2many.Teacher;
import com.zhenqi.entity.single.many2one.Many;
import com.zhenqi.entity.single.many2one.One;
import com.zhenqi.entity.single.one2many.Dept;
import com.zhenqi.entity.single.one2many.Employee;
import com.zhenqi.entity.single.one2one.IDCard;
import com.zhenqi.entity.single.one2one.Man;
import com.zhenqi.entity.two.one2one.Loginer;
import com.zhenqi.entity.two.one2one.User;
import com.zhenqi.util.HibernateUtil;

public class EntityTest {



		private Session session;
		private SessionFactory sf;

		@Before
		public void openSession() {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();

			session.beginTransaction();
		}

		@After
		public void closeSession() {
			session.close();
			sf.close();
		}

		@Test
		public void addStudent() {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			session.beginTransaction();
			Student student = new Student();
			student.setId(2);
			student.setName("dr02");
			student.setAge(22);
			session.save(student);
			session.getTransaction().commit();

			session.close();
			sf.close();
		}

		public void addIDCard() {
			IDCard idCard = new IDCard();
			idCard.setAge(10);
			idCard.setName("二代身份证");
			session.save(idCard);
			session.getTransaction().commit();
		}

		@Test
		public void addMan() {
			Man man = new Man();
			man.setName("李四");
			man.setAge(54);
			man.setAddress("上海浦东新区西门路78号");

			IDCard idCard = (IDCard) session.get(IDCard.class, 1);
			man.setIdCard(idCard);
			session.save(man);
			session.getTransaction().commit();
		}

		@Test
		public void manList() {
			List<Man> list = session.createQuery("from Man").list();
			for (Man man : list) {
				System.out.println(man.getId() + "|" + man.getName() + "|" + man.getAge() + "|" + man.getAddress() + "|"
						+ man.getIdCard());
			}
		}

	    @Test
		public void addEmp() {
			for (int i = 0; i < 10; i++) {
				Employee emp = new Employee();
				emp.setId(i+1);
				emp.setName("江夏"+i);
				emp.setAge(43+i);
				session.save(emp);
			}
			session.getTransaction().commit();
		}
		@Test
		public void addDept(){
		   Dept dept=new Dept();
		   dept.setId(1);
		   dept.setName("太行部");
		   List<Employee> list=session.createQuery("from Employee").list();
		   Set<Employee> emps=new HashSet<Employee>();
		   for(Employee emp : list){
		       emps.add(emp);
		   }
		   dept.setEmps(emps);
		   session.save(dept);
		   session.getTransaction().commit();
		}
		@Test
		public void addMany(){
		   Many many=new Many();
		   many.setName("多方");
		   
		   One one=new One();
		   one.setMany(many);
		   one.setName("yifang");
		   
		   session.save(one);
		   session.getTransaction().commit();
		}
		@Test
		public void addTeacher(){
		   Set<Student> students=new HashSet<Student>();
		   for(int i=0;i<10;i++){
		      Student stu=new Student();
		      stu.setAge(11+i);
		      stu.setName("ta"+i);
		      students.add(stu);
		   }
		   Teacher tea=new Teacher();
		   tea.setAge(45);
		   tea.setName("Tony");
		   tea.setStudents(students);
		   session.save(tea);
		   session.getTransaction().commit();
		}
		@Test
		public void addUser(){
			Loginer login=new Loginer();
			login.setPassword("123");
			login.setUsername("loginer");
			
			User user=new User();
			user.setPassword("123");
			user.setUsername("user");
			user.setSex("male");
			user.setTel("1317725471");
			user.setLoginer(login);
			login.setUser(user);
			
			session.save(user);
			session.getTransaction().commit();
		}
	}

