package qt.qr_backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.enums.Approval;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "store_id")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ceo_id")
    private Ceo ceo;

    @Column(name = "store_name")
    private String name;

    @Column(name = "store_phone")
    private String phoneNumber;
    private String mainAddress;
    private String detailAddress;
    private String businessNumber;
    private int tableCount;

    @Enumerated(EnumType.STRING)
    private Approval approval;

    public Store(Ceo ceo, String name, String phoneNumber, String mainAddress, String detailAddress,
                 String businessNumber,
                 int tableCount, Approval approval) {
        this.ceo = ceo;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
        this.businessNumber = businessNumber;
        this.tableCount = tableCount;
        this.approval = approval;
    }
}