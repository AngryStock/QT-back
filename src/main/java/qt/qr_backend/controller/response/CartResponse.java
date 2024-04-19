package qt.qr_backend.controller.response;


import lombok.*;
import qt.qr_backend.DTO.CartDTO;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class CartResponse {
    private String type;
    private CartDTO cartDTO;
    private String cartId;
    private int amount;
    private String message;
    private int statusCode;

    public CartResponse(String add, CartDTO cartDTO) {
        this.type = add;
        this.cartDTO = cartDTO;
    }

    public CartResponse(String set, String cartId, int amount) {
        this.type = set;
        this.cartId = cartId;
        this.amount = amount;
    }

    public CartResponse(String del, String cartId) {
        this.type = del;
        this.cartId = cartId;
    }

    public CartResponse(String allDel) {
        this.type = allDel;
    }
}
