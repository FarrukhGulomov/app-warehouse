package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT MAX(u.id) FROM users u", nativeQuery = true)
    Integer getMaxId();

    @Query(value = "select case when count (u)>0 then true else false end from users u join users_warehouses u_w " +
            "on u.id = u_w.users_id where u.id=:id", nativeQuery = true)
    boolean existsUserInUserWarehouse(Integer id);

}