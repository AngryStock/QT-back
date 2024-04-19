package qt.qr_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.exOrder.OrderMenuOption;
import qt.qr_backend.repository.MenuOptionRepository;
import qt.qr_backend.repository.OrderMenuRepository;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuOptionDTO {
    private String id;
    private String orderMenuId;
    private String menuOptionId;

    public OrderMenuOptionDTO(String menuOptionId) {
        this.menuOptionId = menuOptionId;
    }

    public OrderMenuOptionDTO(String orderMenuId, String menuOptionId) {
        this.orderMenuId = orderMenuId;
        this.menuOptionId = menuOptionId;
    }


    public OrderMenuOption toOrderMenuOption(OrderMenuRepository orderMenuRepository, MenuOptionRepository menuOptionRepository){
        return OrderMenuOption.builder()
                .orderMenu(orderMenuRepository.getReferenceById(orderMenuId))
                .menuOption(menuOptionRepository.getReferenceById(menuOptionId))
                .build();
    }

//    public static OrderMenuOption fromOrderMenuOptionDTOtoOrderMenuOption(OrderMenuOptionDTO orderMenuOptionDTO){
//        return OrderMenuOption.builder()
//                .id(orderMenuOptionDTO.id)
//                .orderMenu(OrderMenuDTO.fromOrderMenuDTOtoOrderMenu(orderMenuOptionDTO.orderMenu))
//                .menuOption(MenuOptionDTO.fromMenuOptionDTOtoMenuOption(orderMenuOptionDTO.menuOption))
//                .build();
//    }

//    public static OrderMenuOptionDTO fromOrderMenuOptiontoOrderMenuOptionDTO(OrderMenuOption orderMenuOption){
//        return OrderMenuOptionDTO.builder()
//                .id(orderMenuOption.getId())
//                .orderMenu(OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenuOption.getOrderMenu()))
//                .menuOption(MenuOptionDTO.fromMenuOptiontoMenuOptionDTO(orderMenuOption.getMenuOption()))
//                .build();
//    }
//    public static List<OrderMenuOptionDTO> listFromOrderMenuOptiontoOrderMenuOptionDTO(List<OrderMenuOption> list){
//        return list.stream()
//                .map(OrderMenuOptionDTO::fromOrderMenuOptiontoOrderMenuOptionDTO)
//                .toList();
//    }
//    public static List<OrderMenuOption> listFromOrderMenuOptionDTOtoOrderMenuOption(List<OrderMenuOptionDTO> list){
//        return list.stream()
//                .map(OrderMenuOptionDTO::fromOrderMenuOptionDTOtoOrderMenuOption)
//                .toList();
//    }
}
