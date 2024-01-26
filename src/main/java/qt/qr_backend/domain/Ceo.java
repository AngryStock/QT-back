package qt.qr_backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Ceo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Ceo(String name, String mobileNumber, String loginId, String password, boolean isAdmin, String bank, String accountNumber,
               String email) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.loginId = loginId;
        this.password = password;
        this.isAdmin = isAdmin;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.email = email;
    }
}
