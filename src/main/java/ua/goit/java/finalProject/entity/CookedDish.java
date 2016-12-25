package ua.goit.java.finalProject.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "cooked_dish")
public class CookedDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "dish_id")
    private int dishId;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "orders_id")
    private int orderId;

    @Column(name = "date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CookedDish{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", employee=" + employee +
                ", date='" + date + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
