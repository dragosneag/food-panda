package ro.sd.foodpanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.foodpanda.DTO.CategoryDTO;
import ro.sd.foodpanda.DTO.FoodDTO;
import ro.sd.foodpanda.model.Category;
import ro.sd.foodpanda.model.Food;
import ro.sd.foodpanda.model.Restaurant;
import ro.sd.foodpanda.model.User;
import ro.sd.foodpanda.repository.FoodRepository;
import ro.sd.foodpanda.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryService categoryService;

    private Mappers mappers = new Mappers();

    public String save(Food food) {
        Restaurant restaurant = restaurantService.findByName(food.getRestaurant().getName());
        Category category = categoryService.findByName(food.getCategory().getName());
        if (restaurant == null) {
            return "Restaurant is null!";
        }
        if (category == null) {
            return "Category is null!";
        }
        food.setRestaurant(restaurant);
        food.setCategory(category);
        foodRepository.save(food);
        return "Success";
    }

    public FoodDTO findByName(String name) {
        Food food = foodRepository.findFoodByName(name);
        return mappers.convertFoodToDTO(food);
    }

    public Food findFoodByName(String name) {
        return foodRepository.findFoodByName(name);
    }

    public List<FoodDTO> findAll() {
        List<Food> foodList = new ArrayList<>();
        List<FoodDTO> dtoList = new ArrayList<>();
        Iterable<Food> all = foodRepository.findAll();
        all.forEach(foodList::add);
        for (Food food : foodList) {
            dtoList.add(mappers.convertFoodToDTO(food));
        }
        return dtoList;
    }

    public List<FoodDTO> findFoodsInCategory(String name) {
        Category category = categoryService.findByName(name);
        return mappers.convertCategoryToDTO(category).getFoodList();
    }
}
