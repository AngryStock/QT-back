package qt.qr_backend.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import qt.qr_backend.domain.enums.Approval;

@Data
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

    private int tableCount;

    // enum validator 생성 필요
    private Approval approval;
}

