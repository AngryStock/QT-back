package qt.qr_backend.domain;

import jakarta.persistence.*;

@Entity
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_menu_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int orderPrice;
    private int count;
}
