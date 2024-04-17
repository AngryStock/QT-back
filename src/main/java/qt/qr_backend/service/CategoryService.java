package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.CategoryDTO;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Store;
import qt.qr_backend.repository.CategoryRepository;
import qt.qr_backend.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        Category category = categoryRepository.save(categoryDTO.toCategory(storeRepository));
        return CategoryDTO.fromCategorytoCategoryDTO(category);
    }
    public List<CategoryDTO> addCategory(String storeId, List<String> categories){
        List<Category> list = new ArrayList<>();
        Store storeProxy = storeRepository.getReferenceById(storeId);
        for (String category : categories) {
            list.add(new Category(storeProxy, category));
        }
        return CategoryDTO.listFromCategorytoCategoryDTO(categoryRepository.saveAll(list));
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO){
        Optional<Category> targetCategory = categoryRepository.findById(categoryDTO.getId());
        if (targetCategory.isPresent()){
            if (categoryDTO.getName()!=null){
                targetCategory.get().setName(categoryDTO.getName());
            }
        }
        Category savedCategory = categoryRepository.save(targetCategory.get());
        return CategoryDTO.fromCategorytoCategoryDTO(savedCategory);
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
//    public List<CategoryDTO> findAllCategory(){
//        List<Category> findAllCategory = categoryRepository.findAll();
//        return CategoryDTO.listFromCategorytoCategoryDTO(findAllCategory);
//    }

}
