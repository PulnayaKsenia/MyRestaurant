package ua.goit.java.finalProject.services;

import org.springframework.transaction.annotation.Transactional;

import ua.goit.java.finalProject.dao.DishDao;
import ua.goit.java.finalProject.dao.IngredientsDao;
import ua.goit.java.finalProject.dao.StockDao;
import ua.goit.java.finalProject.entity.Dish;
import ua.goit.java.finalProject.entity.Ingredient;
import ua.goit.java.finalProject.entity.Stock;

import java.util.List;

public class StockService {

    private StockDao stockDao;
    private IngredientsDao ingredientsDao;
    private DishDao dishDao;

    @Transactional
    public List<Stock> getAll() {
        return stockDao.findAll();
    }

    @Transactional
    public void addNewIngredient(Ingredient ingredient, int amount) {

        ingredientsDao.save(ingredient);
        stockDao.save(new Stock(ingredient, amount));
    }

    @Transactional
    public void updateStorage(int stockId, String name, int amount) {

        Stock stock = getById(stockId);

        ingredientsDao.updateIngredient(stock.getIngredient_id(), name);
        stockDao.updateStorageInfo(stockId, amount);
    }

    @Transactional
    public boolean ingredientCanBeDeleted(int ingredientId) {

        boolean result = true;

        List<Dish> list = dishDao.findAll();

        Ingredient i = ingredientsDao.getById(ingredientId);

        for (Dish d : list) {
            for (Ingredient ing : d.getIngredients()) {
                if (ing.getId() == ingredientId) result = false;
            }
        }
        return result;
    }

    @Transactional
    public List<Stock> getByName(String ingredientName) {
        return stockDao.getIngredientsByName(ingredientName);
    }

    @Transactional
    public Stock getById(int ingredientId) {
        return stockDao.findById(ingredientId);
    }

    @Transactional
    public void delete(Stock storage) {
        stockDao.remove(storage);
        ingredientsDao.remove(storage.getIngredient());
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public void setIngredientsDao(IngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }
}
