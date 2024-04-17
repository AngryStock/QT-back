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
        List<AdminViewDTO> joinInfos = adminService.listAllJoinInfos();
        return ResponseEntity.ok(joinInfos);
    }

    // 승인
    @PostMapping("/approve/{storeId}")
    public ResponseEntity<?> approveJoin(@PathVariable String storeId) {
        adminService.approveJoin(storeId);
        return ResponseEntity.ok().build();
    }

    // 거부
    @PostMapping("/reject/{storeId}")
    public ResponseEntity<?> rejectJoin(@PathVariable String storeId) {
        adminService.rejectJoin(storeId);
        return ResponseEntity.ok().build();
    }
}
