package qt.qr_backend.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.domain.Store;
import qt.qr_backend.repository.CeoRepository;
import qt.qr_backend.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CeoRepository ceoRepository;
    private final StoreRepository storeRepository;

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

    @GetMapping("/currentStore")
    public List<Store> currentStore(HttpServletResponse response) {
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Ceo> ceoData = ceoRepository.findByLoginId(loginId);

        Ceo ceo = ceoData.orElse(null);

        if(ceo == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        List<Store> allStore = storeRepository.findAll();

        List<Store> currentStore = allStore.stream().filter(store -> store.getCeo().equals(ceo)).toList();

        return currentStore;
    }

}
