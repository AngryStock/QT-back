package qt.qr_backend.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import qt.qr_backend.DTO.CartDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class CartRequest {
    private String type;
    private CartDTO cartDTO;
    private String cartId;
    private int amount;
}
