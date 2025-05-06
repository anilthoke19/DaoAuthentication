package user.example.repositery;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import user.example.entity.Role;

public interface RolesRepoInterf extends JpaRepository<Role, Long> {

	
	 Optional<Role> findByRole(String role);
	
}
