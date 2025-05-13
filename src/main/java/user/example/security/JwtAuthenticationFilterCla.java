package user.example.security;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilterCla extends OncePerRequestFilter {

	UserDetailsService userDetails;
	
	JwtTokenProvider tokenProvider;
	
	public JwtAuthenticationFilterCla(UserDetailsService userDetails,JwtTokenProvider tokenProvider)
	{
		this.userDetails=userDetails;
		this.tokenProvider=tokenProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		
	}

}
