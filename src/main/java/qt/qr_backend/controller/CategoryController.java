package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.DTO.CategoryDTO;
import qt.qr_backend.controller.response.CategoryResponse;
import qt.qr_backend.service.CategoryService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<CategoryDTO> categorySave(@RequestBody CategoryDTO categoryDTO){
        log.info("start save category");
        return ResponseEntity.ok(categoryService.saveCategory(categoryDTO));
    }
    @GetMapping("/delete/{categoryId}")
    public CategoryResponse categoryDelete(@PathVariable String categoryId){
        log.info("start delete category");
        categoryService.deleteCategory(categoryId);
        return new CategoryResponse(200,"delete ok");
    }

    @GetMapping("/find/categoryId/{categoryId}")
    public ResponseEntity<CategoryDTO> categoryFindById(@PathVariable String categoryId){
        log.info("start findById category");
        CategoryDTO categoryDTO = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }
    @GetMapping("/find/storeId/{storeId}")
    public ResponseEntity<List<CategoryDTO>> categoryFindByStoreId(@PathVariable String storeId){
        log.info("start findById category");
        List<CategoryDTO> categoryListByStoreId = categoryService.findCategoryListByStoreId(storeId);
        return ResponseEntity.ok(categoryListByStoreId);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryDTO>> categoryFindAll(){
        log.info("start findAll category");
        List<CategoryDTO> allCategoryDTO = categoryService.findAllCategory();
        return ResponseEntity.ok(allCategoryDTO);
    }




}
