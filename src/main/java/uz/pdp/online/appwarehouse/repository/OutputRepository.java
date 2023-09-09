package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output,Integer> {

    @Query(value = "SELECT MAX (o.id) FROM Output o")
    Integer getMaxId();
}
