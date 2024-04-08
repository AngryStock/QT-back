package qt.qr_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.OrderMenuOption;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuOptionDTO {
    private String id;
    private OrderMenuDTO orderMenu;
    private MenuOptionDTO menuOption;

    public OrderMenuOptionDTO(MenuOptionDTO menuOption) {
        this.menuOption = menuOption;
    }

    public OrderMenuOptionDTO(OrderMenuDTO orderMenu, MenuOptionDTO menuOption) {
        this.orderMenu = orderMenu;
        this.menuOption = menuOption;
    }


    public OrderMenuOption toOrderMenuOption(){
        return OrderMenuOption.builder()
                .orderMenu(OrderMenuDTO.fromOrderMenuDTOtoOrderMenu(orderMenu))
                .menuOption(MenuOptionDTO.fromMenuOptionDTOtoMenuOption(menuOption))
                .build();
    }

    public static OrderMenuOption fromOrderMenuOptionDTOtoOrderMenuOption(OrderMenuOptionDTO orderMenuOptionDTO){
        return OrderMenuOption.builder()
                .id(orderMenuOptionDTO.id)
                .orderMenu(OrderMenuDTO.fromOrderMenuDTOtoOrderMenu(orderMenuOptionDTO.orderMenu))
                .menuOption(MenuOptionDTO.fromMenuOptionDTOtoMenuOption(orderMenuOptionDTO.menuOption))
                .build();
    }

    public static OrderMenuOptionDTO fromOrderMenuOptiontoOrderMenuOptionDTO(OrderMenuOption orderMenuOption){
        return OrderMenuOptionDTO.builder()
                .id(orderMenuOption.getId())
                .orderMenu(OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenuOption.getOrderMenu()))
                .menuOption(MenuOptionDTO.fromMenuOptiontoMenuOptionDTO(orderMenuOption.getMenuOption()))
                .build();
    }
    public static List<OrderMenuOptionDTO> listFromOrderMenuOptiontoOrderMenuOptionDTO(List<OrderMenuOption> list){
        return list.stream()
                .map(OrderMenuOptionDTO::fromOrderMenuOptiontoOrderMenuOptionDTO)
                .toList();
    }
    public static List<OrderMenuOption> listFromOrderMenuOptionDTOtoOrderMenuOption(List<OrderMenuOptionDTO> list){
        return list.stream()
                .map(OrderMenuOptionDTO::fromOrderMenuOptionDTOtoOrderMenuOption)
                .toList();
    }
}
