package qt.qr_backend.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Store;

@Repository
@RequiredArgsConstructor
public class StoreRepository {
    private final EntityManager em;

    public void save(Store store) {
        em.persist(store);
    }
}
