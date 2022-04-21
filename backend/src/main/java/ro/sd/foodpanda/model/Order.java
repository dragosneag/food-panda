package ro.sd.foodpanda.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "placing_date", nullable = false)
    private LocalDate placingDate;

    @Column(name = "placing_time", nullable = false)
    private LocalTime placingTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_food",
            joinColumns = @JoinColumn(name = "order_table_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foodList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Order() {
    }

    public Order(Status status, Integer price, LocalDate placingDate, LocalTime placingTime, List<Food> foodList, Customer customer, Restaurant restaurant) {
        this.status = status;
        this.price = price;
        this.placingDate = placingDate;
        this.placingTime = placingTime;
        this.foodList = foodList;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getPlacingDate() {
        return placingDate;
    }

    public void setPlacingDate(LocalDate placingDate) {
        this.placingDate = placingDate;
    }

    public LocalTime getPlacingTime() {
        return placingTime;
    }

    public void setPlacingTime(LocalTime placingTime) {
        this.placingTime = placingTime;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", price=" + price +
                ", placingDate=" + placingDate +
                ", placingTime=" + placingTime +
                ", foodList=" + foodList +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                '}';
    }
}
