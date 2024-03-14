package qt.qr_backend.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.repository.CeoRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CeoRepository ceoRepository;

    //현재 로그인된 사업자 정보 반환
    @GetMapping("/currentCeo")
    public Ceo currentCeo(HttpServletResponse response) {
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Ceo> ceoData = ceoRepository.findByLoginId(loginId);

        Ceo ceo = ceoData.orElse(null);

        if(ceo == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return ceo;
    }

}
