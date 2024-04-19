
package qt.qr_backend.DTO;


import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Cart;
import qt.qr_backend.domain.converter.StringListConverter;
import qt.qr_backend.repository.MenuRepository;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private String id;
    private String tableNo;
    private String storeId;
    private String menuId;
    private String name;
    private String img;
    private List<String> options;
    private int price;
    private int amount;

    public Cart toCart(){
        return Cart.builder()
                .tableNo(tableNo)
                .storeId(storeId)
                .menuId(menuId)
                .name(name)
                .img(img)
                .options(options)
                .price(price)
                .amount(amount)
                .build();
    }

    public static Cart fromCartDTOtoCart(CartDTO cartDTO){
        return Cart.builder()
                .id(cartDTO.id)
                .tableNo(cartDTO.tableNo)
                .storeId(cartDTO.storeId)
                .menuId(cartDTO.menuId)
                .name(cartDTO.name)
                .img(cartDTO.img)
                .options(cartDTO.options)
                .price(cartDTO.price)
                .amount(cartDTO.amount)
                .build();
    }

    public static CartDTO fromCarttoCartDTO(Cart cart){
        return CartDTO.builder()
                .id(cart.getId())
                .tableNo(cart.getTableNo())
                .storeId(cart.getStoreId())
                .menuId(cart.getMenuId())
                .name(cart.getName())
                .img(cart.getImg())
                .options(cart.getOptions())
                .price(cart.getPrice())
                .amount(cart.getAmount())
                .build();
    }
    public static List<CartDTO> listFromCarttoCartDTO(List<Cart> list){
        return list.stream()
                .map(CartDTO::fromCarttoCartDTO)
                .toList();
    }
}
