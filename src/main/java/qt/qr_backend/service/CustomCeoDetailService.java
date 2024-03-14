package qt.qr_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.CeoDetails;
import qt.qr_backend.DTO.CeoJwtDTO;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.repository.CeoRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomCeoDetailService implements UserDetailsService {
    private final CeoRepository ceoRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Ceo> ceoData = ceoRepository.findByLoginId(loginId);
        Ceo ceo = ceoData.orElse(null);
        if (ceo != null) {
            CeoJwtDTO ceoJwtDTO = new CeoJwtDTO();
            ceoJwtDTO.setLoginId(ceo.getLoginId());
            ceoJwtDTO.setPassword(ceo.getPassword());
            ceoJwtDTO.setRole(ceo.getRole());
            return new CeoDetails(ceoJwtDTO);
        }

        return null;
    }
}
