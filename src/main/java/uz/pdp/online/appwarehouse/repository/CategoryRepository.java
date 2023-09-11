package uz.pdp.online.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.appwarehouse.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Boolean existsByName(String name);

    @Query(value = "SELECT * FROM category WHERE parent_category_id IS NULL",nativeQuery = true)
    List<Category> getAllParentCategory();

    boolean existsByParentCategoryId(Integer id);
}
