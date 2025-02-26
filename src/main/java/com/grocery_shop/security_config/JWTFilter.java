package com.grocery_shop.security_config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import com.grocery_shop.entity.UserDetail;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JWTFilter extends GenericFilterBean {

	@Autowired
	JWTUtil jwtUtil;
	@Autowired
	UserDetailsService userDetailsService;

	public JWTFilter(JWTUtil jwtUtil, org.springframework.security.core.userdetails.UserDetailsService userDetailsService2) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService2;
    }
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        log.info("Authorization Header: " + authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " part
            String username = jwtUtil.extractUsername(token);
            List<String> roles = jwtUtil.extractRoles(token);  // Extract roles
            log.info(roles.toString());
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, userDetails)) {
                    // Map roles to SimpleGrantedAuthority (e.g. ROLE_ADMIN, ROLE_USER)
                    List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority(role))  // Ensure roles have "ROLE_" prefix
                        .collect(Collectors.toList());

                    // Create the authentication object with user details and roles
                    UsernamePasswordAuthenticationToken authenticationToken = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                    // Set the authentication in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        // Continue the filter chain
        chain.doFilter(request, response);
    }

}
