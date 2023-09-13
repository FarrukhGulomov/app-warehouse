package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output,Integer> {

    @Query(value = "SELECT MAX (o.id) FROM Output o")
    Integer getMaxId();

    @Query(value = "SELECT CASE WHEN COUNT (o) >0 THEN TRUE ELSE FALSE END FROM Output o JOIN OutputProduct o_p ON o.id = o_p.output.id WHERE o.id =:id")
    boolean existsOutputInOutputProduct(Integer id);
}
