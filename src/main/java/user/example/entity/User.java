package user.example.entity;

import java.util.*;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid ;
	
	private String name;
	
@Column(nullable = false ,unique = true)
	private  String email;

@Column(nullable = false ,unique=true)
	private String username;

@Column(nullable = false)
	private String password ;


@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@JoinTable(name = "userRoleJoin",
joinColumns = @JoinColumn(name="userId" , referencedColumnName ="uid"),
inverseJoinColumns = @JoinColumn(name="roleId",referencedColumnName = "rId")
		)

public Set<Role> roles ;


public Long getUid() {
	return uid;
}


public void setUid(Long uid) {
	this.uid = uid;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public Set<Role> getRoles() {
	return roles;
}


public void setRoles(Set<Role> roles) {
	this.roles = roles;
}


public User(Long uid, String name, String email, String username, String password, Set<Role> roles) {
	super();
	this.uid = uid;
	this.name = name;
	this.email = email;
	this.username = username;
	this.password = password;
	this.roles = roles;
}


public User() {
	super();
}







}
