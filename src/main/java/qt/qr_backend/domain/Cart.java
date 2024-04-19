package qt.qr_backend.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import qt.qr_backend.domain.converter.StringListConverter;

import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String tableNo;
    private String storeId;
    private String menuId;
    private String name;
    private String img;
    @Convert(converter = StringListConverter.class)
    //@ElementCollection(fetch = FetchType.LAZY)
    private List<String> options;
    private int price;
    private int amount;

    public void setTable(String table) {
        this.tableNo = tableNo;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
