package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.OptionCategoryDTO;
import qt.qr_backend.domain.OptionCategory;
import qt.qr_backend.repository.OptionCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionCategoryService {

    private final OptionCategoryRepository optionCategoryRepository;

    public OptionCategoryDTO saveOptionCategory(OptionCategoryDTO optionCategoryDTO){
        OptionCategory optionCategory = optionCategoryRepository.save(optionCategoryDTO.toOptionCategory());
        return OptionCategoryDTO.fromOptionCategorytoOptionCategoryDTO(optionCategory);
    }

    public OptionCategoryDTO updateOptionCategory(OptionCategoryDTO optionCategoryDTO){
        optionCategoryRepository.save(OptionCategoryDTO.fromOptionCategoryDTOtoOptionCategory(optionCategoryDTO));
        OptionCategory optionCategory = optionCategoryRepository.findNoProxyOptionCategoryById(optionCategoryDTO.getId());
        return OptionCategoryDTO.fromOptionCategorytoOptionCategoryDTO(optionCategory);
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
        }
        return OptionCategoryDTO.fromOptionCategorytoOptionCategoryDTO(optionCategory);
    }
    public List<OptionCategoryDTO> findOptionCategoryListByMenuId(String id){
        List<OptionCategory> findedOptionCategoryByMenuId = optionCategoryRepository.findByMenu_Id(id);
        return OptionCategoryDTO.listFromOptionCategorytoOptionCategoryDTO(findedOptionCategoryByMenuId);
    }
    public List<OptionCategoryDTO> findAllOptionCategory(){
        List<OptionCategory> findAllOptionCategory = optionCategoryRepository.findAll();
        return OptionCategoryDTO.listFromOptionCategorytoOptionCategoryDTO(findAllOptionCategory);
    }

}
