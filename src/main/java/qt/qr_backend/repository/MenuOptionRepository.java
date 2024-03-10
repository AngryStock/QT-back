package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.MenuOption;

import java.util.List;


@Repository
public interface MenuOptionRepository extends JpaRepository<MenuOption,String> {
    List<MenuOption> findByMenu_Id(String menuId);
}
