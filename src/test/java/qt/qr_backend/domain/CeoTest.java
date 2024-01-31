package qt.qr_backend.domain;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import qt.qr_backend.repository.CeoRepository;

@SpringBootTest
@Transactional
class CeoTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CeoRepository ceoRepository;

    @Test
    @DisplayName("저장된 비밀번호와 입력된 비밀번호의 일치 여부 검증")
    void matchPassword() {
        String loginId = "test12";
        // given
        Ceo ceo1 = new Ceo("홍길동", "01011112222", loginId, false, "농협은행",
                "1102312992012",
                "aa@aa.com");
        ceo1.encodePassword(passwordEncoder, "test1234!");
        ceoRepository.save(ceo1);

        // when
        Optional<Ceo> findCeo = ceoRepository.findByLoginId(loginId);

        // then
        findCeo.ifPresentOrElse((ceo) -> {
            String findPassword = ceo.getPassword();
            Assertions.assertThat(ceo.matchPassword(passwordEncoder, "test1234!", findPassword)).isTrue();
            Assertions.assertThat(ceo.matchPassword(passwordEncoder, "test1234", findPassword)).isFalse();
        }, () -> {
            Assertions.fail("사장님을 찾을 수 없습니다.");
        });
    }
}