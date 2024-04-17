package qt.qr_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qt.qr_backend.DTO.AdminViewDTO;
import qt.qr_backend.domain.Store;
import qt.qr_backend.domain.enums.Approval;
import qt.qr_backend.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final StoreRepository storeRepository;
    private final EmailService emailService;

    public AdminService(StoreRepository storeRepository, EmailService emailService) {
        this.storeRepository = storeRepository;
        this.emailService = emailService;
    }

    // 회원가입 신청 정보 조회
    public List<AdminViewDTO> listAllJoinInfos() {
        List<Store> joinInfos = storeRepository.findAll();
        return joinInfos.stream()
                .map(store -> AdminViewDTO.from(store))
                .collect(Collectors.toList());
    }

    // 승인
    @Transactional
    public void approveJoin(String storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            throw new RuntimeException("Store not found");
        }

        store.setApproval(Approval.APPROVE);
        storeRepository.save(store);

        // 승인 이메일 발송
        String to = store.getCeo().getEmail();
        String subject = "회원가입 승인 안내";
        String contact = "문의 전회번호: 000-0000-0000, 이메일: qrtabler@gmail.com";

        emailService.sendApproveMail(to, subject, contact);
    }

    // 거부
    @Transactional
    public void rejectJoin(String storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            throw new RuntimeException("Store not found");
        }

        store.setApproval(Approval.REJECT);
        storeRepository.save(store);

        // 거부 이메일 발송
        String to = store.getCeo().getEmail();
        String subject = "회원가입 거절 안내";
        String contact = "문의 전회번호: 000-0000-0000, 이메일: qrtabler@gmail.com";

        emailService.sendRejectMail(to, subject, contact);
    }

}
