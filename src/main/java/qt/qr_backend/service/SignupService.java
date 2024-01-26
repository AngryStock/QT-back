package qt.qr_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Transactional
    public void signup(CeoSignupRequest ceoRequest, StoreSignupRequest storeRequest) {

        Ceo ceo = new Ceo(ceoRequest.getName(), ceoRequest.getMobileNumber(), ceoRequest.getLoginId(),
                ceoRequest.getPassword(), ceoRequest.getIsAdmin(), ceoRequest.getBank(), ceoRequest.getAccountNumber(),
                ceoRequest.getEmail());
        ceoRepository.save(ceo);

        Store store = new Store(ceo, storeRequest.getName(), storeRequest.getPhoneNumber(),
                storeRequest.getMainAddress(),
                storeRequest.getSubAddress(), storeRequest.getBusinessNumber(), storeRequest.getTableCount(),
                storeRequest.getApproval());
        storeRepository.save(store);
    }
}
