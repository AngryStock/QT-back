package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.CategoryDTO;
import qt.qr_backend.DTO.OptionCategoryDTO;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.OptionCategory;
import qt.qr_backend.domain.Store;
import qt.qr_backend.repository.MenuRepository;
import qt.qr_backend.repository.OptionCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionCategoryService {

    private final OptionCategoryRepository optionCategoryRepository;
    private final MenuRepository menuRepository;

    public OptionCategoryDTO saveOptionCategory(OptionCategoryDTO optionCategoryDTO){
        OptionCategory optionCategory = optionCategoryRepository.save(optionCategoryDTO.toOptionCategory(menuRepository));
        return OptionCategoryDTO.fromOptionCategorytoOptionCategoryDTO(optionCategory);
    }

    public List<OptionCategoryDTO> addOptionCategory(String menuId, List<String> optionCategories) {
        List<OptionCategory> list = new ArrayList<>();
        Menu menuProxy = menuRepository.getReferenceById(menuId);
        for (String optionCategory : optionCategories) {
            list.add(new OptionCategory(menuProxy, optionCategory));
        }
        return OptionCategoryDTO.listFromOptionCategorytoOptionCategoryDTO(optionCategoryRepository.saveAll(list));
    }

    public OptionCategoryDTO updateOptionCategory(OptionCategoryDTO optionCategoryDTO){
        Optional<OptionCategory> targetOptionCategory = optionCategoryRepository.findById(optionCategoryDTO.getId());
        if(targetOptionCategory.isPresent()){
            if(optionCategoryDTO.getName()!=null){
                targetOptionCategory.get().setName(optionCategoryDTO.getName());
            }
            if (optionCategoryDTO.isEssential()!=targetOptionCategory.get().isEssential()){
                targetOptionCategory.get().setEssential(optionCategoryDTO.isEssential());
            }
            if (optionCategoryDTO.isOnly()!=targetOptionCategory.get().isOnly()){
                targetOptionCategory.get().setOnly(optionCategoryDTO.isOnly());
            }
        }
        OptionCategory savedOptionCategory = optionCategoryRepository.save(targetOptionCategory.get());
        return OptionCategoryDTO.fromOptionCategorytoOptionCategoryDTO(savedOptionCategory);
    }

    public void deleteOptionCategory(String id){
        optionCategoryRepository.deleteById(id);
    }
    public OptionCategoryDTO findOptionCategoryById(String id){
        Optional<OptionCategory> findedOptionCategory = optionCategoryRepository.findById(id);
        OptionCategory optionCategory = new OptionCategory();
        if(findedOptionCategory.isPresent()){
            optionCategory.setId(findedOptionCategory.get().getId());
            optionCategory.setName(findedOptionCategory.get().getName());
            optionCategory.setMenu(findedOptionCategory.get().getMenu());
            optionCategory.setEssential(findedOptionCategory.get().isEssential());
            optionCategory.setOnly(findedOptionCategory.get().isOnly());
        }
        return OptionCategoryDTO.fromOptionCategorytoOptionCategoryDTO(optionCategory);
    }
    public List<OptionCategoryDTO> findOptionCategoryListByMenuId(String id){
        List<OptionCategory> findedOptionCategoryByMenuId = optionCategoryRepository.findByMenu_Id(id);
        return OptionCategoryDTO.listFromOptionCategorytoOptionCategoryDTO(findedOptionCategoryByMenuId);
    }


//    public List<OptionCategoryDTO> findAllOptionCategory(){
//        List<OptionCategory> findAllOptionCategory = optionCategoryRepository.findAll();
//        return OptionCategoryDTO.listFromOptionCategorytoOptionCategoryDTO(findAllOptionCategory);
//    }

}
