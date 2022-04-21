package ro.sd.foodpanda.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table (name = "admin")
public class Admin extends User{

    @OneToOne(mappedBy = "admin", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "restaurant-admin")
    private Restaurant restaurant;

    public Admin() {
    }

    public Admin(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
