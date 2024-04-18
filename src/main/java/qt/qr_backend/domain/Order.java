package qt.qr_backend.domain;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;

    private LocalDateTime orderDate;

    @Column(name = "order_status")
    private String status;

    private String tableId;

    private int orderPrice;

    public void setId(String id) {
        this.id = id;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
