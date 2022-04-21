package ro.sd.foodpanda.DTO;

public class FoodDTO {

    private String id;
    private String name;
    private String price;
    private String description;
    private String restaurant;

    public FoodDTO() {
    }

    public FoodDTO(String name, String price, String description, String restaurant) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.restaurant = restaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
