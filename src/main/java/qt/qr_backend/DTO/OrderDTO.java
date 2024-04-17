package qt.qr_backend.DTO;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Order;
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
    private int orderPrice;
    private String tableId;


    public Order toOrder(StoreRepository repository){
        return Order.builder()
                .store(repository.getReferenceById(storeId))
                .orderDate(orderDate)
                .status(status)
                .orderPrice(orderPrice)
                .tableId(tableId)
                .build();
    }

//    public static Order fromOrderDTOtoOrder(OrderDTO orderDTO){
//        return Order.builder()
//                .id(orderDTO.id)
//                .store(StoreDTO.fromStoreDTOtoStore(orderDTO.store))
//                .orderDate(orderDTO.orderDate)
//                .status(orderDTO.status)
//                .orderPrice(orderDTO.orderPrice)
//                .tableId(orderDTO.tableId)
//                .build();
//    }

    public static OrderDTO fromOrdertoOrderDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .storeId(order.getStore().getId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .orderPrice(order.getOrderPrice())
                .tableId(order.getTableId())
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
