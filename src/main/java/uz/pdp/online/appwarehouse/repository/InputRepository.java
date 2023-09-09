package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input,Integer> {

    @Query(value = "SELECT MAX(i.id) FROM input i",nativeQuery = true)
    Integer getMaxId();
}
