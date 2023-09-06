package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Product;
import uz.pdp.online.appwarehouse.payload.ProductDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.ProductRepository;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Result addProduct(ProductDto productDto){
        boolean existsProductByName = productRepository.existsProductByNameAndCategoryId(productDto.getName(),productDto.getCategoryId());
        if(existsProductByName) return new Result("Product is already exist!",false);
        Product product=new Product();
        product.setName(productDto.getName());


    }
}
