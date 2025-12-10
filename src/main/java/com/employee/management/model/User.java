package com.employee.management.model;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	
	private String email;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="users_roles", 
	joinColumns = @JoinColumn(
			name="user_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(
			name="role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
	
	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.email = email;
	    this.password = password;
	    this.roles = roles;
	}
}
