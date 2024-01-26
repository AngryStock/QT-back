package qt.qr_backend.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CeoSignupRequest {
    @NotEmpty
    private String name;

    @NotEmpty
    private String mobileNumber;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    private Boolean isAdmin;

    @NotEmpty
    private String bank;

    @NotEmpty
    private String accountNumber;

    @NotEmpty
    @Email
    private String email;
}
