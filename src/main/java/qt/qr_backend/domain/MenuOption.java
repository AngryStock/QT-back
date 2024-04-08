package qt.qr_backend.domain;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuOption {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "menu_option_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_category_id")
    private OptionCategory optionCategory;

    @Column(name = "option_name")
    private String name;

    @Column(name = "option_price")
    private int price;

    private String menuOptionImageUrl;

    public void setId(String id) {
        this.id = id;
    }

    public void setOptionCategory(OptionCategory optionCategory) {
        this.optionCategory = optionCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
