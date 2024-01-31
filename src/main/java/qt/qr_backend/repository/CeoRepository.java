package qt.qr_backend.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
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

    public Ceo findById(Long id) {
        return em.find(Ceo.class, id);
    }

    public Optional<Ceo> findByLoginId(String loginId) {
        List<Ceo> findCeo = em.createQuery("select c from Ceo c where c.loginId=:loginId", Ceo.class)
                .setParameter("loginId", loginId).getResultList();
        return findCeo.stream().findAny();
    }
}
