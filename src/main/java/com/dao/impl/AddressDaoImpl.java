package main.java.com.dao.impl;

import main.java.com.dao.AddressDao;
import main.java.com.model.Address;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by gayathri on 6/7/17.
 */
public class AddressDaoImpl implements AddressDao{
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction = null;

    /*
       Configure Hibernate stuffs
        */
    @Override
    public void configuration() {
        try{
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /*
    Method for save
     */
    @Override
    public void save(Address address) {
        System.out.println("\n>>>>>>>> Address Save function >>>>>>>>>>");
        session = sessionFactory.openSession(); //creating session object
        try {
            transaction = session.beginTransaction();  //creating transaction object
            session.save(address);
            transaction.commit();
            System.out.println("Save successfully address "+address.getId());

        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            System.out.println("Error occured when saving record address "+address.getId());
            System.out.println(e);

        }finally {
            session.close();
        }
    }
}
