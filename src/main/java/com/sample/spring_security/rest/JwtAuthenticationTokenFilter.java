
package com.sample.spring_security.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.sample.spring_security.entities.UserEntity;
import com.sample.spring_security.service.JwtService;
import com.sample.spring_security.service.UserService;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	private final static String TOKEN_HEADER = "authorization";

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(TOKEN_HEADER);

		if (jwtService.validateTokenLogin(authToken)) {
			String username = jwtService.getUsernameFromToken(authToken);

			UserEntity user = userService.loadUserByUsername(username);
			if (user != null) {
				boolean enabled = true;
				boolean accountNonExpired = true;
				boolean credentialsNonExpired = true;
				boolean accountNonLocked = true;

				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(user.getRole()));

				UserDetails userDetail = new User(username, user.getPassword(), enabled, accountNonExpired,
						credentialsNonExpired, accountNonLocked, authorities);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
						null, userDetail.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}
}