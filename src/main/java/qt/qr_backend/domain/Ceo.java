package qt.qr_backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Slf4j
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Getter @Setter
public class Ceo extends DateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ceo_id")
    private String id;

    @Column(name = "ceo_name")
    private String name;
    private String mobileNumber;
    private String loginId;
    private String password;
    private String role;
    private String accountNumber;
    private String email;
    private String bank;

    private String businessReportCertificateFileUrl;
    private String businessRegistrationFileUrl;
    private String copyOfBankbookFileUrl;

    public Ceo(String name, String mobileNumber, String loginId, String role, String bank,
               String accountNumber,
               String email, String businessReportCertificateFileUrl,
               String businessRegistrationFileUrl, String copyOfBankbookFileUrl) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.loginId = loginId;
        this.role = role;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.email = email;
        this.businessReportCertificateFileUrl = businessReportCertificateFileUrl;
        this.businessRegistrationFileUrl = businessRegistrationFileUrl;
        this.copyOfBankbookFileUrl = copyOfBankbookFileUrl;
    }

    public void encodePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
