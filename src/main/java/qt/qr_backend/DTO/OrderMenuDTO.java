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
    private OrderDTO orderDTO;
    private MenuDTO menuDTO;
    private int orderMenuPrice;

    public OrderMenuDTO(OrderDTO orderDTO,MenuDTO menuDTO, int orderMenuPrice) {
        this.orderDTO = orderDTO;
        this.menuDTO = menuDTO;
        this.orderMenuPrice = orderMenuPrice;
    }

    public OrderMenuDTO setOrderDTOMap(OrderDTO orderDTO,OrderMenuDTO orderMenuDTO) {
        orderMenuDTO.setOrderDTO(orderDTO);
        return orderMenuDTO;
    }

    public OrderMenu toOrderMenu(){
        return OrderMenu.builder()
                .order(OrderDTO.fromOrderDTOtoOrder(orderDTO))
                .menu(MenuDTO.fromMenuDTOtoMenu(menuDTO))
                .orderMenuPrice(orderMenuPrice)
                .build();
    }

    public static OrderMenu fromOrderMenuDTOtoOrderMenu(OrderMenuDTO orderMenuDTO){
        return OrderMenu.builder()
                .id(orderMenuDTO.id)
                .order(OrderDTO.fromOrderDTOtoOrder(orderMenuDTO.orderDTO))
                .menu(MenuDTO.fromMenuDTOtoMenu(orderMenuDTO.menuDTO))
                .orderMenuPrice(orderMenuDTO.orderMenuPrice)
                .build();
    }

    public static OrderMenuDTO fromOrderMenutoOrderMenuDTO(OrderMenu orderMenu){
        return OrderMenuDTO.builder()
                .id(orderMenu.getId())
                .orderDTO(OrderDTO.fromOrdertoOrderDTO(orderMenu.getOrder()))
                .menuDTO(MenuDTO.fromMenutoMenuDTO(orderMenu.getMenu()))
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
