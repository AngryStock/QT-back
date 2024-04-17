package qt.qr_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qt.qr_backend.domain.RefreshEntity;
import qt.qr_backend.repository.RefreshRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final RefreshRepository refreshRepository;

    public Boolean existsByRefresh(String refresh) {
        return refreshRepository.existsByRefresh(refresh);
    }

    public void deleteByRefresh(String refresh){
        refreshRepository.deleteByRefresh(refresh);
    }

    public void addRefreshEntity(String loginId, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setLoginId(loginId);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setIsExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
}
