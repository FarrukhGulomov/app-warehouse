package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Attachment;
import uz.pdp.online.appwarehouse.entity.Category;
import uz.pdp.online.appwarehouse.entity.Measurement;
import uz.pdp.online.appwarehouse.entity.Product;
import uz.pdp.online.appwarehouse.payload.ProductDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.AttachmentRepository;
import uz.pdp.online.appwarehouse.repository.CategoryRepository;
import uz.pdp.online.appwarehouse.repository.MeasurementRepository;
import uz.pdp.online.appwarehouse.repository.ProductRepository;
import uz.pdp.online.appwarehouse.service.helper.Operation;

import java.util.Optional;

@Service
public class ProductService extends Operation {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    MeasurementRepository measurementRepository;
    AttachmentRepository attachmentRepository;


    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, MeasurementRepository measurementRepository, AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.measurementRepository = measurementRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public Result addProduct(ProductDto productDto) {
        boolean existsProductByName = productRepository.existsProductByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsProductByName) return new Result("Product is already exist!", false);
        Product product = new Product();
        product.setName(productDto.getName());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) return new Result("Category is not found!", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()) return new Result("Measurement is not found!", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty()) return new Result("Photo is not found!", false);

        product.setCode(getCode(productRepository.getMaxId()));
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Product is added!", true);

    }




}
