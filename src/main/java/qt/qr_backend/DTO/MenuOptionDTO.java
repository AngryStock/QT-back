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
    private OptionCategoryDTO optionCategory;
    private String name;
    private int price;
    private String menuOptionImageUrl;

    public MenuOption toMenuOption(){
        return MenuOption.builder()
                .optionCategory(OptionCategoryDTO.fromOptionCategoryDTOtoOptionCategory(optionCategory))
                .name(name)
                .price(price)
                .menuOptionImageUrl(menuOptionImageUrl)
                .build();
    }
    public static MenuOptionDTO fromMenuOptiontoMenuOptionDTO(MenuOption menuOption){
        return MenuOptionDTO.builder()
                .id(menuOption.getId())
                .optionCategory(OptionCategoryDTO.fromOptionCategorytoOptionCategoryDTO(menuOption.getOptionCategory()))
                .name(menuOption.getName())
                .price(menuOption.getPrice())
                .menuOptionImageUrl(menuOption.getMenuOptionImageUrl())
                .build();
    }
    public static MenuOption fromMenuOptionDTOtoMenuOption(MenuOptionDTO menuOptionDTO){
        return MenuOption.builder()
                .id(menuOptionDTO.id)
                .optionCategory(OptionCategoryDTO.fromOptionCategoryDTOtoOptionCategory(menuOptionDTO.optionCategory))
                .name(menuOptionDTO.name)
                .price(menuOptionDTO.price)
                .menuOptionImageUrl(menuOptionDTO.menuOptionImageUrl)
                .build();
    }
    public static List<MenuOptionDTO> listFromMenuOptionToMenuOptionDTO(List<MenuOption> list){
        return list.stream()
                .map(MenuOptionDTO::fromMenuOptiontoMenuOptionDTO)
                .toList();
    }

}
