package qt.qr_backend.DTO;



import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.OrderMenu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuDTO {
    private String id;
    private OrderDTO order;
    private MenuDTO menu;
    private int orderMenuPrice;

    public OrderMenuDTO(OrderDTO order,MenuDTO menu, int orderMenuPrice) {
        this.order = order;
        this.menu = menu;
        this.orderMenuPrice = orderMenuPrice;
    }

    public OrderMenuDTO setOrderDTOMap(OrderDTO order,OrderMenuDTO orderMenuDTO) {
        orderMenuDTO.setOrder(order);
        return orderMenuDTO;
    }

    public OrderMenu toOrderMenu(){
        return OrderMenu.builder()
                .order(OrderDTO.fromOrderDTOtoOrder(order))
                .menu(MenuDTO.fromMenuDTOtoMenu(menu))
                .orderMenuPrice(orderMenuPrice)
                .build();
    }

    public static OrderMenu fromOrderMenuDTOtoOrderMenu(OrderMenuDTO orderMenuDTO){
        return OrderMenu.builder()
                .id(orderMenuDTO.id)
                .order(OrderDTO.fromOrderDTOtoOrder(orderMenuDTO.order))
                .menu(MenuDTO.fromMenuDTOtoMenu(orderMenuDTO.menu))
                .orderMenuPrice(orderMenuDTO.orderMenuPrice)
                .build();
    }

    public static OrderMenuDTO fromOrderMenutoOrderMenuDTO(OrderMenu orderMenu){
        return OrderMenuDTO.builder()
                .id(orderMenu.getId())
                .order(OrderDTO.fromOrdertoOrderDTO(orderMenu.getOrder()))
                .menu(MenuDTO.fromMenutoMenuDTO(orderMenu.getMenu()))
                .orderMenuPrice(orderMenu.getOrderMenuPrice())
                .build();
    }
    public static List<OrderMenuDTO> listFromOrderMenutoOrderMenuDTO(List<OrderMenu> list){
        return list.stream()
                .map(OrderMenuDTO::fromOrderMenutoOrderMenuDTO)
                .toList();
    }
    public static List<OrderMenu> listFromOrderMenuDTOtoOrderMenu(List<OrderMenuDTO> list){
        return list.stream()
                .map(OrderMenuDTO::fromOrderMenuDTOtoOrderMenu)
                .toList();
    }

}
