package qt.qr_backend.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryRequest {
    private String storeId;
    private List<String> value;
}
