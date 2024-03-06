package qt.qr_backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Entity
@NoArgsConstructor
@Getter
public class Ceo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ceo_id")
    private Long id;

    @Column(name = "ceo_name")
    private String name;
    private String mobileNumber;
    private String loginId;
    private String password;
    private boolean isAdmin;
    private String accountNumber;
    private String email;
    private String bank;


    public Ceo(String name, String mobileNumber, String loginId, boolean isAdmin, String bank,
               String accountNumber,
               String email) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.loginId = loginId;
        this.isAdmin = isAdmin;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.email = email;
    }

    public void encodePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
