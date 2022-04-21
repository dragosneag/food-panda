package ro.sd.foodpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sd.foodpanda.DTO.CategoryDTO;
import ro.sd.foodpanda.DTO.RestaurantDTO;
import ro.sd.foodpanda.model.Category;
import ro.sd.foodpanda.model.Restaurant;
import ro.sd.foodpanda.service.RestaurantService;

import java.util.List;
import java.util.Map;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping(value = "/addrestaurant")
    public Restaurant newRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant);
    }

    @GetMapping("/restaurants/{name}")
    public Restaurant searchRestaurant(@PathVariable String name) {
        return restaurantService.findByName(name);
    }

    @GetMapping("/restaurants/{name}/categories")
    public List<CategoryDTO> getCategories(@PathVariable String name) {
        return restaurantService.findCategories(name);
    }

    @GetMapping("/restaurants")
    public List<RestaurantDTO> allRestaurants() {
        return restaurantService.findAll();
    }

    @PutMapping("/restaurants/addcategory/{name}")
    public Restaurant addCategory(@PathVariable String name, @RequestBody Category category) {
        System.out.println("Aici " + name + " " + category.getName());
        return restaurantService.updateCategories(name, category);
    }
}
