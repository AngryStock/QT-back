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

    public CartRequest(String add, CartDTO cartDTO) {
        this.type = add;
        this.cartDTO = cartDTO;
    }

    public CartRequest(String set, String cartId, int amount) {
        this.type = set;
        this.cartId = cartId;
        this.amount= amount;
    }

    public CartRequest(String del, String cartId) {
        this.type = del;
        this.cartId = cartId;
    }

    public CartRequest(String allDel) {
        this.type = allDel;
    }
}
