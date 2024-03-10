package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.MenuDTO;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.repository.MenuRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuDTO saveMenu(MenuDTO menuDTO){
        Menu menu = menuRepository.save(menuDTO.toMenu());
        return MenuDTO.fromMenutoMenuDTO(menu);
    }

    public void deleteMenu(String id){
        menuRepository.deleteById(id);
    }
    public MenuDTO findMenuById(String id){
        Optional<Menu> findedMenu = menuRepository.findById(id);
        Menu menu = new Menu();
        if(findedMenu.isPresent()){
            menu.setId(findedMenu.get().getId());
            menu.setCategory(findedMenu.get().getCategory());
            menu.setName(findedMenu.get().getName());
            menu.setPrice(findedMenu.get().getPrice());
            menu.setDescription(findedMenu.get().getDescription());
        }
        return MenuDTO.fromMenutoMenuDTO(menu);
    }
    public List<MenuDTO> findMenuListByCategoryId(String id){
        List<Menu> findedMenuByCategoryId = menuRepository.findByCategory_Id(id);
        return MenuDTO.listFromMenutoMenuDTO(findedMenuByCategoryId);
    }

    public List<MenuDTO> findAllMenu(){
        List<Menu> findAllMenu = menuRepository.findAll();
        return MenuDTO.listFromMenutoMenuDTO(findAllMenu);
    }

}
