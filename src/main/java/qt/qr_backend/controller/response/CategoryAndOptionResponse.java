package qt.qr_backend.controller.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import qt.qr_backend.DTO.MenuOptionDTO;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryAndOptionResponse {
    private String categoryId;
    private String menuId;
    private String title;
    private boolean essential;
    private boolean only;
    private List<MenuOptionDTO> optionLists;
}
