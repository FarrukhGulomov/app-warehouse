package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    Boolean existsByName(String name);
    @Query(value = "SELECT CASE WHEN COUNT (m) > 0 THEN TRUE ELSE FALSE END FROM Measurement m JOIN Product p ON m.id = p.measurement.id WHERE m.id=:id")
boolean existsMeasurementInProduct(Integer id);
}
