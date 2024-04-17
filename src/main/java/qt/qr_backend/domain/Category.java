package qt.qr_backend.domain;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "category_name")
    private String name;

    public Category(Store storeProxy, String s) {
        this.store = storeProxy;
        this.name = s;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setName(String name) {
        this.name = name;
    }
}
