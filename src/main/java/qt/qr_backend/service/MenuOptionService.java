package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.MenuDTO;
import qt.qr_backend.DTO.MenuOptionDTO;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.MenuOption;
import qt.qr_backend.repository.MenuOptionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

    private final MenuOptionRepository menuOptionRepository;

    public MenuOptionDTO saveMenuOption(MenuOptionDTO menuOptionDTO){
        MenuOption menuOption = menuOptionRepository.save(menuOptionDTO.toMenuOption());
        return MenuOptionDTO.fromMenuOptiontoMenuOptionDTO(menuOption);
    }

    public MenuOptionDTO updateMenuOption(MenuOptionDTO menuOptionDTO){
        menuOptionRepository.save(MenuOptionDTO.fromMenuOptionDTOtoMenuOption(menuOptionDTO));
        MenuOption menuOption = menuOptionRepository.findNoProxyMenuOptionById(menuOptionDTO.getId());
        return MenuOptionDTO.fromMenuOptiontoMenuOptionDTO(menuOption);
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

    public List<MenuOptionDTO> findAllMenuOption(){
        List<MenuOption> findAllMenuOption = menuOptionRepository.findAll();
        return MenuOptionDTO.listFromMenuOptionToMenuOptionDTO(findAllMenuOption);
    }

}
