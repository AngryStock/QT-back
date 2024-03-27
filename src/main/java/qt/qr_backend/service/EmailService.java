package qt.qr_backend.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendRejectMail(String to, String subject, String contact) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setFrom("qrtabler@gmail.com"); // 발신자 메일
            helper.setTo(to); // 수신자 메일
            helper.setSubject(subject); // 메일 제목

            // 간단한 HTML 메일 본문
            String htmlMail = "<h3>회원 가입이 거절 되었습니다.</h3>"
                    + "<p>추가 정보가 필요하신 경우 아래의 연락처로 문의해주세요.</p>"
                    + "<p>" + contact + "</p>";
            helper.setText(htmlMail, true); // true를 설정하여 HTML 형식 지정

            emailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
