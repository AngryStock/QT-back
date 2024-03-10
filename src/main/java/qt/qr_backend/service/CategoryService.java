package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import qt.qr_backend.DTO.CategoryDTO;
import qt.qr_backend.DTO.MenuDTO;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        Category category = categoryRepository.save(categoryDTO.toCategory());
        return CategoryDTO.fromCategorytoCategoryDTO(category);
    }

    public void deleteCategory(String id){
        categoryRepository.deleteById(id);
    }
    public CategoryDTO findCategoryById(String id){
        Optional<Category> findedCategory = categoryRepository.findById(id);
        Category category = new Category();
        if(findedCategory.isPresent()){
            category.setId(findedCategory.get().getId());
            category.setName(findedCategory.get().getName());
            category.setStore(findedCategory.get().getStore());
        }
        return CategoryDTO.fromCategorytoCategoryDTO(category);
    }
    public List<CategoryDTO> findCategoryListByStoreId(String id){
        List<Category> findedCategoryByStoreId = categoryRepository.findByStore_Id(id);
        return CategoryDTO.listFromCategorytoCategoryDTO(findedCategoryByStoreId);
    }
    public List<CategoryDTO> findAllCategory(){
        List<Category> findAllCategory = categoryRepository.findAll();
        return CategoryDTO.listFromCategorytoCategoryDTO(findAllCategory);
    }

}
