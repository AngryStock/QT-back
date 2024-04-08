package qt.qr_backend.DTO;



import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.Menu;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private String id;
    private CategoryDTO category;
    private String name;
    private int price;
    private String description;
    private String menuImageUrl;

    public Menu toMenu(){
        return Menu.builder()
                .category(CategoryDTO.fromCategoryDTOtoCategory(category))
                .name(name)
                .price(price)
                .description(description)
                .menuImageUrl(menuImageUrl)
                .build();
    }
    public static Menu fromMenuDTOtoMenu(MenuDTO menuDTO){
        return Menu.builder()
                .id(menuDTO.id)
                .category(CategoryDTO.fromCategoryDTOtoCategory(menuDTO.category))
                .name(menuDTO.name)
                .price(menuDTO.price)
                .description(menuDTO.description)
                .menuImageUrl(menuDTO.menuImageUrl)
                .build();
    }
    public static MenuDTO fromMenutoMenuDTO(Menu menu){
        return MenuDTO.builder()
                .id(menu.getId())
                .category(CategoryDTO.fromCategorytoCategoryDTO(menu.getCategory()))
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .menuImageUrl(menu.getMenuImageUrl())
                .build();
    }
    public static List<MenuDTO> listFromMenutoMenuDTO(List<Menu> list){
        return list.stream()
                .map(MenuDTO::fromMenutoMenuDTO)
                .toList();
    }

}
