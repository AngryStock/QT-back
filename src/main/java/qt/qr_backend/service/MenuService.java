package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.MenuDTO;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.repository.CategoryRepository;
import qt.qr_backend.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public MenuDTO saveMenu(MenuDTO menuDTO){
        Menu menu = menuRepository.save(menuDTO.toMenu(categoryRepository));
        return MenuDTO.fromMenutoMenuDTO(menu);
    }
    public MenuDTO updateMenu(MenuDTO menuDTO){
        Optional<Menu> targetMenu = menuRepository.findById(menuDTO.getId());
        if (targetMenu.isPresent()){
            if (menuDTO.getName()!=null){
                targetMenu.get().setName(menuDTO.getName());
            }
            if (menuDTO.getPrice()!=0){
                targetMenu.get().setPrice(menuDTO.getPrice());
            }
            if (menuDTO.getDescription()!=null){
                targetMenu.get().setDescription(menuDTO.getDescription());
            }
            if (menuDTO.getMenuImageUrl()!=null){
                targetMenu.get().setMenuImageUrl(menuDTO.getMenuImageUrl());
            }
        }
        Menu savedMenu = menuRepository.save(targetMenu.get());
        return MenuDTO.fromMenutoMenuDTO(savedMenu);
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
    public List<MenuDTO> findMenuListByStoreId(String storeId){
        List<Category> categories = categoryRepository.findByStore_Id(storeId);
        List<Menu> menus = new ArrayList<>();
        for (Category category : categories) {
            menus.addAll(menuRepository.findByCategory_Id(category.getId()));
        }
        return MenuDTO.listFromMenutoMenuDTO(menus);
    }

    public List<MenuDTO> findAllMenu(){
        List<Menu> findAllMenu = menuRepository.findAll();
        return MenuDTO.listFromMenutoMenuDTO(findAllMenu);
    }

}
