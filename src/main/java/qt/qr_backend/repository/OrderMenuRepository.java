package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.exOrder.OrderMenu;

import java.util.List;


@Repository

public interface OrderMenuRepository extends JpaRepository<OrderMenu,String> {
    List<OrderMenu> findByOrder_Id(String orderId);
    List<OrderMenu> findByMenu_Id(String menuId);

    @Query("select om from OrderMenu om join fetch om.menu m join fetch om.order o where om.id= :orderMenuId")
    OrderMenu findNoProxyOrderMenuById(String orderMenuId);

}
