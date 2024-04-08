package qt.qr_backend.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qt.qr_backend.controller.request.SignupRequest;
import qt.qr_backend.controller.request.SignupTestRequest;
import qt.qr_backend.controller.request.StoreSignupRequest;
import qt.qr_backend.controller.response.CeoImagesUrlResponse;
import qt.qr_backend.controller.response.SignupResponse;
import qt.qr_backend.exception.ErrorResponse;
import qt.qr_backend.service.FileHandler;
import qt.qr_backend.service.SignupService;

import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignupService signupService;
    private final FileHandler fileHandler;

    //공사중
    //사업자 등록증, 영업 신고증, 통장 사본 이미지 파일 form으로 옴
    @PostMapping("/ceoImages")
    public ResponseEntity<Object> saveCeoImages(
            @RequestParam MultipartFile businessReportCertificateFile,
            @RequestParam MultipartFile businessRegistrationFile,
            @RequestParam MultipartFile copyOfBankbookFile) throws IOException {


        String businessReportCertificateFileUrl = fileHandler.parseFileInfo(businessReportCertificateFile);
        String businessRegistrationFileUrl = fileHandler.parseFileInfo(businessRegistrationFile);
        String copyOfBankbookFileUrl = fileHandler.parseFileInfo(copyOfBankbookFile);

        // Url 값이 null일 경우 파일을 첨부하지 않고 보낸 것이므로 에러를 응답해야함
        if(businessReportCertificateFileUrl == null || businessRegistrationFileUrl == null
                || copyOfBankbookFileUrl == null) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "파일을 첨부하지 않았습니다");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        log.info("businessReportCertificateFileUrl = {}", businessReportCertificateFileUrl);
        log.info("businessRegistrationFileUrl = {}", businessRegistrationFileUrl);
        log.info("copyOfBankbookFileUrl = {}", copyOfBankbookFileUrl);

        CeoImagesUrlResponse ceoImagesUrlResponse = new CeoImagesUrlResponse(businessReportCertificateFileUrl, businessRegistrationFileUrl, copyOfBankbookFileUrl);

        return new ResponseEntity<>(ceoImagesUrlResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody @Valid SignupRequest signupRequest) {
        signupService.signup(signupRequest.getCeo(), signupRequest.getStore());
        log.info("서비스 로직 성공");
        return new SignupResponse(200, "회원가입이 완료되었습니다.");
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
