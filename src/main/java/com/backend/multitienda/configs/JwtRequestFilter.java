package com.backend.multitienda.configs;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  private final JwtTokenUtil jwtTokenUtil;

  public JwtRequestFilter(JwtTokenUtil jwtTokenUtil) {
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader("Authorization");

    String emailUsuario = null;
    String jwtToken = null;

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);

      try {
        emailUsuario = jwtTokenUtil.getUsernameFromToken(jwtToken);
      } catch (IllegalArgumentException e) {
        System.out.println("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        System.out.println("JWT Token has expired");
      }
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
    }

    if (emailUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(emailUsuario);

      if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(request, response);

  }
}
