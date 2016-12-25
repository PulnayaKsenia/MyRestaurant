package ua.goit.java.finalProject.services;

import org.springframework.transaction.annotation.Transactional;

import ua.goit.java.finalProject.dao.DishDao;
import ua.goit.java.finalProject.dao.DishToOrderDao;
import ua.goit.java.finalProject.dao.OrdersDao;
import ua.goit.java.finalProject.dao.Requests;
import ua.goit.java.finalProject.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderService {

    private OrdersDao ordersDao;
    private DishDao dishDao;
    private DishToOrderDao dishToOrderDao;
    private EmployeeService employeeService;
    private StockService storageService;
    private Requests requests = new Requests();

    public List<Dish> allDishes() {
        return dishDao.findAll();
    }

    public List<Order> getAllOrders() {
        return ordersDao.findAll();
    }

    @Transactional
    public Order getOrderById(int id) {
        return ordersDao.findById(id);
    }

    @Transactional
    public void deleteOrderById(int orderId) {
        dishToOrderDao.removeById(orderId);
        ordersDao.remove(ordersDao.findById(orderId));
    }

    @Transactional
    public void updateOrderInfo(int orderId, int tableNumber, int waiterId) {
        Order order = ordersDao.findById(orderId);
        order.setTableNumber(tableNumber);
        order.setEmployee(employeeService.getEmployeeByID(waiterId));
    }

    @Transactional
    public void addTheDishToOrder(int orderId, int dishId) {

        List<Dish> dishes = ordersDao.findById(orderId).getDishes();

        dishes.add(dishDao.findById(dishId));

        ordersDao.findById(orderId).setListOfDishes(dishes);
    }

    @Transactional
    public List<Order> getOrdersByWaiter(Employee employee) {
        return ordersDao.getOrdersByWaiter(employee);
    }

    @Transactional
    public List<Order> getOrderByDate(String date) {
        return ordersDao.getOrdersByDate(date);
    }

    public String enoughIngredients(ArrayList selectedDishes) {

        boolean result = true;

        StringBuffer message = new StringBuffer();

        HashMap<Integer, Integer> amountOfIngredientsForOrder = amountOfEachIngredientId(selectedDishes);
        System.out.println(amountOfIngredientsForOrder.toString());

        for (Integer i : amountOfIngredientsForOrder.keySet()) {
            int ingredientId = i;
            Stock ingredientInStorage = storageService.getById(ingredientId);

            if (ingredientInStorage.getAmount() - amountOfIngredientsForOrder.get(ingredientId) < 0) {
                result = false;
                message.append("\nNot enough " + storageService.getById(ingredientId).getIngredient().getName() + "!");
            }
        }

        if (result) {
            message.append("successful added!");
        }

        return message.toString();
    }

    @Transactional
    public void decreaseIngredients(ArrayList selectedDishes) {

        HashMap<Integer, Integer> amountOfIngredientsForOrder = amountOfEachIngredientId(selectedDishes);

        for (Integer ingredientId : amountOfIngredientsForOrder.keySet()) {

            Stock ingredientInStorage = storageService.getById(ingredientId);
            int amountOfIngredientAfterDecrease = ingredientInStorage.getAmount() - amountOfIngredientsForOrder.get(ingredientId);

            ingredientInStorage.setAmount(amountOfIngredientAfterDecrease);
        }
    }

    private HashMap<Integer, Integer> amountOfEachIngredientId(ArrayList selectedDishes) {

        ArrayList<Dish> dishesToOrder = new ArrayList<>();

        for (int i = 0; i < selectedDishes.size(); i++) {
            int id = Integer.parseInt(selectedDishes.get(i).toString());
            dishesToOrder.add(dishDao.findById(id));
        }

        ArrayList<Ingredient> ingredientsToOrder = new ArrayList<>();

        for (int i = 0; i < dishesToOrder.size(); i++) {

            Dish dish = dishesToOrder.get(i);

            List<Ingredient> ingredientsToDish = dish.getIngredients();

            for (int j = 0; j < ingredientsToDish.size(); j++) {
                ingredientsToOrder.add(ingredientsToDish.get(j));
            }
        }

        HashMap<Integer, Integer> amountOfIngredientsForOrder = new HashMap();

        for (int i = 0; i < ingredientsToOrder.size(); i++) {

            int id = ingredientsToOrder.get(i).getId();

            if (amountOfIngredientsForOrder.keySet().contains(id)) {
                int amount = amountOfIngredientsForOrder.get(id);
                amountOfIngredientsForOrder.put(id, amount + 1);
            } else {
                amountOfIngredientsForOrder.put(id, 1);
            }
        }
        return amountOfIngredientsForOrder;
    }

    @Transactional
    public void deleteDishFromOrder(int orderId, int dishId) {

        Order orders = ordersDao.findById(orderId);
        Dish dish = dishDao.findById(dishId);

        List<Dish> dishes = orders.getDishes();
        dishes.remove(dish);

        orders.setListOfDishes(dishes);

        for (Ingredient ingredient : dish.getIngredients()) {
            Stock ingredientInStorage = storageService.getById(ingredient.getId());
            int newAmount = ingredientInStorage.getAmount() + 1;
            ingredientInStorage.setAmount(newAmount);
        }
    }

    @Transactional
    public boolean IsThereNoOrdersWithThisEmployee(Employee employee) {

        boolean result = false;

        List<Order> list = ordersDao.getOrdersByWaiter(employee);

        if (list.isEmpty()) result = true;

        return result;
    }


    @Transactional
    public List<Order> getOrdersByTableNumber(int tableNumber) {
        return ordersDao.getOrdersByTableNumber(tableNumber);
    }

    @Transactional
    public void creatingOrder(int waiterId, int tableNumber, ArrayList selectedDishes) {

        Order order = new Order();

        order.setEmployee(employeeService.getEmployeeByID(waiterId));
        order.setTableNumber(tableNumber);
        order.setDate(requests.getCurrentTime());

        List<Dish> dishes = new ArrayList<>();

        for (int i = 0; i < selectedDishes.size(); i++) {
            Dish dish = dishDao.findById(Integer.parseInt(selectedDishes.get(i).toString()));
            dishes.add(dish);
        }

        decreaseIngredients(selectedDishes);

        order.setListOfDishes(dishes);

        ordersDao.save(order);
    }

    public void setStockService(StockService storageService) {
        this.storageService = storageService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setDishToOrderDAO(DishToOrderDao dishToOrderDAO) {
        this.dishToOrderDao = dishToOrderDAO;
    }

    public void setOrdersDao(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    public void setDishDao(DishDao dishesDao) {
        this.dishDao = dishesDao;
    }

}
