package qt.qr_backend.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CeoImagesUrlResponse {
    private String businessReportCertificateFileUrl; // 영업 신고증
    private String businessRegistrationFileUrl; // 사업자 등록증
    private String copyOfBankbookFileUrl; // 통장 사본
}
