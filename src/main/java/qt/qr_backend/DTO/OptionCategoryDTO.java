package qt.qr_backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.OptionCategory;
import qt.qr_backend.repository.MenuRepository;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionCategoryDTO {
    private String id;
    private String menuId;
    private String name;
    private boolean essential;
    private boolean only;

    public OptionCategory toOptionCategory(MenuRepository repository){
        return OptionCategory.builder()
                .menu(repository.getReferenceById(menuId))
                .name(name)
                .essential(false)
                .only(false)
                .build();
    }

    public static OptionCategory fromOptionCategoryDTOtoOptionCategory(OptionCategoryDTO optionCategoryDTO, MenuRepository repository){
        return OptionCategory.builder()
                .id(optionCategoryDTO.id)
                .menu(repository.getReferenceById(optionCategoryDTO.menuId))
                .name(optionCategoryDTO.name)
                .essential(optionCategoryDTO.essential)
                .only(optionCategoryDTO.only)
                .build();
    }

    public static OptionCategoryDTO fromOptionCategorytoOptionCategoryDTO(OptionCategory optionCategory){
        return OptionCategoryDTO.builder()
                .id(optionCategory.getId())
                .menuId(optionCategory.getMenu().getId())
                .name(optionCategory.getName())
                .essential(optionCategory.isEssential())
                .only(optionCategory.isOnly())
                .build();
    }
    public static List<OptionCategoryDTO> listFromOptionCategorytoOptionCategoryDTO(List<OptionCategory> list){
        return list.stream()
                .map(OptionCategoryDTO::fromOptionCategorytoOptionCategoryDTO)
                .toList();
    }
}
