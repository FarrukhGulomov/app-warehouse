package uz.pdp.online.appwarehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.appwarehouse.entity.Category;
import uz.pdp.online.appwarehouse.payload.CategoryDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Result addCategoryService(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);

    }
}
