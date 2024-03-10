package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.DTO.MenuOptionDTO;
import qt.qr_backend.controller.response.MenuOptionResponse;
import qt.qr_backend.service.MenuOptionService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menuOption")
public class MenuOptionController {

    private final MenuOptionService menuOptionService;

    @PostMapping("/save")
    public ResponseEntity<MenuOptionDTO> menuOptionSave(@RequestBody MenuOptionDTO menuOptionDTO){
        log.info("start save menuOption");
        return ResponseEntity.ok(menuOptionService.saveMenuOption(menuOptionDTO));
    }
    @GetMapping("/delete/{menuOptionId}")
    public MenuOptionResponse menuOptionDelete(@PathVariable String menuOptionId){
        log.info("start delete menuOption");
        menuOptionService.deleteMenuOption(menuOptionId);
        return new MenuOptionResponse(200,"delete ok");
    }

    @GetMapping("/find/menuOptionId/{menuOptionId}")
    public ResponseEntity<MenuOptionDTO> menuOptionFindById(@PathVariable String menuOptionId){
        log.info("start findById menuOption");
        MenuOptionDTO menuOptionDTO = menuOptionService.findMenuOptionById(menuOptionId);
        return ResponseEntity.ok(menuOptionDTO);
    }
    @GetMapping("/find/menuId/{menuId}")
    public ResponseEntity<List<MenuOptionDTO>> menuOptionFindByMenuId(@PathVariable String menuId){
        log.info("start findBymenuId menuOption");
        List<MenuOptionDTO> menuOptionListByMenuId = menuOptionService.findMenuOptionListByMenuId(menuId);
        return ResponseEntity.ok(menuOptionListByMenuId);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MenuOptionDTO>> menuOptionFindAll(){
        log.info("start findAll menuOption");
        List<MenuOptionDTO> allMenuOptionDTO = menuOptionService.findAllMenuOption();
        return ResponseEntity.ok(allMenuOptionDTO);
    }




}
