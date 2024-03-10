package qt.qr_backend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import qt.qr_backend.dto.CeoDTO;
import qt.qr_backend.dto.CeoDetails;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/signup") || requestURI.startsWith("/login")
                || requestURI.startsWith("/findId") || requestURI.startsWith("/findPassword")) {
            filterChain.doFilter(request, response);
            return;
        }

        //쿠키들을 불러온 뒤 Authorization Key에 담긴 쿠키를 찾음.
        String authorization = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            log.info("쿠키 이름 = {}", cookie.getName());
            if(cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
        }

        if(authorization == null) {
            log.info("토큰이 없습니다");
            filterChain.doFilter(request,response);

            return;
        }

        //토큰
        String token = authorization;

        //토큰 소멸시간 검증
        if(jwtUtil.isExpired(token)) {
            log.info("토큰이 소멸 됐습니다");
            filterChain.doFilter(request,response);

            return;
        }

        //토큰에서 아이디(username)과 역할(role) 획득
        String loginId = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        //ceoDTO를 생성하여 값 set
        CeoDTO ceoDTO = new CeoDTO();
        ceoDTO.setLoginId(loginId);
        ceoDTO.setRole(role);

        //CeoDetails에 ceo 정보 객체 담기
        CeoDetails ceoDetails = new CeoDetails(ceoDTO);

        //스프링 시큐리티 인증 토큰 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(ceoDetails, null, ceoDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
