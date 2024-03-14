package qt.qr_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qt.qr_backend.controller.response.FindPwResponse;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.DTO.IdFindDTO;
import qt.qr_backend.DTO.PwChangeDTO;
import qt.qr_backend.DTO.PwFindDTO;
import qt.qr_backend.exception.ErrorResponse;
import qt.qr_backend.repository.CeoRepository;
import qt.qr_backend.service.IdPwFindService;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IdPwFindController {

    private final IdPwFindService idPwFindService;
    private final CeoRepository ceoRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/findId")
    public ResponseEntity<String> findId(IdFindDTO idFindDTO) {
        log.info("idFindDto = name: {}", idFindDTO.getName());
        log.info("idFindDto = mobileNumber: {}", idFindDTO.getMobileNumber());
        String result = idPwFindService.findByIdFindDTO(idFindDTO);
        if(result == null) {
            return new ResponseEntity<>("아이디가 없거나 올바르지 않은 정보를 입력하였습니다.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/findPassword")
    public FindPwResponse findPassword(PwFindDTO pwFindDTO) {
        //계정이 있을 경우, 임시 비밀번호 발급
        String result = idPwFindService.findByPwFindDTO(pwFindDTO);
        if(result == null) {
            return new FindPwResponse(false, "계정이 없거나 정보를 잘못 입력하였습니다.");
        }

        return new FindPwResponse(true, result);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(PwChangeDTO pwChangeDTO) {

        // 변경할 비밀번호와 비밀번호 확인 값이 일치하지 않을 경우
        if (!pwChangeDTO.getPostPassword().equals(pwChangeDTO.getCheckPassword())) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), "비밀번호 확인이 변경할 비밀번호와 일치하지 않습니다.");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
        }

        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Ceo> ceoData = ceoRepository.findByLoginId(loginId);
        Ceo ceo = ceoData.orElseThrow();

        // 현재 비밀번호가 db에 저장된 비밀번호와 일치하는지 확인 후 로직 수행
        if(passwordEncoder.matches(pwChangeDTO.getCurrentPassword(), ceo.getPassword())) {
            idPwFindService.changePassword(ceo, pwChangeDTO.getPostPassword());
            return new ResponseEntity<>("비밀번호가 변경되었습니다.",HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "현재 비밀번호를 올바르게 입력해주세요.");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

}
