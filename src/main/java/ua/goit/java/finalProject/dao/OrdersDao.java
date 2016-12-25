package ua.goit.java.finalProject.dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.finalProject.entity.Employee;
import ua.goit.java.finalProject.entity.Order;

import java.util.List;

public class OrdersDao {

    private SessionFactory sessionFactory;

    @Transactional
    public void save(Order orders) {
        Session session = sessionFactory.getCurrentSession();
        session.save(orders);
    }

    @Transactional
    public void remove(Order orders) {
        sessionFactory.getCurrentSession().delete(orders);
    }

    public Order findById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Order o where o.ID = :id");
        query.setParameter("id", id);

        return (Order) query.uniqueResult();
    }


    public List<Order> findAllOpenedOrders() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Order o where o.access = true").list();
    }

    public List<Order> getOrdersByTableNumber(int tableNumber) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Order o where o.tableNumber = :tableNumber");
        query.setParameter("tableNumber", tableNumber);

        return query.list();
    }

    public List<Order> getOrdersByWaiter(Employee employee) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Order o where o.employee = :id");
        query.setParameter("id", employee);

        return query.list();
    }

    public List<Order> getOrdersByDate(String date) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select o from Order o where o.date like :date");
        query.setParameter("date", date+"%");

        return query.list();
    }

    @Transactional
    public List<Order> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Order o").list();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

