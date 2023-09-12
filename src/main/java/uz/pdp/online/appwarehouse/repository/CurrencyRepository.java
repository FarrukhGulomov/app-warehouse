package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    boolean existsByName(String name);

    @Query(value = "SELECT CASE WHEN COUNT (c) > 0 THEN TRUE ELSE FALSE END FROM currency c JOIN input i " +
            "ON c.id = i.currency_id WHERE c.id = :id ", nativeQuery = true)
    boolean existsCurrencyInInput(Integer id);

    @Query(value = "SELECT CASE WHEN COUNT (c) > 0 THEN TRUE ELSE FALSE END FROM currency c JOIN output o " +
            "ON c.id = o.currency_id WHERE c.id = :id",nativeQuery = true)
    boolean existsCurrencyInOutput(Integer id);
}
