package qt.qr_backend.domain;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
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
