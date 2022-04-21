package ro.sd.foodpanda.DTO;

import java.util.List;

public class CategoryDTO {

    private String name;
    private List<FoodDTO> foodList;

    public CategoryDTO() {
    }

    public CategoryDTO(String name, List<FoodDTO> foodList) {
        this.name = name;
        this.foodList = foodList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodDTO> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodDTO> foodList) {
        this.foodList = foodList;
    }
}
