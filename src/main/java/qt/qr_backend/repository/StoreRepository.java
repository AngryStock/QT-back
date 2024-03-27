package qt.qr_backend.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.domain.Store;

@Repository
@RequiredArgsConstructor
public class StoreRepository {
    private final EntityManager em;

    public void save(Store store) {
        em.persist(store);
    }

    public Store findById(String id) {
        return em.find(Store.class, id);
    }

    public List<Store> findByBusinessNumber(String businessNumber) {
        return em.createQuery("select s from Store s where s.businessNumber = :businessNumber", Store.class)
                .setParameter("businessNumber", businessNumber)
                .getResultList();
    }

    // 모든 Store 정보 조회 - AdminService
    public List<Store> findAll() {
        return em.createQuery("select s from Store s", Store.class).getResultList();
    }
}
