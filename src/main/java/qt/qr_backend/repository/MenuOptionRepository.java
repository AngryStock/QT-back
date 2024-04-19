package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.MenuOption;

import java.util.List;


@Repository
public interface MenuOptionRepository extends JpaRepository<MenuOption,String> {
    List<MenuOption> findByOptionCategory_Id(String optionCategoryId);

    @Query("select mo from MenuOption mo join fetch mo.optionCategory oc join fetch oc.menu m join fetch m.category c join fetch c.store s join fetch s.ceo ce where mo.id= :menuOptionId")
    MenuOption findNoProxyMenuOptionById(String menuOptionId);

}
