package com.api.org.security;

import com.api.org.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
public class UserPrincipal implements UserDetails {
  

	private Long id;
	
	private String name;
	
    private String username;
    
    @JsonIgnore
    private String password; 

    private String email;
    private String ani;	
    
	@JsonIgnore
    private Integer roles; 
	
	@JsonIgnore
    private Integer status;
    

	@JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;
	
    public UserPrincipal()
    {
    	
    }

    public UserPrincipal(Long id,String username, String email, String password,Integer roles, Collection<? extends GrantedAuthority> authorities,
    		String name,String ani,Integer status) {
        this.id = id;
       
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.roles=roles;
        this.name=name;
        this.ani=ani;
        this.status=status;
    }

    public static UserPrincipal create(Users user) {
        List<GrantedAuthority> authorities = null;
        	        return new UserPrincipal(
	                user.getId(),
	                user.getUserName(),
	                user.getEmail(),
	                user.getPassword(),
	                user.getRole(),
	                authorities,
	                user.getName(),
	                user.getAni(),
	                user.getStatus()
	        );
      
    }

    public Integer getRoles() {
		return roles;
	}

	public void setRoles(Integer roles) {
		this.roles = roles;
	}

	public Long getId() {
        return id;
    }

   
    
 

    

	@Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

	@Override
	public String toString() {
		return "UserPrincipal [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", ani=" + ani + ", name=" + name + ", roles=" + roles
				+ ", authorities=" + authorities + "]";
	}
	
	public UserPrincipal(Users user) 
	{		
		this.email=user.getEmail();
		this.ani=user.getAni();
		this.username=user.getUserName();
		this.name=user.getName();
		this.roles=user.getRole();
		this.id=user.getId();
		this.status=user.getStatus();
	}



	
	
	

	
    
}
