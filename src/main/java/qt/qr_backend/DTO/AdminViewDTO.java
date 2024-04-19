package qt.qr_backend.DTO;

import lombok.Getter;
import lombok.Setter;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.domain.Store;
import qt.qr_backend.domain.enums.Approval;

import java.time.LocalDateTime;

@Getter @Setter
public class AdminViewDTO {

    // Store
    private String storeId; // id
    private String storeName; // name = 상호명
    private String phoneNumber; // 업체 전화번호
    private String mainAddress; // 주소
    private String detailAddress; // 상세주소
    private String businessNumber; // 사업자등록번호
    private int tableCount;
    private Approval approval;

    // Ceo
    private String ceoId; // id
    private String ceoName; // name = 대표자명
    private String mobileNumber; // 대표자 휴대폰번호
    private String loginId; // 아이디
    private String password; // 비밀번호
    private String role;
    private String accountNumber; // 계좌번호
    private String email; // 이메일
    private String bank; // 은행
    private String businessReportCertificateFileUrl; //사업자등록증
    private String businessRegistrationFileUrl; // 영업신고증
    private String copyOfBankbookFileUrl; // 통장사본
    private LocalDateTime createAt; // 신청일시

    // 정적 팩토리 메서드
    public static AdminViewDTO from(Store store) {

        AdminViewDTO adminViewDTO = new AdminViewDTO();
        Ceo ceo = store.getCeo();

        adminViewDTO.setStoreId(store.getId());
        adminViewDTO.setStoreName(store.getName());
        adminViewDTO.setPhoneNumber(store.getPhoneNumber());
        adminViewDTO.setMainAddress(store.getMainAddress());
        adminViewDTO.setDetailAddress(store.getDetailAddress());
        adminViewDTO.setApproval(store.getApproval());
        adminViewDTO.setCreateAt(store.getCreatedAt());

        if (ceo != null) {
            adminViewDTO.setCeoId(ceo.getId());
            adminViewDTO.setCeoName(ceo.getName());
            adminViewDTO.setMobileNumber(ceo.getMobileNumber());
            adminViewDTO.setLoginId(ceo.getLoginId());
            adminViewDTO.setEmail(ceo.getEmail());
            adminViewDTO.setBank(ceo.getBank());
            adminViewDTO.setAccountNumber(ceo.getAccountNumber());
            adminViewDTO.setBusinessReportCertificateFileUrl(ceo.getBusinessReportCertificateFileUrl());
            adminViewDTO.setBusinessRegistrationFileUrl(ceo.getBusinessRegistrationFileUrl());
            adminViewDTO.setCopyOfBankbookFileUrl(ceo.getCopyOfBankbookFileUrl());
        }

        return adminViewDTO;
    }
}
