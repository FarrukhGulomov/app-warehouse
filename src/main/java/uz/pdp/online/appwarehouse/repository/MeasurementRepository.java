package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwarehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    Boolean existsByName(String name);

}
