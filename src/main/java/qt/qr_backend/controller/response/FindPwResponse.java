package qt.qr_backend.controller.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindPwResponse {
    private boolean isSuccess;
    private String tempPassword;
}
