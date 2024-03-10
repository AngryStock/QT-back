package qt.qr_backend.DTO;



import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.MenuOption;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionDTO {
    private String id;
    private MenuDTO menu;
    private String name;
    private int price;
    public MenuOption toMenuOption(){
        return MenuOption.builder()
                .menu(MenuDTO.fromMenuDTOtoMenu(menu))
                .name(name)
                .price(price)
                .build();
    }
    public static MenuOptionDTO fromMenuOptiontoMenuOptionDTO(MenuOption menuOption){
        return MenuOptionDTO.builder()
                .id(menuOption.getId())
                .menu(MenuDTO.fromMenutoMenuDTO(menuOption.getMenu()))
                .name(menuOption.getName())
                .price(menuOption.getPrice())
                .build();
    }
    public static List<MenuOptionDTO> listFromMenuOptionToMenuOptionDTO(List<MenuOption> list){
        return list.stream()
                .map(MenuOptionDTO::fromMenuOptiontoMenuOptionDTO)
                .toList();
    }

}
