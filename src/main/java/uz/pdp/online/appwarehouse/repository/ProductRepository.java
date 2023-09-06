package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwarehouse.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsProductByNameAndCategoryId(String name,Integer categoryId);
}
