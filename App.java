package org.example;

import org.example.Department;
import org.example.Employee;
import org.example.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.util.*;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Configuration con = new Configuration().addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class).addAnnotatedClass(Role.class);
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mariadb://localhost:3306/hibernate?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "root");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MariaDBDialect");

        settings.put(Environment.SHOW_SQL, "true");

        settings.put(Environment.HBM2DDL_AUTO, "update");

        con.setProperties(settings);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        //Query : 1

        Query query = session.createQuery("FROM " + "Employee");

        List<Employee> employeeList = query.list();

        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        tx.commit();

        //Query : 2

        session.beginTransaction();
        Employee employee = new Employee();
        employee.setRole_id(1);
        employee.setName("Daniel");
        employee.setSalary(1200000);
        employee.setDept_id(1);
        employee.setId(1);

        session.save(employee);


        tx.commit();

        session.beginTransaction();

        Employee emp = new Employee();
        emp.setRole_id(2);
        emp.setName("David");
        emp.setSalary(8900000);
        emp.setDept_id(1);
        emp.setId(2);

        session.save(employee);


        tx.commit();


        //Query : 3

        session.beginTransaction();
        Department department = new Department();
        department.setDept_name("Sales Management");
        department.setDept_id(1);

        Department d1 = new Department();
        d1.setDept_id(2);
        d1.setDept_name("Engineering");

        session.save(d1);
        tx.commit();

        session.beginTransaction();

        session.save(department);

        tx.commit();

        // Query : 4

        session.beginTransaction();
        emp = null;
        emp = session.get(Employee.class, 1);
        Department dept = null;
        dept = session.get(Department.class, emp.getDept_id());

        System.out.println(dept);
        tx.commit();

        //Query : 5
        Role role = new Role();
        role.setRole_name("DEV");
        role.setId(1);
        session.save(role);

        //Query : 6

        session.beginTransaction();
        int emp_id = 1;
        Employee del_dept = null;
        del_dept = session.get(Employee.class, emp_id);
        session.delete(del_dept);


        Query q = session.createQuery("FROM " + "Employee ");

        List<Employee> emp_list = q.list();


        for (Employee e : emp_list) {
            System.out.println(e);
        }

        tx.commit();

    }
}
