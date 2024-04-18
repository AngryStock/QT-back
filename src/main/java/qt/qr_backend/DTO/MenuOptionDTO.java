package qt.qr_backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.MenuOption;
import qt.qr_backend.repository.OptionCategoryRepository;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionDTO {
    private String id;
    private String optionCategoryId;
    private String name;
    private int price;
    private boolean isSelected = false;

    public MenuOption toMenuOption(OptionCategoryRepository repository){
        return MenuOption.builder()
                .optionCategory(repository.getReferenceById(optionCategoryId))
                .name(name)
                .price(price)
                .build();
    }
    public static MenuOptionDTO fromMenuOptiontoMenuOptionDTO(MenuOption menuOption){
        return MenuOptionDTO.builder()
                .id(menuOption.getId())
                .optionCategoryId(menuOption.getOptionCategory().getId())
                .name(menuOption.getName())
                .price(menuOption.getPrice())
                .isSelected(false)
                .build();
    }
    public static MenuOption fromMenuOptionDTOtoMenuOption(MenuOptionDTO menuOptionDTO,OptionCategoryRepository repository){
        return MenuOption.builder()
                .id(menuOptionDTO.id)
                .optionCategory(repository.getReferenceById(menuOptionDTO.optionCategoryId))
                .name(menuOptionDTO.name)
                .price(menuOptionDTO.price)
                .build();
    }
    public static List<MenuOptionDTO> listFromMenuOptionToMenuOptionDTO(List<MenuOption> list){
        return list.stream()
                .map(MenuOptionDTO::fromMenuOptiontoMenuOptionDTO)
                .toList();
    }

}
