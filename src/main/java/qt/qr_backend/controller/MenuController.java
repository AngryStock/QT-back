package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qt.qr_backend.DTO.MenuDTO;
import qt.qr_backend.DTO.OrderMenuDTO;
import qt.qr_backend.controller.response.MenuResponse;
import qt.qr_backend.service.MenuService;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/save")
    public ResponseEntity<MenuDTO> menuSave(@RequestBody MenuDTO menuDTO){
        log.info("start save menu");
        return ResponseEntity.ok(menuService.saveMenu(menuDTO));
    }


    @PostMapping("/update")
    public ResponseEntity<MenuDTO> menuUpdate(@RequestBody MenuDTO menuDTO){
        return ResponseEntity.ok(menuService.updateMenu(menuDTO));
    }
    @GetMapping("/delete/{menuId}")
    public MenuResponse menuDelete(@PathVariable String menuId){
        log.info("start delete menu");
        menuService.deleteMenu(menuId);
        return new MenuResponse(200,"delete ok");
    }

    @GetMapping("/find/menuId/{menuId}")
    public ResponseEntity<MenuDTO> menuFindById(@PathVariable String menuId){
        log.info("start findById menu");
        MenuDTO menuDTO = menuService.findMenuById(menuId);
        return ResponseEntity.ok(menuDTO);
    }
    @GetMapping("/find/categoryId/{categoryId}")
    public ResponseEntity<List<MenuDTO>> menuFindByCategoryId(@PathVariable String categoryId){
        log.info("start findByCategoryId menu");
        List<MenuDTO> menuListByCategoryId = menuService.findMenuListByCategoryId(categoryId);
        return ResponseEntity.ok(menuListByCategoryId);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MenuDTO>> menuFindAll(){
        log.info("start findAll menu");
        List<MenuDTO> allMenuDTO = menuService.findAllMenu();
        return ResponseEntity.ok(allMenuDTO);
    }




}
