package ro.sd.foodpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sd.foodpanda.DTO.FoodDTO;
import ro.sd.foodpanda.model.Food;
import ro.sd.foodpanda.service.FoodService;

import java.util.List;

@RestController
public class FoodController {

    @Autowired
    FoodService foodService;

    @PostMapping(value = "/addfood")
    public String newFood(@RequestBody Food food) {
        System.out.println("Aici");
        return foodService.save(food);
    }

    @GetMapping("/foods")
    public List<FoodDTO> allFoods() {
        return foodService.findAll();
    }

    @GetMapping("/foods/{name}")
    public FoodDTO findFood(@PathVariable String name) {
        return foodService.findByName(name);
    }

    @GetMapping("/foods/category/{name}")
    public List<FoodDTO> foodsInCategory(@PathVariable String name) {
        return foodService.findFoodsInCategory(name);
    }
}
