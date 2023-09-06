package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Category;
import uz.pdp.online.appwarehouse.payload.CategoryDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Result addCategory(CategoryDto  categoryDto){
        Boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if(existsByName) return new Result("This category is already exist!",false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        if(categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if(optionalParentCategory.isEmpty()) return new Result(" Parent category is not found!",false);
            Category parentCategory = optionalParentCategory.get();
            category.setParentCategory(parentCategory);
            categoryRepository.save(category);
        }

        categoryRepository.save(category);
        return new Result("Category is added!",true);

    }

}
