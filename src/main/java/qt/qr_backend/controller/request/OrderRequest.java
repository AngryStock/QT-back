package qt.qr_backend.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import qt.qr_backend.DTO.OrderMenuDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequest {
    private String orderId;
    private String table;
    private int price;
    private List<OrderMenuDTO> menus;
    private String storeId;
    private String status;
}
