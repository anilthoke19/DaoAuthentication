package user.example.repositery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import user.example.entity.User;

public interface UserReposInterf extends JpaRepository<User,Long> {

	Optional<User> findByusername(String username);
	
//	Optional<User> findByUserNameorEmail(String username, String email);
	
//	Optional<User> findByemail(String email);
//	
//	Boolean existsusername(String username);
//	
	
	
}
