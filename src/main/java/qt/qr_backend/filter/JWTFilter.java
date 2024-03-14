package qt.qr_backend.filter;

import io.jsonwebtoken.ExpiredJwtException;
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
import qt.qr_backend.DTO.CeoDetails;
import qt.qr_backend.DTO.CeoJwtDTO;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/signup") || requestURI.startsWith("/login")
                || requestURI.startsWith("/findId") || requestURI.startsWith("/findPassword")
                || requestURI.startsWith("/ceoImages") || requestURI.startsWith("/reissue")) {
            filterChain.doFilter(request, response);
            return;
        }

        //쿠키들을 불러온 뒤 access token이 담긴 쿠키를 찾음.
        String accessToken = null;
        Cookie[] cookies = request.getCookies();

        // cookies 배열이 null이 아닌 경우에만 반복문 실행
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("쿠키 이름 = {}", cookie.getName());
                if (cookie.getName().equals("access")) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        } else {
            log.info("쿠키가 없습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        if(accessToken == null) {
            log.info("토큰이 없습니다");
            filterChain.doFilter(request,response);

            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch(ExpiredJwtException e) {

            // response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            // response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

       // 토큰이 access인지
        String category = jwtUtil.getCategory(accessToken);
        if(!category.equals("access")) {

            // response body
            PrintWriter writer = response.getWriter();
            writer.print("Invalid access token");

            // response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //토큰에서 아이디(username)과 역할(role) 획득
        String loginId = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //ceoDTO를 생성하여 값 set
        CeoJwtDTO ceoJwtDTO = new CeoJwtDTO();
        ceoJwtDTO.setLoginId(loginId);
        ceoJwtDTO.setRole(role);

        //CeoDetails에 ceo 정보 객체 담기
        CeoDetails ceoDetails = new CeoDetails(ceoJwtDTO);

        //스프링 시큐리티 인증 토큰 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(ceoDetails, null, ceoDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
