package uz.pdp.online.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Category;
import uz.pdp.online.appwarehouse.payload.CategoryDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Result addCategoryService(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);

    }

    @GetMapping
    public Result getAllCategory() {
        return categoryService.getCategoryList();
    }

    @GetMapping("/getCategoryById/{id}")
    public Result getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping(value = "/parentCategory/")
    public List<Category> getALlParentCategory(){
       return categoryService.getAllParentCategory();
    }


    @PutMapping("/editCategory/{id}")
    public Result editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto) {
        return categoryService.editCategory(id,categoryDto);
    }

    @DeleteMapping("deleteCategory/{id}")
    public Result deleteCategoryById(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }
}
