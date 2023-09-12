package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Client;
import uz.pdp.online.appwarehouse.entity.Supplier;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByPhoneNumberAndName(String phoneNumber, String name);

    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Client c JOIN Output o ON c.id = o.client.id WHERE c.id= :id")
    boolean existsClientInOutputId(Integer id);
}
