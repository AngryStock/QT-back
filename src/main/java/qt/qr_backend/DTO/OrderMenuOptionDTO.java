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
    private OrderMenuDTO orderMenuDTO;
    private MenuOptionDTO menuOptionDTO;

    public OrderMenuOptionDTO(MenuOptionDTO menuOptionDTO) {
        this.menuOptionDTO = menuOptionDTO;
    }

    public OrderMenuOptionDTO(OrderMenuDTO orderMenuDTO, MenuOptionDTO menuOptionDTO) {
        this.orderMenuDTO = orderMenuDTO;
        this.menuOptionDTO = menuOptionDTO;
    }


    public OrderMenuOption toOrderMenuOption(){
        return OrderMenuOption.builder()
                .orderMenu(OrderMenuDTO.fromOrderMenuDTOtoOrderMenu(orderMenuDTO))
                .menuOption(MenuOptionDTO.fromMenuOptionDTOtoMenuOption(menuOptionDTO))
                .build();
    }

    public static OrderMenuOption fromOrderMenuOptionDTOtoOrderMenuOption(OrderMenuOptionDTO orderMenuOptionDTO){
        return OrderMenuOption.builder()
                .id(orderMenuOptionDTO.id)
                .orderMenu(OrderMenuDTO.fromOrderMenuDTOtoOrderMenu(orderMenuOptionDTO.orderMenuDTO))
                .menuOption(MenuOptionDTO.fromMenuOptionDTOtoMenuOption(orderMenuOptionDTO.menuOptionDTO))
                .build();
    }

    public static OrderMenuOptionDTO fromOrderMenuOptiontoOrderMenuOptionDTO(OrderMenuOption orderMenuOption){
        return OrderMenuOptionDTO.builder()
                .id(orderMenuOption.getId())
                .orderMenuDTO(OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenuOption.getOrderMenu()))
                .menuOptionDTO(MenuOptionDTO.fromMenuOptiontoMenuOptionDTO(orderMenuOption.getMenuOption()))
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
