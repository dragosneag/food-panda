package ro.sd.foodpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.sd.foodpanda.DTO.CategoryDTO;
import ro.sd.foodpanda.model.Category;
import ro.sd.foodpanda.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/addcategory")
    public String newCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> allCategories() {
        return categoryService.findAll();
    }
}
