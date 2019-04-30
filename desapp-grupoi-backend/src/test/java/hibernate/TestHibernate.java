package hibernate;

import org.hibernate.Session;

import pruebaHibernate.EmployeeEntity;
import util.HibernateUtil;


 
public class TestHibernate
{
   public static void main(String[] args)
   {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      // Add new Employee object
      EmployeeEntity emp = new EmployeeEntity();
      emp.setEmployeeId(1);
      emp.setEmail("demo-user@mail.com");
      emp.setFirstName("demo");
      emp.setLastName("user");
      session.save(emp);
      session.getTransaction().commit();
      HibernateUtil.shutdown();
   }
}

//Output:
//	 
//Hibernate: drop table Employee if exists
//Hibernate: create table Employee (ID integer not null, EMAIL varchar(100) not null, FIRST_NAME varchar(100) not null, LAST_NAME varchar(100) not null, primary key (ID))
//Hibernate: alter table Employee add constraint UK_ardf0f11mfa6tujs3hflthwdv  unique (EMAIL)
//Hibernate: insert into Employee (EMAIL, FIRST_NAME, LAST_NAME, ID) values (?, ?, ?, ?)