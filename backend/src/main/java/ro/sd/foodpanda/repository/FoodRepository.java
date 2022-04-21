package ro.sd.foodpanda.repository;

import ro.sd.foodpanda.model.Food;

public interface FoodRepository extends AbstractRepository<Food> {

    Food findFoodByName(String name);
}
