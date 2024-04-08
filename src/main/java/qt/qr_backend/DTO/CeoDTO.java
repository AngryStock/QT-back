package qt.qr_backend.DTO;

import lombok.*;
import qt.qr_backend.domain.Ceo;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CeoDTO {
    private String id;
    private String name;
    private String mobileNumber;
    private String loginId;
    private String password;
    private String role;
    private String accountNumber;
    private String email;
    private String bank;
    private String businessReportCertificateFileUrl; // 사업자 영업 신고증
    private String businessRegistrationFileUrl; // 사업자 등록증
    private String copyOfBankbookFileUrl;

    public static Ceo fromCeoDTOtoCeo(CeoDTO ceo) {
        return Ceo.builder()
                .id(ceo.id)
                .name(ceo.name)
                .mobileNumber(ceo.mobileNumber)
                .loginId(ceo.loginId)
                .password(ceo.password)
                .role(ceo.role)
                .accountNumber(ceo.accountNumber)
                .email(ceo.email)
                .bank(ceo.bank)
                .businessReportCertificateFileUrl(ceo.businessReportCertificateFileUrl)
                .businessRegistrationFileUrl(ceo.businessRegistrationFileUrl)
                .copyOfBankbookFileUrl(ceo.copyOfBankbookFileUrl)
                .build();
    }

    public static CeoDTO fromCeotoCeoDTO(Ceo ceo) {
        return CeoDTO.builder()
                .id(ceo.getId())
                .name(ceo.getName())
                .mobileNumber(ceo.getMobileNumber())
                .loginId(ceo.getLoginId())
                .password(ceo.getPassword())
                .role(ceo.getRole())
                .accountNumber(ceo.getAccountNumber())
                .email(ceo.getEmail())
                .bank(ceo.getBank())
                .businessReportCertificateFileUrl(ceo.getBusinessReportCertificateFileUrl())
                .businessRegistrationFileUrl(ceo.getBusinessRegistrationFileUrl())
                .copyOfBankbookFileUrl(ceo.getCopyOfBankbookFileUrl())
                .build();
    }
}
