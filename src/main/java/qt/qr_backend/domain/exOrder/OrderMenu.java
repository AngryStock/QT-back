package qt.qr_backend.domain.exOrder;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import qt.qr_backend.domain.Menu;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderMenu {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_menu_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int orderMenuPrice;

    public void setId(String id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setOrderMenuPrice(int orderMenuPrice) {
        this.orderMenuPrice = orderMenuPrice;
    }
}
