package qt.qr_backend.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.OrderMenu;

import java.util.List;


@Repository

public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findByStore_Id(String storeId);

    @Query("select c from Category c join fetch c.store s join fetch s.ceo ce where c.id= :categoryId")
    Category findNoProxyCategoryById(String categoryId);
}
