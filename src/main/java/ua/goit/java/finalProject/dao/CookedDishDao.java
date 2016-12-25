package ua.goit.java.finalProject.dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.java.finalProject.entity.CookedDish;

import java.util.List;
import java.util.Scanner;

public class CookedDishDao {

    private SessionFactory sessionFactory;

    public void save(CookedDish cookedDish) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cookedDish);
    }

    public void remove(CookedDish cookedDish) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cookedDish);
    }

    public CookedDish findByName() {

        Session session = sessionFactory.getCurrentSession();
        System.out.println("Enter dish name: ");
        String enteredName = new Scanner(System.in).nextLine();
        Query query = session.createQuery("select c from CookedDish c where c.dish = :name");
        query.setParameter("name", enteredName);

        return (CookedDish) query.uniqueResult();
    }

    public List<CookedDish> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CookedDish c");

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
