package ro.sd.foodpanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.foodpanda.DTO.CategoryDTO;
import ro.sd.foodpanda.model.Category;
import ro.sd.foodpanda.model.Food;
import ro.sd.foodpanda.model.User;
import ro.sd.foodpanda.repository.CategoryRepository;
import ro.sd.foodpanda.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Mappers mappers = new Mappers();

    public String save(Category category) {
        categoryRepository.save(category);
        return "Success";
    }

    public List<CategoryDTO> findAll() {
        List<Category> categories = new ArrayList<>();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        Iterable<Category> all = categoryRepository.findAll();
        all.forEach(categories::add);
        for (Category category : categories) {
            categoryDTOList.add(mappers.convertCategoryToDTO(category));
        }
        return categoryDTOList;
    }

    public Category findByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }
}
