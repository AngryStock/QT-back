package qt.qr_backend.controller.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MenuResponse {
    private int statusCode;
    private String message;
}
