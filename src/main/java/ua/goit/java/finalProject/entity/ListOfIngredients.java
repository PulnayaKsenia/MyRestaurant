package ua.goit.java.finalProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "list_of_ingredients")
public class ListOfIngredients implements Serializable {

    @Id
    @Column(name = "dish_id")
    private int dishId;

    @Id
    @Column(name = "ingredient_id")
    private int ingredientId;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Override
    public String toString() {
        return "ListOfIngredients{" +
                "ingredientId=" + ingredientId +
                ", dishId=" + dishId +
                '}';
    }
}