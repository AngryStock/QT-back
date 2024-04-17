package qt.qr_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.DTO.AdminViewDTO;
import qt.qr_backend.service.AdminService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // 회원가입 정보 조회
    @GetMapping("/joinInfos")
    public ResponseEntity<List<AdminViewDTO>> listAllJoinInfos() {
        log.info("Listing all join infos");
        List<AdminViewDTO> joinInfos = adminService.listAllJoinInfos();
        return ResponseEntity.ok(joinInfos);
    }

    // 승인
    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approveJoin(@PathVariable("id") String storeId) {
        log.info("Approving join for storeId: {}", storeId);
        adminService.approveJoin(storeId);
        return ResponseEntity.ok().build();
    }

    // 거부
    @PostMapping("/reject/{id}")
    public ResponseEntity<?> rejectJoin(@PathVariable("id") String storeId) {
        log.info("Rejecting join for storeId: {}", storeId);
        adminService.rejectJoin(storeId);
        return ResponseEntity.ok().build();
    }
}
