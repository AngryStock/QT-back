package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Order;
import qt.qr_backend.domain.OrderMenu;

import java.util.List;


@Repository

public interface OrderMenuRepository extends JpaRepository<OrderMenu,String> {
    List<OrderMenu> findByOrder_Id(String orderId);
    List<OrderMenu> findByMenu_Id(String menuId);

}
