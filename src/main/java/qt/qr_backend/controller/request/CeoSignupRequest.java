package qt.qr_backend.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
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

    public CeoSignupRequest(String name, String mobileNumber, String loginId, String password,
                            String bank, String accountNumber, String email) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.loginId = loginId;
        this.password = password;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.email = email;
    }
}
