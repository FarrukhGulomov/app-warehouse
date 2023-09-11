package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
   boolean existsByName(String name);


}
