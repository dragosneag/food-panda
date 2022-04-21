package ro.sd.foodpanda.DTO;

public class AdminDTO {

    private String id;
    private String name;
    private String username;
    private String password;
    private String restaurant;

    public AdminDTO() {
    }

    public AdminDTO(String id, String name, String username, String password, String restaurant) {
        this.name = name;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
