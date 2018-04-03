package com.github.herowzz.springfuse.example.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public abstract class JwtUtil {

	public static String createJWT(String userId, long ttlMillis) {
		return createJWT(userId, 0, ttlMillis);
	}

	public static String createJWT(String userId, Integer userType, long ttlMillis) {
		long expMillis = System.currentTimeMillis() + ttlMillis;
		String jwtToken = Jwts.builder().setIssuer(SecurityConstant.ISS_USER).setSubject(userId).claim("userType", userType).setIssuedAt(new Date()).setExpiration(new Date(expMillis)).signWith(SignatureAlgorithm.HS256, SecurityConstant.SECRET_KEY).compact();
		return jwtToken;
	}

	public static Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(SecurityConstant.SECRET_KEY).parseClaimsJws(token).getBody();
	}

}
