package qt.qr_backend.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.dto.IdFindDTO;
import qt.qr_backend.dto.PwFindDTO;
import qt.qr_backend.repository.CeoRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class IdPwFindService {
    private final CeoRepository ceoRepository;
    private final PasswordEncoder passwordEncoder;

    //계정이 있으면 아이디 반환. 아니면 null 반환.
    public String findByIdFindDTO(IdFindDTO idFindDTO) {
        Optional<Ceo> ceoData = ceoRepository.findByIdFindDTO(idFindDTO);
        Ceo ceo = ceoData.orElse(null);
        System.out.println("ceo = " + ceo);
        if (ceo == null) {
            return null;
        }

        return ceo.getLoginId();
    }

    //계정이 맞으면 임시 비밀번호 반환. 아니면 null 반환.
    public String findByPwFindDTO(PwFindDTO pwFindDTO) {
        Optional<Ceo> ceoData = ceoRepository.findByPwFindDTO(pwFindDTO);
        Ceo ceo = ceoData.orElse(null);
        if(ceo == null) {
            return null;
        }

        //임시 비밀번호 발급
        String newPwd = RandomStringUtils.randomAlphanumeric(8);

        //임시 비밀번호로 비밀번호 재설정 후 DB 저장
        ceo.setPassword(passwordEncoder.encode(newPwd));
        return newPwd;
    }


    // 비밀번호 변경
    public void changePassword(Ceo ceo, String password) {
        ceo.setPassword(passwordEncoder.encode(password));
    }
}
