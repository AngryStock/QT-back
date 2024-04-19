package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Cart;
import qt.qr_backend.domain.Category;

import java.util.List;


@Repository

public interface CartRepository extends JpaRepository<Cart,String> {
    List<Cart> findByTableNoAndStoreId(String tableNo, String storeId);
}
