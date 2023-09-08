package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
//    @Query(value = "SELECT CASE c.name  FROM Currency c THEN TRUE ELSE FALSE WHERE c.name= :name ")
//    Boolean hasCurrencyName(String name);
}
