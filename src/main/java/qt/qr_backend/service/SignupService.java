package qt.qr_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qt.qr_backend.controller.request.CeoSignupRequest;
import qt.qr_backend.controller.request.StoreSignupRequest;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.domain.Store;
import qt.qr_backend.repository.CeoRepository;
import qt.qr_backend.repository.StoreRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignupService {

    private final CeoRepository ceoRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(CeoSignupRequest ceoRequest, StoreSignupRequest storeRequest) {
        log.info("Ceo 생성 시작");
        Ceo ceo = new Ceo(ceoRequest.getName(), ceoRequest.getMobileNumber(), ceoRequest.getLoginId(),
                ceoRequest.getIsAdmin(), ceoRequest.getBank(), ceoRequest.getAccountNumber(),
                ceoRequest.getEmail());

        ceo.encodePassword(passwordEncoder, ceoRequest.getPassword());
        ceoRepository.save(ceo);

        Store store = new Store(ceo, storeRequest.getName(), storeRequest.getPhoneNumber(),
                storeRequest.getMainAddress(),
                storeRequest.getDetailAddress(), storeRequest.getBusinessNumber(), storeRequest.getTableCount(),
                storeRequest.getApproval());
        storeRepository.save(store);
    }
}
