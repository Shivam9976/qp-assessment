package com.grocery_shop.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(unique = true)
	private String emailId;
	@Column
	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "user_detail_role",
        joinColumns = @JoinColumn(name = "user_detail_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
	private List<Role> role;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserAddress address;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.stream().map(i -> new SimpleGrantedAuthority(i.getRole())).toList();
	}

	@Override
	public String getUsername() {
		return emailId;
	}
	
	@Override
    public String getPassword() {
        return password;
    }

}
