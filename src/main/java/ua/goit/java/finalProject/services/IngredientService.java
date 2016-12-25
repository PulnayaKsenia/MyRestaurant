package ua.goit.java.finalProject.services;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.finalProject.dao.IngredientsDao;
import ua.goit.java.finalProject.dao.ListOfIngredientsDao;
import ua.goit.java.finalProject.entity.Ingredient;
import ua.goit.java.finalProject.entity.ListOfIngredients;


import java.util.ArrayList;
import java.util.List;


public class IngredientService {

    private ListOfIngredientsDao listOfIngredientsDao;
    private IngredientsDao ingredientsDao;

    @Transactional
    public void addNewIngredient(Ingredient ingredient){
        ingredientsDao.save(ingredient);
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

    public List<Ingredient> getAllIngredients() {
        return ingredientsDao.findAll();
    }

    public void setIngredientsDao(IngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }

    public void setListOfIngredientsDao(ListOfIngredientsDao listOfIngredientsDao) {
        this.listOfIngredientsDao = listOfIngredientsDao;
    }
}
