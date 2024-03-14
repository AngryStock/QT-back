package qt.qr_backend.DTO;

import lombok.*;
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
    private String role;
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
                .role(ceo.role)
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
                .role(ceo.getRole())
                .accountNumber(ceo.getAccountNumber())
                .email(ceo.getEmail())
                .bank(ceo.getBank())
                .build();
    }
}
