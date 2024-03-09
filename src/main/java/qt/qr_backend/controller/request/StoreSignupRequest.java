package qt.qr_backend.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import qt.qr_backend.annotation.ValidEnum;
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

    //@ValidEnum(enumClass = Approval.class)
    private Approval approval;

    public StoreSignupRequest(String name, String phoneNumber, String mainAddress, String detailAddress,
                              String businessNumber, int tableCount, Approval approval) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
        this.businessNumber = businessNumber;
        this.tableCount = tableCount;
        this.approval = approval;
    }
}

