package com.api.org.security;

import com.api.org.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserPrincipal implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String name;
    private String email;
    private String ani;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Integer role;

    @JsonIgnore
    private Integer status;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal() {
    }

    private UserPrincipal(
            Long id,
            String username,
            String password,
            Integer role,
            Collection<? extends GrantedAuthority> authorities,
            String name,
            String email,
            String ani,
            Integer status) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.name = name;
        this.email = email;
        this.ani = ani;
        this.status = status;
    }

    public static UserPrincipal create(Users user) {

        // Map integer role → Spring Security authority
        GrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + user.getRole());

        return new UserPrincipal(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getRole(),
                Collections.singletonList(authority),
                user.getName(),
                user.getEmail(),
                user.getAni(),
                user.getStatus()
        );
    }

 
    public Long getId() {
        return id;
    }

    public Integer getRole() {
        return role;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    

    public String getName() {
        return name;
    }
    public String getAni() {
        return ani;
    }
    public Integer getStatus() {
        return status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // NEVER null
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
        return status == null || status == 1;
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
}
