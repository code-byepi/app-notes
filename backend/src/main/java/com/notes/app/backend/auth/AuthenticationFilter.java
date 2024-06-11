package com.notes.app.backend.auth;

import com.notes.app.backend.entities.Usuario;
import com.notes.app.backend.services.CustomUserDetailService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailService customUserDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");

		System.out.println("Request URI: " + request.getRequestURI());
		System.out.println("Authorization Header: " + requestTokenHeader);

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = jwtUtils.extractUsername(jwtToken);
			} catch (MalformedJwtException e) {
				System.out.println("Invalid JWT token format: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("JWT token extraction error: " + e.getMessage());
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				Usuario userDetails = this.customUserDetailsService.loadUserByUsername(username);
				if (jwtUtils.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				} else {
					System.out.println("Token is not validated...");
				}
			}
		} else {
			System.out.println("JWT Token does not begin with Bearer String or is null");
		}

		filterChain.doFilter(request, response);
	}
}
