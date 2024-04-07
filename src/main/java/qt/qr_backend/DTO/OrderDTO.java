package qt.qr_backend.DTO;



import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Order;
import qt.qr_backend.domain.Store;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private StoreDTO storeDTO;
    private LocalDateTime orderDate;
    private String status;
    private int orderPrice;


    public Order toOrder(){
        return Order.builder()
                .store(StoreDTO.fromStoreDTOtoStore(storeDTO))
                .orderDate(orderDate)
                .status(status)
                .orderPrice(orderPrice)
                .build();
    }

    public static Order fromOrderDTOtoOrder(OrderDTO orderDTO){
        return Order.builder()
                .id(orderDTO.id)
                .store(StoreDTO.fromStoreDTOtoStore(orderDTO.storeDTO))
                .orderDate(orderDTO.orderDate)
                .status(orderDTO.status)
                .orderPrice(orderDTO.orderPrice)
                .build();
    }

    public static OrderDTO fromOrdertoOrderDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .storeDTO(StoreDTO.fromStoretoStoreDTO(order.getStore()))
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .orderPrice(order.getOrderPrice())
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
