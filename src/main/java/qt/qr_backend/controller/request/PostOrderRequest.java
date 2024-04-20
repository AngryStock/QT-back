package qt.qr_backend.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import qt.qr_backend.DTO.OrderMenuDTO;
import qt.qr_backend.DTO.OrderMenuRequest;

import java.util.List;


@Data
@AllArgsConstructor
public class PostOrderRequest {
    private String status;
    private String orderId;
    private String tableId;
    //private List<OrderMenuDTO> orderMenuDTOList;
}
