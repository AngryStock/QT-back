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
    private String name;
    private List<String> options;
    private int amount;
    private String orderId;


    public OrderMenu toOrderMenu(){
        return OrderMenu.builder()
                .name(name)
                .options(options)
                .amount(amount)
                .order(null)
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
                .name(orderMenu.getName())
                .options(orderMenu.getOptions())
                .amount(orderMenu.getAmount())
                .orderId(orderMenu.getOrder().getId())
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
