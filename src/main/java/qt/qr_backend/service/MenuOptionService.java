package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.MenuDTO;
import qt.qr_backend.DTO.MenuOptionDTO;
import qt.qr_backend.DTO.OptionCategoryDTO;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.MenuOption;
import qt.qr_backend.domain.OptionCategory;
import qt.qr_backend.repository.MenuOptionRepository;
import qt.qr_backend.repository.OptionCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

    private final MenuOptionRepository menuOptionRepository;
    private final OptionCategoryRepository optionCategoryRepository;
    public MenuOptionDTO saveMenuOption(MenuOptionDTO menuOptionDTO){
        MenuOption menuOption = menuOptionRepository.save(menuOptionDTO.toMenuOption(optionCategoryRepository));
        return MenuOptionDTO.fromMenuOptiontoMenuOptionDTO(menuOption);
    }

    public List<MenuOptionDTO> addMenuOption(String optionCategoryId, List<String> menuOptions) {
        List<MenuOption> list = new ArrayList<>();
        OptionCategory optionCategoryProxy = optionCategoryRepository.getReferenceById(optionCategoryId);
        for (String menuOption : menuOptions) {
            list.add(new MenuOption(optionCategoryProxy, menuOption));
        }
        return MenuOptionDTO.listFromMenuOptionToMenuOptionDTO(menuOptionRepository.saveAll(list));
    }

    public MenuOptionDTO updateMenuOption(MenuOptionDTO menuOptionDTO){
        Optional<MenuOption> targetMenuOption = menuOptionRepository.findById(menuOptionDTO.getId());
        if (targetMenuOption.isPresent()) {
            if (menuOptionDTO.getName() != null) {
                targetMenuOption.get().setName(menuOptionDTO.getName());
            }
            if (menuOptionDTO.getPrice() != 0) {
                targetMenuOption.get().setPrice(menuOptionDTO.getPrice());
            }
        }
        MenuOption savedMenuOption = menuOptionRepository.save(targetMenuOption.get());
        return MenuOptionDTO.fromMenuOptiontoMenuOptionDTO(savedMenuOption);
    }

    public void deleteMenuOption(String id){
        menuOptionRepository.deleteById(id);
    }
    public MenuOptionDTO findMenuOptionById(String id){
        Optional<MenuOption> findedMenuOption = menuOptionRepository.findById(id);
        MenuOption menuOption = new MenuOption();
        if(findedMenuOption.isPresent()){
            menuOption.setId(findedMenuOption.get().getId());
            menuOption.setOptionCategory(findedMenuOption.get().getOptionCategory());
            menuOption.setName(findedMenuOption.get().getName());
            menuOption.setPrice(findedMenuOption.get().getPrice());
        }
        return MenuOptionDTO.fromMenuOptiontoMenuOptionDTO(menuOption);
    }
    public List<MenuOptionDTO> findMenuOptionListByOptionCategoryId(String id){
        List<MenuOption> findedMenuOptionByOptionCategoryId = menuOptionRepository.findByOptionCategory_Id(id);
        return MenuOptionDTO.listFromMenuOptionToMenuOptionDTO(findedMenuOptionByOptionCategoryId);
    }



//    public List<MenuOptionDTO> findAllMenuOption(){
//        List<MenuOption> findAllMenuOption = menuOptionRepository.findAll();
//        return MenuOptionDTO.listFromMenuOptionToMenuOptionDTO(findAllMenuOption);
//    }

}
