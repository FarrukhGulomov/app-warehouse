package uz.pdp.online.appwarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Input;
import uz.pdp.online.appwarehouse.entity.InputProduct;
import uz.pdp.online.appwarehouse.entity.Product;
import uz.pdp.online.appwarehouse.payload.InputProductDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.InputProductRepository;
import uz.pdp.online.appwarehouse.repository.InputRepository;
import uz.pdp.online.appwarehouse.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    InputProductRepository inputProductRepository;
    InputRepository inputRepository;
    ProductRepository productRepository;

    public InputProductService(InputProductRepository inputProductRepository, InputRepository inputRepository, ProductRepository productRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepository = inputRepository;
        this.productRepository = productRepository;
    }

    public Result addInputProduct(InputProductDto inputProductDto) {
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()) return new Result("Input id not found", false);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()) return new Result("Product is not found!", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setAmount(inputProductDto.getAmount());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        inputProduct.setExpireDate(date);
        System.out.println(inputProduct.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("InputProduct is added!", true);
    }

    public List<InputProduct> getAllInputProducts() {
        return inputProductRepository.findAll();
    }

    public Page<InputProduct> getInputProductByPage( int page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        return inputProductRepository.findAll(pageable);
    }

    public InputProduct getInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(optionalInputProduct.isPresent()) return optionalInputProduct.get();
        return new InputProduct();
    }

    public void outputInputAmount(Double outputAmount, Integer productId){
        Double inputProductTotalAmount = inputProductRepository.getInputProductTotalAmount(productId);
        if (inputProductTotalAmount==null) {
            new Result("This id is not found!", false);
            return;
        }
        inputProductTotalAmount-=outputAmount;
        InputProduct inputProduct=new InputProduct(inputProductTotalAmount);
        inputProductRepository.save(inputProduct);
        new Result("Succesfully done!", true);

    }
}
