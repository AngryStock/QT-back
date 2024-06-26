package qt.qr_backend.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateEntity {

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-mm-dd/HH:mm:ss")
    private LocalDateTime createdAt;

    // 승인 날짜
    @Setter
    @DateTimeFormat(pattern = "yyyy-mm-dd/HH:mm:ss")
    private LocalDateTime approvalAt;

}
