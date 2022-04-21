package ro.sd.foodpanda.DTO;

import java.util.List;

public class OrderDTO {

    private String id;
    private String status;
    private String price;
    private String placingDate;
    private String placingTime;
    private List<FoodDTO> foodList;
    private String customer;
    private String restaurant;

    public OrderDTO() {
    }

    public OrderDTO(String id, String status, String price, String placingDate, String placingTime, List<FoodDTO> foodList, String customer, String restaurant) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.placingDate = placingDate;
        this.placingTime = placingTime;
        this.foodList = foodList;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlacingDate() {
        return placingDate;
    }

    public void setPlacingDate(String placingDate) {
        this.placingDate = placingDate;
    }

    public String getPlacingTime() {
        return placingTime;
    }

    public void setPlacingTime(String placingTime) {
        this.placingTime = placingTime;
    }

    public List<FoodDTO> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodDTO> foodList) {
        this.foodList = foodList;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                ", placingDate='" + placingDate + '\'' +
                ", placingTime='" + placingTime + '\'' +
                ", foodList=" + foodList +
                ", customer='" + customer + '\'' +
                ", restaurant='" + restaurant + '\'' +
                '}';
    }
}
