package main.java.com.dao.impl;

import main.java.com.dao.EmployeeDao;
import main.java.com.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by gayathri on 6/7/17.
 */
public class EmployeeDaoImpl implements EmployeeDao{
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction = null;

    /*
       Configure Hibernate stuffs
        */
    @Override
    public void configuration() {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /*
    Method for save
     */
    @Override
    public void save(Employee employee) {
        System.out.println("\n>>>>>>>> Employee Save function >>>>>>>>>>");
        session = sessionFactory.openSession(); //creating session object
        try {
            transaction = session.beginTransaction();  //creating transaction object
            session.save(employee);
            transaction.commit();
            System.out.println("Save successfully employee "+employee.getId());

        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            System.out.println("Error occured when saving record employee "+employee.getId());
            System.out.println(e);

        }finally {
            session.close();
        }
    }

    /*
    Method for fetch data
     */
    @Override
    public List<Employee> fetchData() {
        System.out.println("\n\n>>>>>>>>>> Fetching function >>>>>>>>>>>>");
        session = sessionFactory.openSession();
        List<Employee> employeeList = null;
        try{
            transaction = session.beginTransaction();
           /* Query query = session.createQuery("FROM emp558");
            employeeList = query.list();*/

            employeeList = session.createQuery("FROM Employee").list();
            transaction.commit();

        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            System.out.println("Error occured when fetching data");
            System.out.println(e);

        }finally {
            //session.close();
        }

        return employeeList;
    }
}
