package uz.pdp.online.appwarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Product;
import uz.pdp.online.appwarehouse.payload.ProductDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping
    Page<Product> getAllProductByPage(@RequestParam Integer page){
        return productService.getAllProductByPage(page);
    }
    @GetMapping("/productById/{id}")
    public Result getProduct(@PathVariable Integer id){
       return productService.getProduct(id);
    }
    @PutMapping("/edit/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody ProductDto dto){
       return productService.editProduct(id,dto);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return productService.delete(id);
    }
}

