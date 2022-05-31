package com.project.productservice.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Slf4j
public class Interceptor implements HandlerInterceptor {
	static Claims claims ;
	static String username;
	static String authority;

	static String token;

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	public static String getToken() {
		return token;
	}

	public static String getUsername() {
		return username;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url = UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request)).build().toString();

		log.info(url);

		String header = request.getHeader("Authorization");
		token = header.replace("Bearer", "");
		claims = Jwts.parser()
	             .setSigningKey(secretKey.getBytes())
	             .parseClaimsJws(token)
	             .getBody();
		username = claims.getSubject();
//		System.out.println(username);
        List<Map<String, String>> authoritiesMap=(List<Map<String, String>>) claims.get("auth");
        Map<String,String> map =authoritiesMap.get(0);
        authority =map.get("authority");

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
}
