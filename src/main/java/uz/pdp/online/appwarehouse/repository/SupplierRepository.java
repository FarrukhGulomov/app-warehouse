package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.online.appwarehouse.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT CASE WHEN COUNT (s)>0 THEN TRUE ELSE FALSE END FROM supplier s" +
            " JOIN input i ON s.id = i.supplier_id WHERE s.id=:id",nativeQuery = true)
    boolean existsSupplierInInput(Integer id);
}
