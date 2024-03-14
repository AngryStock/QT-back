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
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "option_name")
    private String name;

    @Column(name = "option_price")
    private int price;

    public void setId(String id) {
        this.id = id;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
