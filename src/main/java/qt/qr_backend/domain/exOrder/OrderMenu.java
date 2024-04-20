package qt.qr_backend.domain.exOrder;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import qt.qr_backend.DTO.OrderDTO;
import qt.qr_backend.domain.Menu;
import qt.qr_backend.domain.converter.StringListConverter;

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
    @Convert(converter = StringListConverter.class)
    private List<String> options;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    private int amount;
    private String name;

    public OrderMenu(String name, List<String> options, int amount, Order savedOrder) {
        this.id = null;
        this.name = name;
        this.options = options;
        this.amount = amount;
        this.order = savedOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
