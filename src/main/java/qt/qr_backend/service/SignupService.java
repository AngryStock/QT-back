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
import qt.qr_backend.domain.enums.Approval;
import qt.qr_backend.exception.DuplicateException;
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
        validateDuplicateLoginId(ceoRequest.getLoginId());
        Ceo ceo = new Ceo(ceoRequest.getName(), ceoRequest.getMobileNumber(), ceoRequest.getLoginId(),
                "ROLE_USER", ceoRequest.getBank(), ceoRequest.getAccountNumber(),
                ceoRequest.getEmail());
        ceo.encodePassword(passwordEncoder, ceoRequest.getPassword());
        ceoRepository.save(ceo);
        log.info("Ceo 생성 완료");

        log.info("Store 생성 시작");
        validateDuplicateBusinessNumber(storeRequest.getBusinessNumber());
        Store store = new Store(ceo, storeRequest.getName(), storeRequest.getPhoneNumber(),
                storeRequest.getMainAddress(),
                storeRequest.getDetailAddress(), storeRequest.getBusinessNumber(), storeRequest.getTableCount(),
                Approval.BEFORE);
        storeRepository.save(store);
        log.info("Store 생성 완료");
    }

    private void validateDuplicateLoginId(String loginId) {
        boolean existLoginId = ceoRepository.findByLoginId(loginId)
                .stream().findAny().isPresent();
        if (existLoginId) {
            throw new DuplicateException("이미 가입된 아이디입니다.");
        }
    }

    private void validateDuplicateBusinessNumber(String businessNumber) {
        boolean existBusinessNumber = storeRepository.findByBusinessNumber(businessNumber)
                .stream().findAny().isPresent();
        if (existBusinessNumber) {
            throw new DuplicateException("이미 가입된 사업자등록번호입니다.");
        }
    }
}
