package qt.qr_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import qt.qr_backend.domain.Store;
import qt.qr_backend.exception.ErrorResponse;
import qt.qr_backend.repository.StoreRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreRepository storeRepository;

    @GetMapping("/store/find/{storeId}")
    public ResponseEntity<?> findStoreById(@PathVariable String storeId) {
        Optional<Store> storeData = storeRepository.findById(storeId);
        Store store = storeData.orElse(null);
        if (store == null) {
            ErrorResponse errorResponse = new ErrorResponse(404, "가게 정보가 없습니다. 스토어 아이디를 다시 확인해주세요.");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(store, HttpStatus.OK);
    }
}
