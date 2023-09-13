package uz.pdp.online.appwarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<OutputProduct> getOutputProductByPage(Integer page){
        int size=10;
        Pageable pageable= PageRequest.of(page,size);
        return outputProductRepository.findAll(pageable);
    }
    public Result getOutputProduct(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.map(outputProduct ->
                new Result("Successfully is found!", true, outputProduct))
                .orElseGet(() -> new Result("OutputProduct is not found!", false));
    }

    public Result editOutputProduct(Integer id,OutputProductDto dto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isEmpty()) new Result("OutputProduct is not found!", false);
        OutputProduct outputProduct = optionalOutputProduct.get();

        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        if(optionalProduct.isEmpty()) return new Result("Product is not found!",false);

        Optional<Output> optionalOutput = outputRepository.findById(dto.getOutputId());
        if(optionalOutput.isEmpty()) return new Result("Output is not found!",false);

        Double inputProductTotalAmount = inputProductRepository.getInputProductTotalAmount(dto.getProductId());
        if(inputProductTotalAmount<dto.getAmount()) return new Result("outputProduct is bigger then inputProduct!",false);
        outputProduct.setPrice(dto.getPrice());
        outputProduct.setAmount(dto.getAmount());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output is successfully edited!",true);
    }
    public Result delete(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isEmpty()) return new Result("outputProduct is  not found!",false);

        outputProductRepository.deleteById(id);
        return new Result("outputProduct is successfully deleted!",true);
    }
}
