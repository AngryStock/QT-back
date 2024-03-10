package qt.qr_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qt.qr_backend.controller.response.FindPwResponse;
import qt.qr_backend.dto.IdFindDTO;
import qt.qr_backend.dto.PwFindDTO;
import qt.qr_backend.service.IdPwFindService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IdPwFindController {
    private final IdPwFindService idPwFindService;

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
}
