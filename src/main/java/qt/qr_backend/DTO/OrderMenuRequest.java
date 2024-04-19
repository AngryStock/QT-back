package qt.qr_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuRequest {
    private String menuId;
    private int orderMenuPrice;
    private int amount;
    private List<OrderMenuOptionDTO> orderMenuOptionDTOList;

}
