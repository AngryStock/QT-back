package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Category;

import java.util.List;


@Repository

public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findByStore_Id(String storeId);

    @Query("select c from Category c join fetch c.store s join fetch s.ceo ce where c.id= :categoryId")
    Category findNoProxyCategoryById(String categoryId);
}
