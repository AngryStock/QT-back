package qt.qr_backend.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CeoSignupRequest {
    @NotEmpty
    private String name;

    @NotEmpty
    @Pattern(regexp = "^(01[0,1])\\d{7,8}$", message = "올바른 형식의 핸드폰 번호를 입력해주세요.")
    private String mobileNumber;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @NotEmpty
    private String bank;

    @NotEmpty
    private String accountNumber;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String businessReportCertificateFileUrl;

    @NotEmpty
    private String businessRegistrationFileUrl;

    @NotEmpty
    private String copyOfBankbookFileUrl;
}
