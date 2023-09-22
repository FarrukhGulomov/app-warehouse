package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.online.appwarehouse.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsProductByNameAndCategoryId(String name, Integer categoryId);

    @Query(value = "SELECT max(p.id) FROM product p", nativeQuery = true)
    Integer getMaxId();

    @Query(value = "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p JOIN InputProduct i_p ON p.id = i_p.product.id WHERE p.id=:id")
    boolean existsProductInputProduct(Integer id);
    @Query(value = "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p JOIN OutputProduct o_p" +
            " on p.id = o_p.product.id WHERE p.id=:id")
    boolean existsProductInOutputProduct(Integer id);
}
