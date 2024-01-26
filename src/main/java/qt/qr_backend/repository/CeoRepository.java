package qt.qr_backend.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Ceo;

@Repository
@RequiredArgsConstructor
public class CeoRepository {

    private final EntityManager em;

    public void save(Ceo ceo) {
        em.persist(ceo);
    }
}
