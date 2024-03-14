package qt.qr_backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CeoJwtDTO {
    private String loginId;
    private String password;
    private String role;
}
