package qt.qr_backend.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import qt.qr_backend.controller.request.CeoSignupRequest;
import qt.qr_backend.controller.request.StoreSignupRequest;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.domain.Store;
import qt.qr_backend.domain.enums.Approval;
import qt.qr_backend.repository.CeoRepository;
import qt.qr_backend.repository.StoreRepository;

@SpringBootTest
@Transactional
class SignupServiceTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CeoRepository ceoRepository;

    @Autowired
    StoreRepository storeRepository;

    @Test
    @DisplayName("회원가입")
    void signup() {
        // given
        CeoSignupRequest ceoRequest = new CeoSignupRequest("김민정", "01011112222", "test12", "test1234!", false, "농협은행",
                "1102312992012",
                "aa@aa.com");

        StoreSignupRequest storeRequest = new StoreSignupRequest("탐앤탐스", "0422223423", "대전시 유성구 장대동", "탐앤탐스 장대점",
                "1111122222", 20, Approval.BEFORE);

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

        // when
        Ceo findCeo = ceoRepository.findById(ceo.getId());
        Store findStore = storeRepository.findById(store.getId());

        // then
        Assertions.assertThat(findCeo.getId()).isEqualTo(ceo.getId());
        Assertions.assertThat(findCeo).isEqualTo(ceo);
        Assertions.assertThat(findStore.getCeo()).isEqualTo(findCeo);
    }
}