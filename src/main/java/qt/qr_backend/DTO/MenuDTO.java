package qt.qr_backend.DTO;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.repository.CategoryRepository;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private String id;
    private String categoryId;
    private String name;
    private int price;
    private String description;
    private String menuImageUrl;

    public Menu toMenu(CategoryRepository repository){
        return Menu.builder()
                .category(repository.getReferenceById(categoryId))
                .name(name)
                .price(price)
                .description(description)
                .menuImageUrl(menuImageUrl)
                .build();
    }
    public static Menu fromMenuDTOtoMenu(MenuDTO menuDTO,CategoryRepository repository){
        return Menu.builder()
                .id(menuDTO.id)
                .category(repository.getReferenceById(menuDTO.categoryId))
                .name(menuDTO.name)
                .price(menuDTO.price)
                .description(menuDTO.description)
                .menuImageUrl(menuDTO.menuImageUrl)
                .build();
    }
    public static MenuDTO fromMenutoMenuDTO(Menu menu){
        return MenuDTO.builder()
                .id(menu.getId())
                .categoryId(menu.getCategory().getId())
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
