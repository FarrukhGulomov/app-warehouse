package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Output;
import uz.pdp.online.appwarehouse.entity.OutputProduct;
import uz.pdp.online.appwarehouse.entity.Product;
import uz.pdp.online.appwarehouse.payload.OutputProductDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.InputProductRepository;
import uz.pdp.online.appwarehouse.repository.OutputProductRepository;
import uz.pdp.online.appwarehouse.repository.OutputRepository;
import uz.pdp.online.appwarehouse.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class OutputProductService {
    OutputProductRepository outputProductRepository;
    OutputRepository outputRepository;
    ProductRepository productRepository;
    InputProductRepository inputProductRepository;

    public OutputProductService(OutputProductRepository outputProductRepository, OutputRepository outputRepository, ProductRepository productRepository, InputProductRepository inputProductRepository) {
        this.outputProductRepository = outputProductRepository;
        this.outputRepository = outputRepository;
        this.productRepository = productRepository;
        this.inputProductRepository = inputProductRepository;
    }

    public Result output(OutputProductDto outputProductDto){
        Double inputProductTotalAmount = inputProductRepository.getInputProductTotalAmount(outputProductDto.getProductId());

        if(outputProductDto.getAmount()> inputProductTotalAmount) return new Result("Output amoun is greater than input",false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if(optionalOutput.isEmpty()) return new Result("Output is not found!",false);

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty()) return new Result("Product is not found!",false);

        OutputProduct outputProduct=new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        Timestamp date=new Timestamp(System.currentTimeMillis());
        outputProduct.setExpireDate(date);
        outputProductRepository.save(outputProduct);

        return new Result("Successfully added!",true);
    }
}
