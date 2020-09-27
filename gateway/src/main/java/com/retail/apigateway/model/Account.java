package com.retail.apigateway.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Account implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Integer age;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN,
        SEEKER,
        JOB_CENTER
    }

	
	public Account(Long id, String username, String password, String firstName, String lastName, String email,
			Integer age, String address) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", age=" + age + ", address=" + address
				+ ", role=" + role + "]";
	}
}
