package qt.qr_backend.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Ceo;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CeoDTO {
    private String id;
    private String name;
    private String mobileNumber;
    private String loginId;
    private String password;
    private boolean isAdmin;
    private String accountNumber;
    private String email;
    private String bank;

    public static Ceo fromCeoDTOtoCeo(CeoDTO ceo) {
        return Ceo.builder()
                .id(ceo.id)
                .name(ceo.name)
                .mobileNumber(ceo.mobileNumber)
                .loginId(ceo.loginId)
                .password(ceo.password)
                .isAdmin(ceo.isAdmin)
                .accountNumber(ceo.accountNumber)
                .email(ceo.email)
                .bank(ceo.bank)
                .build();
    }

    public static CeoDTO fromCeotoCeoDTO(Ceo ceo) {
        return CeoDTO.builder()
                .id(ceo.getId())
                .name(ceo.getName())
                .mobileNumber(ceo.getMobileNumber())
                .loginId(ceo.getLoginId())
                .password(ceo.getPassword())
                .isAdmin(ceo.isAdmin())
                .accountNumber(ceo.getAccountNumber())
                .email(ceo.getEmail())
                .bank(ceo.getBank())
                .build();
    }
}
