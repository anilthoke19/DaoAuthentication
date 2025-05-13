package user.example.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import user.example.security.CustomUserDetailServ;

@Configuration
@EnableAutoConfiguration
public class SecurityConfigu {

	public CustomUserDetailServ userDetailsService;
	
	// autowired 
	public SecurityConfigu(CustomUserDetailServ userDetailsService)
	{
		 this.userDetailsService=userDetailsService;
	}


    @Bean
    PasswordEncoder passwordEncode()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	               .userDetailsService(userDetailsService)
	               .passwordEncoder(passwordEncode())
	               .and()
	               .build();
	    
	}

	
	
	@Bean 
	  SecurityFilterChain filterChan(HttpSecurity http) throws Exception
	{
		
		http.csrf(crsf->crsf.disable()).authorizeHttpRequests(
				(auth)->auth
				//.requestMatchers("/view/**").hasRole("ADMIN")
				//.requestMatchers("/contact2").hasAuthority("USER")
				.requestMatchers("/view").hasAnyRole("ADMIN","PUBLIC","USER")
				.requestMatchers("/public/**").permitAll()
				.anyRequest().authenticated()
				)
		
		
.httpBasic(Customizer.withDefaults())                    // ✅ Basic Auth for tools like Postman
         .formLogin(AbstractHttpConfigurer::disable);             // ✅ Disable form login for API clients

	        return http.build();
		
	}
	
	
	
}
