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
import qt.qr_backend.domain.enums.Approval;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ceo_id")
    private Ceo ceo;

    @Column(name = "store_name")
    private String name;

    @Column(name = "store_phone")
    private String phoneNumber;
    private String address;
    private String businessNumber;
    private int tableCount;

    @Enumerated(EnumType.STRING)
    private Approval approval;
}