package qt.qr_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.controller.request.OwnerIdRequest;
import qt.qr_backend.controller.request.SignupRequest;
import qt.qr_backend.controller.response.SignupResponse;
import qt.qr_backend.service.SignupService;

import java.util.*;

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

    @GetMapping("/available/ownerId")
    public String isAvailable(@RequestBody OwnerIdRequest ownerIdRequest) {
        String ownerId = ownerIdRequest.getOwnerId();
        return signupService.validateDuplicateLoginIdV2(ownerId);
    }

    //어드민 계정 포스트맨 테스트용
    @GetMapping("/admin")
    public String adminP() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();

        Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = collection.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "test admin page : " + name + " / " + role;
    }
}
