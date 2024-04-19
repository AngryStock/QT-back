package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.exOrder.OrderMenuOption;



@Repository

public interface OrderMenuOptionRepository extends JpaRepository<OrderMenuOption,String> {

}
