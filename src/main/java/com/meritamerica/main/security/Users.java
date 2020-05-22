package com.meritamerica.main.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false) 
	private String password;
	
	private boolean enabled;
	
	private String roles = "ROLE_USER";
	
	/**
	 * 2 authorities: ADMIN_PRIVILEGE, USER_PRIVILEGE
	 */
	@NotEmpty
	private String authorities ;
	
	public Users(String username, String password) {
		this.username = username;
		this.password = password; 
		this.enabled = true;
	}
	
	
	public Users() {
		this.enabled = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	public String getAuthorities() {
		return authorities;
	}


	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	public List<String> getRoleList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		
		return new ArrayList<>();
	}
//	
//	public List<String> getPermissionList() {
//		if (this.authority.length() > 0) {
//			return Arrays.asList(this.authority.split(","));
//		}
//		
//		return new ArrayList<>();
//	}
	
	public List<GrantedAuthority> getAuthorityList() {
		if (this.authorities.length() > 0) {
			String[] arrStr = this.authorities.split(",");
			List<GrantedAuthority> authorityList = new ArrayList<>();
			for (String auth : arrStr) {
				authorityList.add(new Authority(auth));
			}
			
			return authorityList;
		}
		
		return new ArrayList<GrantedAuthority>();

	}
}
