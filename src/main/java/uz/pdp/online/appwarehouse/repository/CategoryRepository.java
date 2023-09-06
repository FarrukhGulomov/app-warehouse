package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwarehouse.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Boolean existsByName(String name);
}
