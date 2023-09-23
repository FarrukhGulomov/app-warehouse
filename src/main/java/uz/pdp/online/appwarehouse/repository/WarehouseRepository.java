package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    boolean existsByName(String name);

@Query(value = "SELECT CASE WHEN COUNT (w) > 0 THEN TRUE ELSE  FALSE END FROM warehouse w " +
        "JOIN input i on w.id = i.warehouse_id where w.id= :id",nativeQuery = true)
    boolean existsWarehouseInInput(Integer id);

    @Query(value = "SELECT CASE WHEN COUNT (w) > 0 THEN TRUE ELSE  FALSE END FROM warehouse w " +
            "JOIN output o on w.id = o.warehouse_id where w.id= :id",nativeQuery = true)
    boolean existsWarehouseInOutput(Integer id);

    @Query(value = "SELECT CASE WHEN COUNT (w) > 0 THEN TRUE ELSE  FALSE END FROM warehouse w " +
            "JOIN users_warehouses u_w on w.id = u_w.warehouses_id where w.id= :id",nativeQuery = true)
    boolean existsWarehouseInUsersWarehouses(Integer id);

}