package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwarehouse.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {
}
