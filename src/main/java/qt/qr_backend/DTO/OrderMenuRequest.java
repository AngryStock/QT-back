package qt.qr_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.OrderMenu;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuRequest {
    private OrderDTO orderDTO;
    private MenuDTO menuDTO;
    private int orderMenuPrice;
    private List<OrderMenuOptionDTO> orderMenuOptionDTOList;

}
