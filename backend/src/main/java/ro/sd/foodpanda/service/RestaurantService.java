package ro.sd.foodpanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.foodpanda.DTO.AdminDTO;
import ro.sd.foodpanda.DTO.CategoryDTO;
import ro.sd.foodpanda.DTO.RestaurantDTO;
import ro.sd.foodpanda.model.Admin;
import ro.sd.foodpanda.model.Category;
import ro.sd.foodpanda.model.Restaurant;
import ro.sd.foodpanda.model.Zone;
import ro.sd.foodpanda.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private CategoryService categoryService;

    private Mappers mappers = new Mappers();

    public Restaurant save(Restaurant restaurant) {
        Restaurant toSaveRestaurant = restaurant;
        List<Zone> zoneList = new ArrayList<>();
        Admin admin = adminService.findAdminByUsername(restaurant.getAdmin().getUsername());
        for (Zone zone : restaurant.getZones()) {
            zoneList.add(zoneService.findByName(zone.getName()));
        }
        toSaveRestaurant.setAdmin(admin);
        toSaveRestaurant.setZones(zoneList);
        return restaurantRepository.save(toSaveRestaurant);
    }

    public Restaurant findByName(String name) {
        return restaurantRepository.findRestaurantByName(name);
    }

    public List<CategoryDTO> findCategories(String name) {
        Restaurant restaurant = findByName(name);
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : restaurant.getCategories()) {
            categoryDTOList.add(mappers.convertCategoryToDTO(category));
        }
        return categoryDTOList;
    }

    public List<RestaurantDTO> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        Iterable<Restaurant> all = restaurantRepository.findAll();
        all.forEach(restaurants::add);
        for (Restaurant restaurant : restaurants) {
            restaurantDTOList.add(mappers.convertRestaurantToDTO(restaurant));
        }
        return restaurantDTOList;
    }

    public Restaurant updateCategories(String restaurantName, Category category) {
        Restaurant initialRestaurant = findByName(restaurantName);
        List<Category> categoryList = initialRestaurant.getCategories();
        Category toAddCategory = categoryService.findByName(category.getName());
        categoryList.add(toAddCategory);
        initialRestaurant.setCategories(categoryList);
        return restaurantRepository.save(initialRestaurant);
    }
}
