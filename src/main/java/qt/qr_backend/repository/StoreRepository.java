package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Store;

import java.util.List;
import java.util.Optional;


@Repository
public interface StoreRepository extends JpaRepository<Store,String> {

    Optional<Store> findById(String id);

    List<Store> findByCeo_Id(String ceoId);

    @Query("select c from Category c join fetch c.store s join fetch s.ceo ce where c.id= :categoryId")
    Category findNoProxyCategoryById(String categoryId);

    List<Store> findByBusinessNumber(String businessNumber);

    // 모든 Store 정보 조회 - AdminService
    @Query("select s from Store s")
    List<Store> findAll();
}
