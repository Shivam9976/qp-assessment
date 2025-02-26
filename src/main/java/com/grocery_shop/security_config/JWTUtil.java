package com.grocery_shop.security_config;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTUtil {

	private final String SECRET_KEY = "8Zz5tw0Ionm3XPZZfN0NOml3z9FMfmpgXwovR9fp6ryDIoGRM8EPHAB6iHsc0fb";
	
	public String generateToken(UserDetails userDetail) {
		return Jwts.builder().setSubject(userDetail.getUsername())
				.claim("roles", userDetail.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		log.info(userDetails.getUsername());
		//log.info(isTokenExpired(token) ? "true" : "false");
		return (username.equals(userDetails.getUsername()));
	}

	    public Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(token)
	                .getBody();
	    }
	    
	    public String extractUsername(String token) {
	        return extractAllClaims(token).getSubject();
	    }

	    public List<String> extractRoles(String token) {
	        List<?> rawRoles = extractAllClaims(token).get("roles", List.class);

	        return rawRoles.stream()
	            .map(role -> {
	                if (role instanceof Map) {
	                    return (String) ((Map<?, ?>) role).get("authority"); // Extract role name
	                }
	                return role.toString(); // Fallback for String roles
	            })
	            .collect(Collectors.toList());
	    }

//	    public boolean isTokenExpired(String token) {
//	        return extractAllClaims(token).getExpiration().before(new Date());
//	    }
}
