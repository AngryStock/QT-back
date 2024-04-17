package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Order;

import java.util.List;


@Repository

public interface OrderRepository extends JpaRepository<Order,String> {
    List<Order> findByStore_Id(String storeId);

    @Query("select o from Order o join fetch o.store s join fetch s.ceo c where o.id= :orderId")
    Order findNoProxyOrderById(String orderId);
}
