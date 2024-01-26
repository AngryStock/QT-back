package qt.qr_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qt.qr_backend.controller.request.SignupRequest;
import qt.qr_backend.controller.response.SignupResponse;
import qt.qr_backend.service.SignupService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignupService signupService;

    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody @Valid SignupRequest signupRequest) {
        signupService.signup(signupRequest.getCeo(), signupRequest.getStore());
        log.info("서비스 로직 성공");
        return new SignupResponse(200, "회원가입이 완료되었습니다.");
    }
}
