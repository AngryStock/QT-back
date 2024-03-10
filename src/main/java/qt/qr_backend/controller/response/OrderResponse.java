package qt.qr_backend.controller.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderResponse {
    private int statusCode;
    private String message;
}
