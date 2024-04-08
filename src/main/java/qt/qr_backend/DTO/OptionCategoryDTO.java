package qt.qr_backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.OptionCategory;
import qt.qr_backend.domain.Menu;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionCategoryDTO {
    private String id;
    private MenuDTO menu;
    private String name;

    public OptionCategory toOptionCategory(){
        return OptionCategory.builder()
                .menu(MenuDTO.fromMenuDTOtoMenu(menu))
                .name(name)
                .build();
    }

    public static OptionCategory fromOptionCategoryDTOtoOptionCategory(OptionCategoryDTO optionCategoryDTO){
        return OptionCategory.builder()
                .id(optionCategoryDTO.id)
                .menu(MenuDTO.fromMenuDTOtoMenu(optionCategoryDTO.menu))
                .name(optionCategoryDTO.name)
                .build();
    }

    public static OptionCategoryDTO fromOptionCategorytoOptionCategoryDTO(OptionCategory optionCategory){
        return OptionCategoryDTO.builder()
                .id(optionCategory.getId())
                .menu(MenuDTO.fromMenutoMenuDTO(optionCategory.getMenu()))
                .name(optionCategory.getName())
                .build();
    }
    public static List<OptionCategoryDTO> listFromOptionCategorytoOptionCategoryDTO(List<OptionCategory> list){
        return list.stream()
                .map(OptionCategoryDTO::fromOptionCategorytoOptionCategoryDTO)
                .toList();
    }
}
