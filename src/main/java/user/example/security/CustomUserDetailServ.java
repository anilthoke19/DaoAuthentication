package user.example.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import user.example.entity.User;
import user.example.repositery.UserReposInterf;

@Service
public class CustomUserDetailServ  implements  UserDetailsService {
	
	private UserReposInterf userRepository;
	
	public CustomUserDetailServ(UserReposInterf userRepository)
	{
		this.userRepository= userRepository;
		
		
	}

	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("USERNAME i "+" "+username);
		User user=userRepository.findByusername(username)
				.orElseThrow(()->new RuntimeException(username+" not present in list "));
		
		
		Set<GrantedAuthority> authority = user.getRoles().stream()
			    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase()))  // Ensure ROLE_ prefix
			    .collect(Collectors.toSet());
	
//		Set<GrantedAuthority> authority=user.getRoles().stream().
//				map((role)-> new SimpleGrantedAuthority(role.getRole().toUpperCase())).
//				collect(Collectors.toSet());
//		System.out.println("It is password "+user.getPassword() +" "+user.getUsername()+" "+ user.getRoles());
//		
//		for (GrantedAuthority role : authority) {
//		    System.out.println("These are role "+role);
//		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authority);
		
	}

}
