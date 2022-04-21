package ro.sd.foodpanda.repository;

import ro.sd.foodpanda.model.Category;

public interface CategoryRepository extends AbstractRepository<Category> {

    Category findCategoryByName(String name);
}
