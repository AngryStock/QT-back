package qt.qr_backend.domain.exOrder;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import qt.qr_backend.domain.MenuOption;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_menu_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderMenu orderMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MenuOption menuOption;


    public void setId(String id) {
        this.id = id;
    }

    public void setOrderMenu(OrderMenu orderMenu) {
        this.orderMenu = orderMenu;
    }

    public void setMenuOption(MenuOption menuOption) {
        this.menuOption = menuOption;
    }


}
