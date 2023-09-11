package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwarehouse.entity.Client;
import uz.pdp.online.appwarehouse.entity.Supplier;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    boolean existsByPhoneNumberAndName(String phoneNumber,String name);


}
