package qt.qr_backend.DTO;

import lombok.Data;

@Data
public class PwChangeDTO {
    private String currentPassword; //이전 비밀번호 입력
    private String postPassword; // 비밀번호 변경
    private String checkPassword; // 비밀번호 확인
}
