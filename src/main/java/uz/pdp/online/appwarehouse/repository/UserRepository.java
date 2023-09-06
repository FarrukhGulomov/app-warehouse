package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT MAX(u.id) FROM users u",nativeQuery = true)
    Integer getMaxId();
}
