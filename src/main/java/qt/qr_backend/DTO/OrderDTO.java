package qt.qr_backend.DTO;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.exOrder.Order;
import qt.qr_backend.repository.StoreRepository;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String storeId;
    private LocalDateTime orderDate;
    private String status;
    private int price;
    private String tableId;

    public OrderDTO(String storeId, LocalDateTime date, String wait, int price, String tableId) {
        this.storeId = storeId;
        this.orderDate = date;
        this.status = wait;
        this.price = price;
        this.tableId = tableId;
    }


    public Order toOrder(){
        return Order.builder()
                .storeId(storeId)
                .orderDate(orderDate)
                .status(status)
                .price(price)
                .tableNo(tableId)
                .build();
    }

    public static Order fromOrderDTOtoOrder(OrderDTO orderDTO){
        return Order.builder()
                .id(orderDTO.id)
                .storeId(orderDTO.getStoreId())
                .orderDate(orderDTO.orderDate)
                .status(orderDTO.status)
                .price(orderDTO.price)
                .tableNo(orderDTO.tableId)
                .build();
    }

    public static OrderDTO fromOrdertoOrderDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .storeId(order.getStoreId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .price(order.getPrice())
                .tableId(order.getTableNo())
                .build();
    }
    public static List<OrderDTO> listFromOrdertoOrderDTO(List<Order> list){
        return list.stream()
                .map(OrderDTO::fromOrdertoOrderDTO)
                .toList();
    }

    public void changeStatus(String status){
        this.status = status;
    }

}
