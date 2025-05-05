package com.rasel.bank_management.config;


import com.rasel.bank_management.model.JwtUserDetails;
import com.rasel.bank_management.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
                                   @Nonnull HttpServletRequest request,
                                   @Nonnull HttpServletResponse response,
                                   @Nonnull FilterChain filterChain)
            throws ServletException, IOException {
            try{
                String jwtToken = getJwtFromRequest(request);

                if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)){
                    Claims claims = jwtTokenProvider.getClaimsFromToken(jwtToken);
                    String username = claims.getSubject();
                    Integer userId = claims.get("id", Integer.class);
                    String email = claims.get("email", String.class);
                    String roleName = claims.get("role", String.class);

                    List<GrantedAuthority> authorities = Collections.singletonList(
                          new SimpleGrantedAuthority("ROLE_" + roleName)
                    );

                    UsernamePasswordAuthenticationToken authenticain =
                            new UsernamePasswordAuthenticationToken(
                                    new JwtUserDetails(userId, username, email, roleName),
                                    null,
                                    authorities
                            );
                    authenticain.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticain);
                }
            }catch (Exception e){
                log.error("Could not set user authentication in security context", e);
            }
            filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
