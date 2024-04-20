package qt.qr_backend.domain.exOrder;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import qt.qr_backend.domain.Store;
import qt.qr_backend.domain.converter.StringListConverter;

import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.processing.Generated;

@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String storeId;
    private LocalDateTime orderDate;
    private String status;
    private String tableNo;
    private int price;
}
