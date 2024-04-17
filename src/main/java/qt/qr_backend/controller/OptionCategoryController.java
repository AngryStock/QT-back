package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.DTO.CategoryDTO;
import qt.qr_backend.DTO.OptionCategoryDTO;
import qt.qr_backend.controller.request.CategoryRequest;
import qt.qr_backend.controller.request.OptionCategoryRequest;
import qt.qr_backend.controller.response.OptionCategoryResponse;
import qt.qr_backend.service.OptionCategoryService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/optionCategory")
public class OptionCategoryController {

    private final OptionCategoryService optionCategoryService;

    @PostMapping("/save")
    public ResponseEntity<OptionCategoryDTO> optionCategorySave(@RequestBody OptionCategoryDTO optionCategoryDTO){
        log.info("start save optionCategory");
        return ResponseEntity.ok(optionCategoryService.saveOptionCategory(optionCategoryDTO));
    }

    @PostMapping("/add")
    public ResponseEntity<List<OptionCategoryDTO>> categoryAdd(@RequestBody OptionCategoryRequest request){
        return ResponseEntity.ok(optionCategoryService.addOptionCategory(request.getMenuId(), request.getValue()));
    }

    @PostMapping("/update")
    public ResponseEntity<OptionCategoryDTO> optionCategoryUpdate(@RequestBody OptionCategoryDTO optionCategoryDTO){
        return ResponseEntity.ok(optionCategoryService.updateOptionCategory(optionCategoryDTO));
    }
    @GetMapping("/delete/{optionCategoryId}")
    public OptionCategoryResponse optionCategoryDelete(@PathVariable String optionCategoryId){
        log.info("start delete optionCategory");
        optionCategoryService.deleteOptionCategory(optionCategoryId);
        return new OptionCategoryResponse(200,"delete ok");
    }

    @GetMapping("/find/optionCategoryId/{optionCategoryId}")
    public ResponseEntity<OptionCategoryDTO> optionCategoryFindById(@PathVariable String optionCategoryId){
        log.info("start findById optionCategory");
        OptionCategoryDTO optionCategoryDTO = optionCategoryService.findOptionCategoryById(optionCategoryId);
        return ResponseEntity.ok(optionCategoryDTO);
    }
    @GetMapping("/find/menuId/{menuId}")
    public ResponseEntity<List<OptionCategoryDTO>> optionCategoryFindByMenuId(@PathVariable String menuId){
        log.info("start findById optionCategory");
        List<OptionCategoryDTO> optionCategoryListByStoreId = optionCategoryService.findOptionCategoryListByMenuId(menuId);
        return ResponseEntity.ok(optionCategoryListByStoreId);
    }

//    @GetMapping("/findAll")
//    public ResponseEntity<List<OptionCategoryDTO>> optionCategoryFindAll(){
//        log.info("start findAll optionCategory");
//        List<OptionCategoryDTO> allOptionCategoryDTO = optionCategoryService.findAllOptionCategory();
//        return ResponseEntity.ok(allOptionCategoryDTO);
//    }




}
