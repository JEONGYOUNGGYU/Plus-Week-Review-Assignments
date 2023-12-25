package com.sparta.plusweekreviewassignment.jwt;

import com.sparta.plusweekreviewassignment.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final UserDetailsServiceImpl userDetailsService;
  public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

    String tokenValue = jwtUtil.getTokenFromRequest(req); // "Authorization" 값을 가져온다
    if (StringUtils.hasText(tokenValue)) {                // 가지고 있는지 없는지 확인, 왜? 로그인을 했는지 안했는지 모름 있으면 BEARER_PREFIX에서 BEARER만 떼오기
                                                          // 없으면 다른 필터로 이동 -> (filterChain.doFilter(req, res) 여기로
      tokenValue = jwtUtil.substringToken(tokenValue);
      log.info(tokenValue);

      if (!jwtUtil.validateToken(tokenValue)) {           // 내가 만든 토큰이 맞는지 확인
        log.error("Token Error");
        res.setStatus(403);
        return;
      }

      Claims info = jwtUtil.getUserInfoFromToken(tokenValue); // 시그니처키랑 토큰을 해서 값만 받아오기
      try {
        setAuthentication(info.getSubject());                 // 시큐리티 하는 목적 : 시큐리티 컨텍스트 홀더에 Authentication을 넣는 것
      } catch (Exception e) {
        log.error(e.getMessage());
        return;
      }
    }
    filterChain.doFilter(req, res);
  }
  public void setAuthentication(String username) {    // 시큐리티 컨텍스트 홀더에 넣어주기
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    Authentication authentication = createAuthentication(username);
    context.setAuthentication(authentication);
    SecurityContextHolder.setContext(context);
  }
  private Authentication createAuthentication(String username) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username); // 이때 디비에 갔다온다
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
}