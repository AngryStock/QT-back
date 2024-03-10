package qt.qr_backend.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private LocalDateTime orderDate;

    @Column(name = "order_status")
    private String status;
}
