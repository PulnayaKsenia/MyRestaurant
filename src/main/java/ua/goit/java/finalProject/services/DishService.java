package ua.goit.java.finalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.finalProject.dao.DishDao;
import ua.goit.java.finalProject.dao.IngredientsDao;
import ua.goit.java.finalProject.dao.ListOfIngredientsDao;
import ua.goit.java.finalProject.dao.OrdersDao;
import ua.goit.java.finalProject.entity.Dish;
import ua.goit.java.finalProject.entity.Ingredient;
import ua.goit.java.finalProject.entity.ListOfIngredients;
import ua.goit.java.finalProject.entity.Order;


import java.util.ArrayList;
import java.util.List;

public class DishService {

    private DishDao dishDao;
    private ListOfIngredientsDao listOfIngredientsDao;
    private IngredientsDao ingredientsDao;
    private OrdersDao ordersDao;
    private Object dishes;

    @Transactional
    public void updateDishInfo(int id, Dish dishWithNewInformation) {
        dishDao.updateDish(id, dishWithNewInformation);
    }

    @Transactional
    public List<Ingredient> getIngredientsToThisDish(int dishId) {

        List<ListOfIngredients> listOfIngredients = listOfIngredientsDao.findByDishId(dishId);

        List<Ingredient> ingredients = new ArrayList<>();
        for (ListOfIngredients ingredientsToDish : listOfIngredients) {
            ingredients.add(ingredientsDao.getById(ingredientsToDish.getIngredientId()));
        }
        return ingredients;
    }

    @Transactional
    public List<Ingredient> setListOfIngredients(List listOfIngredientsForTheDish) {

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        for (int i = 0; i < listOfIngredientsForTheDish.size(); i++) {
            int id = Integer.parseInt(listOfIngredientsForTheDish.get(i).toString());
            Ingredient ingredient = ingredientsDao.getById(id);
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    @Transactional
    public Dish getDishByID(int dishId) {
        return dishDao.findById(dishId);
    }


    @Transactional
    public void delete(int dishId) {
        listOfIngredientsDao.removeById(dishId);
        dishDao.remove(dishDao.findById(dishId));
    }

    public List<Dish> getDishByName(String dishName) {
        return dishDao.findByName(dishName);
    }

    @Transactional
    public void addNewDish(Dish dish) {
        dishDao.save(dish);
    }

    @Transactional
    public boolean canDishBeDeleted(int dishID) {

        boolean result = true;

        List<Order> list = ordersDao.findAll();

        for (Order orders : list) {
            for (Dish dishFromOrder : orders.getDishes()) {
                if (dishFromOrder.getId() == dishID) {
                    result = false;
                }
            }
        }
        return result;
    }

    @Transactional
    public void saveNewDish(String name, double price, int weight, List listOfIngredients) {

        Dish dish = new Dish();

        dish.setName(name);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setIngredients(setListOfIngredients(listOfIngredients));

        dishDao.save(dish);
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setListOfIngredientsDao(ListOfIngredientsDao listOfIngredientsDao) {
        this.listOfIngredientsDao = listOfIngredientsDao;
    }

    public void setOrdersDao(OrdersDao ordersDAO) {
        this.ordersDao = ordersDAO;
    }

    @Autowired
    public void setIngredientsDao(IngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }

    public Object getDishes() {
        return dishes;
    }
}


