package ro.sd.foodpanda.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "customer")
public class Customer extends User{

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String name, String username, String password, List<Order> orders) {
        super(name, username, password);
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
