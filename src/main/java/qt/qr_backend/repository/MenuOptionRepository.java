package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.MenuOption;
import qt.qr_backend.domain.OrderMenu;

import java.util.List;


@Repository
public interface MenuOptionRepository extends JpaRepository<MenuOption,String> {
    List<MenuOption> findByMenu_Id(String menuId);

    @Query("select mo from MenuOption mo join fetch mo.menu m join fetch m.category c join fetch c.store s  join fetch s.ceo ce where mo.id= :menuOptionId")
    MenuOption findNoProxyMenuOptionById(String menuOptionId);

}
