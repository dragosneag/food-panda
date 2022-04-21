package ro.sd.foodpanda.repository;

import ro.sd.foodpanda.model.Restaurant;

public interface RestaurantRepository extends AbstractRepository<Restaurant> {

    Restaurant findRestaurantByName(String name);
}
