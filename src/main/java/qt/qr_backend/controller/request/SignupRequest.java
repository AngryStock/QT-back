package qt.qr_backend.controller.request;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class SignupRequest {
    @Valid
    private CeoSignupRequest ceo;

    @Valid
    private StoreSignupRequest store;
}
