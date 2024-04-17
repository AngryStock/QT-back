package qt.qr_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Category;
import qt.qr_backend.repository.StoreRepository;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String id;
    private String storeId;
    private String name;

    public Category toCategory(StoreRepository repository){
        return Category.builder()
                .store(repository.getReferenceById(storeId))
                .name(name)
                .build();
    }

    public static Category fromCategoryDTOtoCategory(CategoryDTO categoryDTO, StoreRepository storeRepository){
        return Category.builder()
                .id(categoryDTO.id)
                .store(storeRepository.getReferenceById(categoryDTO.storeId))
                .name(categoryDTO.name)
                .build();
    }

    public static CategoryDTO fromCategorytoCategoryDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .storeId(category.getStore().getId())
                .name(category.getName())
                .build();
    }
    public static List<CategoryDTO> listFromCategorytoCategoryDTO(List<Category> list){
        return list.stream()
                .map(CategoryDTO::fromCategorytoCategoryDTO)
                .toList();
    }

}
