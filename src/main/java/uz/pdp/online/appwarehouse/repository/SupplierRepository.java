package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.appwarehouse.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    boolean existsByPhoneNumberAndName(String phoneNumber,String name);
}
