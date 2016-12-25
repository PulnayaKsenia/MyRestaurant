package ua.goit.java.finalProject.dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.finalProject.entity.Ingredient;
import ua.goit.java.finalProject.entity.Stock;

import java.util.List;

public class StockDao {

    private IngredientsDao ingredientsDao;
    private SessionFactory sessionFactory;

    public void save(Stock storage) {
        Session session = sessionFactory.getCurrentSession();
        session.save(storage);
    }

    public void remove(Stock storage) {
        sessionFactory.getCurrentSession().delete(storage);
    }

    @Transactional
    public Stock findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from Stock s where s.ingredient_id = :id");
        query.setParameter("id", id);

        return (Stock) query.uniqueResult();
    }

    @Transactional
    public void updateStorageInfo(int storageId, int amount) {
        Session session = sessionFactory.getCurrentSession();
        Stock storage = session.get(Stock.class, storageId);

        storage.setAmount(amount);

        session.update(storage);
    }

    @Transactional
    public List<Stock> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select s from Stock s").list();
    }

    public List<Stock> getIngredientsByName(String ingredientName) {

        Ingredient ingredient = ingredientsDao.getIngredientByName(ingredientName);

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from Storage s where s.ingredient = :ingredient");
        query.setParameter("ingredient", "%"+ingredient+"%");

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setIngredientsDao(IngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }
}
