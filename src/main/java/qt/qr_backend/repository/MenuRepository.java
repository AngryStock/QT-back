package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.MenuOption;
import qt.qr_backend.domain.OrderMenu;

import java.util.List;


@Repository
public interface MenuRepository extends JpaRepository<Menu,String> {
    List<Menu> findByCategory_Id(String categoryId);

    @Query("select m from Menu m join fetch m.category c join fetch c.store s join fetch s.ceo ce where m.id= :menuId")
    Menu findNoProxyMenuById(String menuId);

}
