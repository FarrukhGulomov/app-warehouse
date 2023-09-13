package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
//    @Query(value = "SELECT CASE WHEN (SUM(i_p.amount) - o_p.amount) >= 0 THEN TRUE ELSE FALSE END AS isAmountPositive \n" +
//            "FROM input_product i_p \n" +
//            "JOIN product p ON i_p.product_id = p.id \n" +
//            "JOIN output_product o_p ON p.id = o_p.product_id \n" +
//            "WHERE p.id = :productId GROUP BY o_p.amount",nativeQuery = true)
//    Boolean isAmountPositive(Integer productId);

    @Query(value = "SELECT COALESCE(SUM(i_p.amount), 0) FROM input_product i_p\n" +
            "WHERE i_p.product_id = :productId",nativeQuery = true)
    Double getInputProductTotalAmount(Integer productId);




}
