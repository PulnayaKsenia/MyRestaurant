package ua.goit.java.finalProject.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Stock {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ingredient_id;

    @OneToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "amount")
    private int amount;

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    private Stock() {
    }

    public Stock(Ingredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ingredient_id=" + ingredient_id +
                ", ingredient=" + ingredient +
                ", amount=" + amount +
                '}';
    }
}
