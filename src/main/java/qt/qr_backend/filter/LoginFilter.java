package qt.qr_backend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
import qt.qr_backend.DTO.CeoDetails;
import qt.qr_backend.controller.request.LoginRequest;
import qt.qr_backend.service.RefreshService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String loginId = obtainUsername(request);
        String password = obtainPassword(request);

        log.info("아이디 : {} 로그인 시도", loginId);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginId, password, null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CeoDetails ceoDetails = (CeoDetails) authResult.getPrincipal();

        String loginId = ceoDetails.getUsername();

        log.info("아이디 : {} 로그인 성공", loginId);

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String access = jwtUtil.createJwt("access", loginId, role, 1000 * 60 * 60 * 24L);
        String refresh = jwtUtil.createJwt("refresh", loginId, role, 1000 * 60 * 60 * 24L);

        response.addCookie(createCookie("access", access));
        response.addCookie(createCookie("refresh", refresh));

        refreshService.addRefreshEntity(loginId, refresh, 1000 * 60 * 60 * 24L);

        response.setStatus(HttpStatus.OK.value());
        //response.sendRedirect("http://localhost:3000/");
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*24);
        //cookie.setSecure(true); https 통신만 허용
        cookie.setPath("/"); // 웹 어플리케이션의 모든 url 요청 범위에서 전송, /url/ 로 하면 /url/** 요청 시 전송함.
        cookie.setHttpOnly(true); // http 통신만 허용

        return cookie;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        writer.write("login failed");
        response.setStatus(401);
    }
}
