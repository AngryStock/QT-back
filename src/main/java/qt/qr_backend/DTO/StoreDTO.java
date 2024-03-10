package qt.qr_backend.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.domain.Store;
import qt.qr_backend.domain.enums.Approval;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDTO {

    private String id;
    private CeoDTO ceo;
    private String name;
    private String phoneNumber;
    private String mainAddress;
    private String detailAddress;
    private String businessNumber;
    private int tableCount;

    private Approval approval;

    public static Store fromStoreDTOtoStore(StoreDTO store) {
        return Store.builder()
                .id(store.id)
                .ceo(CeoDTO.fromCeoDTOtoCeo(store.ceo))
                .name(store.name)
                .phoneNumber(store.phoneNumber)
                .mainAddress(store.mainAddress)
                .detailAddress(store.detailAddress)
                .businessNumber(store.businessNumber)
                .tableCount(store.tableCount)
                .approval(store.approval)
                .build();
    }
    public static StoreDTO fromStoretoStoreDTO(Store store){
        return StoreDTO.builder()
                .id(store.getId())
                .ceo(CeoDTO.fromCeotoCeoDTO(store.getCeo()))
                .name(store.getName())
                .phoneNumber(store.getPhoneNumber())
                .mainAddress(store.getMainAddress())
                .detailAddress(store.getDetailAddress())
                .businessNumber(store.getBusinessNumber())
                .tableCount(store.getTableCount())
                .approval(store.getApproval())
                .build();
    }
}
