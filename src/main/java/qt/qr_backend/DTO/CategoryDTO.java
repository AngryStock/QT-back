package qt.qr_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Category;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String id;
    private StoreDTO store;
    private String name;

    public Category toCategory(){
        return Category.builder()
                .store(StoreDTO.fromStoreDTOtoStore(store))
                .name(name)
                .build();
    }

    public static Category fromCategoryDTOtoCategory(CategoryDTO categoryDTO){
        return Category.builder()
                .id(categoryDTO.id)
                .store(StoreDTO.fromStoreDTOtoStore(categoryDTO.store))
                .name(categoryDTO.name)
                .build();
    }

    public static CategoryDTO fromCategorytoCategoryDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .store(StoreDTO.fromStoretoStoreDTO(category.getStore()))
                .name(category.getName())
                .build();
    }
    public static List<CategoryDTO> listFromCategorytoCategoryDTO(List<Category> list){
        return list.stream()
                .map(CategoryDTO::fromCategorytoCategoryDTO)
                .toList();
    }

}
