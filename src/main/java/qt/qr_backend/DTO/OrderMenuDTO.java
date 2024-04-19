package qt.qr_backend.DTO;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.exOrder.OrderMenu;
import qt.qr_backend.repository.MenuRepository;
import qt.qr_backend.repository.OrderRepository;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuDTO {
    private String id;
    private String orderId;
    private String menuId;
    private int orderMenuPrice;

    public OrderMenuDTO(String orderId,String menuId, int orderMenuPrice) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.orderMenuPrice = orderMenuPrice;
    }

    public OrderMenuDTO setOrderDTOMap(OrderDTO order,OrderMenuDTO orderMenuDTO) {
        orderMenuDTO.setOrderId(order.getId());
        return orderMenuDTO;
    }

    public OrderMenu toOrderMenu(OrderRepository orderRepository, MenuRepository menuRepository){
        return OrderMenu.builder()
                .order(orderRepository.getReferenceById(orderId))
                .menu(menuRepository.getReferenceById(menuId))
                .orderMenuPrice(orderMenuPrice)
                .build();
    }

//    public static OrderMenu fromOrderMenuDTOtoOrderMenu(OrderMenuDTO orderMenuDTO){
//        return OrderMenu.builder()
//                .id(orderMenuDTO.id)
//                .order(OrderDTO.fromOrderDTOtoOrder(orderMenuDTO.order))
//                .menu(MenuDTO.fromMenuDTOtoMenu(orderMenuDTO.menu))
//                .orderMenuPrice(orderMenuDTO.orderMenuPrice)
//                .build();
//    }

    public static OrderMenuDTO fromOrderMenutoOrderMenuDTO(OrderMenu orderMenu){
        return OrderMenuDTO.builder()
                .id(orderMenu.getId())
                .orderId(orderMenu.getOrder().getId())
                .menuId(orderMenu.getMenu().getId())
                .orderMenuPrice(orderMenu.getOrderMenuPrice())
                .build();
    }
    public static List<OrderMenuDTO> listFromOrderMenutoOrderMenuDTO(List<OrderMenu> list){
        return list.stream()
                .map(OrderMenuDTO::fromOrderMenutoOrderMenuDTO)
                .toList();
    }
//    public static List<OrderMenu> listFromOrderMenuDTOtoOrderMenu(List<OrderMenuDTO> list){
//        return list.stream()
//                .map(OrderMenuDTO::fromOrderMenuDTOtoOrderMenu)
//                .toList();
//    }

}
