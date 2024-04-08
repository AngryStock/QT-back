package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qt.qr_backend.DTO.MenuDTO;
import qt.qr_backend.DTO.MenuOptionDTO;
import qt.qr_backend.controller.response.MenuImageUrlResponse;
import qt.qr_backend.controller.response.MenuOptionImageUrlResponse;
import qt.qr_backend.controller.response.MenuOptionResponse;
import qt.qr_backend.exception.ErrorResponse;
import qt.qr_backend.service.FileHandler;
import qt.qr_backend.service.MenuOptionService;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menuOption")
public class MenuOptionController {

    private final MenuOptionService menuOptionService;
    private final FileHandler fileHandler;

    @PostMapping("/save")
    public ResponseEntity<MenuOptionDTO> menuOptionSave(@RequestBody MenuOptionDTO menuOptionDTO){
        log.info("start save menuOption");
        return ResponseEntity.ok(menuOptionService.saveMenuOption(menuOptionDTO));
    }

    @PostMapping("/menuOptionImages")
    public ResponseEntity<Object> saveMenuOptionImages(
            @RequestParam MultipartFile menuOptionImageFile) throws IOException {
        String menuOptionImageFileUrl = fileHandler.parseFileInfo(menuOptionImageFile);
        if(menuOptionImageFileUrl == null) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "파일을 첨부하지 않았습니다");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        log.info("menuOptionImageFileUrl = {}", menuOptionImageFileUrl);
        MenuOptionImageUrlResponse menuOptionImageUrlResponse = new MenuOptionImageUrlResponse(menuOptionImageFileUrl);
        return new ResponseEntity<>(menuOptionImageUrlResponse, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<MenuOptionDTO> menuOptionUpdate(@RequestBody MenuOptionDTO menuOptionDTO){
        return ResponseEntity.ok(menuOptionService.updateMenuOption(menuOptionDTO));
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
    @GetMapping("/find/optionCategoryId/{optionCategoryId}")
    public ResponseEntity<List<MenuOptionDTO>> menuOptionFindByOptionCategoryId(@PathVariable String optionCategoryId){
        log.info("start findBymenuId menuOption");
        List<MenuOptionDTO> menuOptionListByMenuId = menuOptionService.findMenuOptionListByOptionCategoryId(optionCategoryId);
        return ResponseEntity.ok(menuOptionListByMenuId);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MenuOptionDTO>> menuOptionFindAll(){
        log.info("start findAll menuOption");
        List<MenuOptionDTO> allMenuOptionDTO = menuOptionService.findAllMenuOption();
        return ResponseEntity.ok(allMenuOptionDTO);
    }




}
