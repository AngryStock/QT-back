package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.OptionCategory;

import java.util.List;


@Repository

public interface OptionCategoryRepository extends JpaRepository<OptionCategory,String> {
    List<OptionCategory> findByMenu_Id(String menuId);

    @Query("select oc from OptionCategory oc join fetch oc.menu m join fetch m.category c join fetch c.store s join fetch s.ceo ce where oc.id= :optionCategoryId")
    OptionCategory findNoProxyOptionCategoryById(String optionCategoryId);
}
