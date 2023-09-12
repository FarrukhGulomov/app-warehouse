package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input,Integer> {

    @Query(value = "SELECT MAX(i.id) FROM input i",nativeQuery = true)
    Integer getMaxId();

    @Query(value = "SELECT CASE WHEN COUNT (i) > 0 THEN TRUE ELSE FALSE END FROM" +
            " input i JOIN input_product i_p ON i.id = i_p.input_id WHERE i.id = :id",nativeQuery = true)
    boolean existsInputProductInInput(Integer id);


}
