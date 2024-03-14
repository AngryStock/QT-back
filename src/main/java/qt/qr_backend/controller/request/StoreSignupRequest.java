package qt.qr_backend.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import qt.qr_backend.annotation.ValidEnum;
import qt.qr_backend.domain.enums.Approval;

@Data
@AllArgsConstructor
public class StoreSignupRequest {
    @NotEmpty
    private String name;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String mainAddress;

    @NotEmpty
    private String detailAddress;

    @NotEmpty
    @Size(max = 10)
    private String businessNumber;

}

