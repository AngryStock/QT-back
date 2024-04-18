package qt.qr_backend.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import qt.qr_backend.DTO.MenuOptionDTO;
import qt.qr_backend.DTO.OptionCategoryDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class OptionRequest {
    private List<OptionCategoryDTO> categories;
    private List<MenuOptionDTO> menuOptions;
}
