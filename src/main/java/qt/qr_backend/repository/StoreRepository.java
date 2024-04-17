package qt.qr_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Store;

import java.util.List;


@Repository

public interface StoreRepository extends JpaRepository<Store,String> {
    List<Store> findByCeo_Id(String ceoId);

    @Query("select c from Category c join fetch c.store s join fetch s.ceo ce where c.id= :categoryId")
    Category findNoProxyCategoryById(String categoryId);

    public List<Store> findByBusinessNumber(String businessNumber) {
        return em.createQuery("select s from Store s where s.businessNumber = :businessNumber", Store.class)
                .setParameter("businessNumber", businessNumber)
                .getResultList();
    }

    // 모든 Store 정보 조회 - AdminService
    public List<Store> findAll() {
        return em.createQuery("select s from Store s", Store.class).getResultList();
    }
    List<Store> findByBusinessNumber(String businessNumber);
}
