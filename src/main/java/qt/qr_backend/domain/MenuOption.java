package qt.qr_backend.domain;

import jakarta.persistence.*;

@Entity
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "menu_option_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "option_name")
    private String name;

    @Column(name = "option_price")
    private int price;
}
