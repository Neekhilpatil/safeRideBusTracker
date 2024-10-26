package com.saferidetracker.model;

import jakarta.persistence.*;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Default constructor
    public User() {}

    // Fluent setter for fullName
    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this; // Return the current instance (User) for method chaining
    }

    // Fluent setter for email
    public User setEmail(String email) {
        this.email = email;
        return this; // Return the current instance (User) for method chaining
    }

    // Fluent setter for password
    public User setPassword(String password) {
        this.password = password;
        return this; // Return the current instance (User) for method chaining
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

    // Other getters and setters (if needed)
//	@ManyToOne(cascade = CascadeType.REMOVE)
//	@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
//	private Role role;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	
	public Role getRole() {
	    return role;
	}

	public User setRole(Role role) {
	    this.role = role;
	    
	    return this;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName().toString());
	        
	    return List.of(authority);
	}
    
}

