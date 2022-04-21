package ro.sd.foodpanda.DTO;

import java.util.List;

public class RestaurantDTO {

    private String name;
    private String address;
    private List<String> zones;
    private String admin;
    private List<String> categories;

    public RestaurantDTO() {
    }

    public RestaurantDTO(String name, String address, List<String> zones, String admin, List<String> categories) {
        this.name = name;
        this.address = address;
        this.zones = zones;
        this.admin = admin;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
