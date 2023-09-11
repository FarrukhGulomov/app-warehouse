package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Category;
import uz.pdp.online.appwarehouse.payload.CategoryDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Result addCategory(CategoryDto categoryDto) {
        Boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName) return new Result("This category is already exist!", false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (optionalParentCategory.isEmpty()) return new Result(" Parent category is not found!", false);
            Category parentCategory = optionalParentCategory.get();
            category.setParentCategory(parentCategory);
            categoryRepository.save(category);
        }

        categoryRepository.save(category);
        return new Result("Category is added!", true);

    }

    public Result getCategoryList() {
        return new Result("", true, categoryRepository.findAll());
    }

    public Result getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) return new Result("Category is not found!", false);
        Category category = optionalCategory.get();
        return new Result("Success", true, category);
    }

    public List<Category> getAllParentCategory() {
        return categoryRepository.getAllParentCategory();
    }

    public Result editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) return new Result("Category is not found by this id", false);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Category parentCategory = category.getParentCategory();
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            optionalParentCategory.ifPresent(value -> parentCategory.setName(value.getName())); // pastdagi bn bir xil
            //if(optionalParentCategory.isPresent()) parentCategory.setName(optionalParentCategory.get().getName());

        }
        categoryRepository.save(category);
        return new Result("Category is successfully edited!", true, category);


    }

    public Result deleteCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) return new Result("Category is not found",false);

        Category category = optionalCategory.get();
        boolean existsParent = categoryRepository.existsByParentCategoryId(id);
        if(existsParent) return new Result("This is parent category you can't delete just now!",false);

        categoryRepository.deleteById(id);
        return new Result("Category by name :"+optionalCategory.get().getName()+"is successfully deleted",true);


    }
}
