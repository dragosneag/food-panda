package ro.sd.foodpanda.service;

import ro.sd.foodpanda.DTO.*;
import ro.sd.foodpanda.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Mappers {

    public FoodDTO convertFoodToDTO(Food food) {
        FoodDTO foodDTO = new FoodDTO();

        foodDTO.setId(String.valueOf(food.getId()));
        foodDTO.setName(food.getName());
        foodDTO.setPrice(String.valueOf(food.getPrice()));
        foodDTO.setDescription(food.getDescription());
        foodDTO.setRestaurant(food.getRestaurant().getName());
        return foodDTO;
    }

    public CategoryDTO convertCategoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        List<FoodDTO> foodList = new ArrayList<>();

        categoryDTO.setName(category.getName());
        for (Food food : category.getFoodList()) {
            foodList.add(convertFoodToDTO(food));
        }
        categoryDTO.setFoodList(foodList);
        return categoryDTO;
    }

    public RestaurantDTO convertRestaurantToDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        List<String> zones = new ArrayList<>();
        List<String> categories = new ArrayList<>();

        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setAddress(restaurant.getAddress());
        for (Zone zone : restaurant.getZones()) {
            zones.add(zone.getName());
        }
        restaurantDTO.setZones(zones);
        restaurantDTO.setAdmin(restaurant.getAdmin().getName());
        for (Category category : restaurant.getCategories()) {
            categories.add(category.getName());
        }
        restaurantDTO.setCategories(categories);
        return restaurantDTO;
    }

    public AdminDTO convertAdminToDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setId(String.valueOf(admin.getId()));
        adminDTO.setName(admin.getName());
        adminDTO.setUsername(admin.getUsername());
        adminDTO.setPassword(admin.getPassword());
        if (admin.getRestaurant() != null) {
            adminDTO.setRestaurant(admin.getRestaurant().getName());
        }
        return adminDTO;
    }

    public Admin convertAdminFromDTO(AdminDTO adminDTO) {
        Admin admin = new Admin();

        admin.setId(Integer.valueOf(adminDTO.getId()));
        admin.setName(adminDTO.getName());
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(adminDTO.getPassword());
        Restaurant restaurant = new Restaurant();
        restaurant.setName(adminDTO.getRestaurant());
        admin.setRestaurant(restaurant);
        return admin;
    }

    public OrderDTO convertOrderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        List<FoodDTO> foodList = new ArrayList<>();

        orderDTO.setId(String.valueOf(order.getId()));
        orderDTO.setStatus(String.valueOf(order.getStatus()));
        orderDTO.setPrice(String.valueOf(order.getPrice()));
        orderDTO.setPlacingDate(String.valueOf(order.getPlacingDate()));
        orderDTO.setPlacingTime(String.valueOf(order.getPlacingTime()));
        for (Food food : order.getFoodList()) {
            foodList.add(convertFoodToDTO(food));
        }
        orderDTO.setFoodList(foodList);
        orderDTO.setCustomer(order.getCustomer().getUsername());
        orderDTO.setRestaurant(order.getRestaurant().getName());
        return orderDTO;
    }

    public Order convertOrderFromDTO(OrderDTO orderDTO) {
        Order order = new Order();

        order.setStatus(Status.valueOf(orderDTO.getStatus()));
        order.setPrice(Integer.valueOf(orderDTO.getPrice()));
        order.setPlacingDate(LocalDate.parse(orderDTO.getPlacingDate()));
        order.setPlacingTime(LocalTime.parse(orderDTO.getPlacingTime()));

        return order;
    }

    public CustomerDTO convertCustomerToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        customerDTO.setId(String.valueOf(customer.getId()));
        customerDTO.setName(customer.getName());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setPassword(customer.getPassword());
        for (Order order : customer.getOrders()) {
            orderDTOList.add(convertOrderToDTO(order));
        }
        customerDTO.setOrders(orderDTOList);
        return customerDTO;
    }
}
