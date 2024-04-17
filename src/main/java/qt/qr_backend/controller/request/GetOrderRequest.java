package qt.qr_backend.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import qt.qr_backend.DTO.OrderMenuRequest;

import java.util.List;


@Data
@AllArgsConstructor
public class GetOrderRequest {
    private String tableId;
    private String orderId;
    private String storeId;
    private List<OrderMenuRequest> list;
}
