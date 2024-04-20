package qt.qr_backend.controller.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import qt.qr_backend.DTO.OrderMenuDTO;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderResponse {
    private String type;
    private String orderId;
    private String storeId;
    private LocalDateTime orderDate;
    private String status;
    private int price;
    private String table;
    private List<OrderMenuDTO> menus;
    private int statusCode;
    private String message;

    public OrderResponse(String order, String id, String storeId, LocalDateTime orderDate,String status, int price, String tableId, List<OrderMenuDTO> orderMenuDTOS) {
        this.type = order;
        this.orderId = id;
        this.storeId = storeId;
        this.orderDate = orderDate;
        this.status = status;
        this.price = price;
        this.table = tableId;
        this.menus = orderMenuDTOS;
    }

    public OrderResponse(int i, String deleteOk) {
        this.statusCode = i;
        this.message = deleteOk;
    }
}
