package qt.qr_backend.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.DTO.IdFindDTO;
import qt.qr_backend.DTO.PwFindDTO;

@Repository
@RequiredArgsConstructor
public class CeoRepository {

    private final EntityManager em;

    public void save(Ceo ceo) {
        em.persist(ceo);
    }

    public Ceo findById(String id) {
        return em.find(Ceo.class, id);
    }

    public Optional<Ceo> findByLoginId(String loginId) {
        List<Ceo> findCeo = em.createQuery("select c from Ceo c where c.loginId=:loginId", Ceo.class)
                .setParameter("loginId", loginId).getResultList();
        return findCeo.stream().findAny();
    }

    public Optional<Ceo> findByIdFindDTO(IdFindDTO idFindDTO) {
        List<Ceo> findCeo = em.createQuery("select c from Ceo c where c.name=:name and c.mobileNumber=:mobileNumber", Ceo.class)
                .setParameter("name", idFindDTO.getName())
                .setParameter("mobileNumber", idFindDTO.getMobileNumber())
                .getResultList();

        return findCeo.stream().findAny();
    }

    public Optional<Ceo> findByPwFindDTO(PwFindDTO pwFindDTO) {
        List<Ceo> findCeo = em.createQuery("select c from Ceo c where c.loginId=:loginId and c.name=:name and c.email=:email", Ceo.class)
                .setParameter("loginId", pwFindDTO.getLoginId())
                .setParameter("name", pwFindDTO.getName())
                .setParameter("email", pwFindDTO.getEmail())
                .getResultList();

        return findCeo.stream().findAny();
    }
}
