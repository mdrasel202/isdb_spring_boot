package com.rasel.bank_management.model;

import com.rasel.bank_management.constants.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class JwtUserDetails implements UserDetails {

    private final Integer id;
    private final String username;
    private final String email;
    private final Role role;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(Integer id, String username, String email, Role role, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
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
  /*  private final Integer id;
    private final String username;
    private final String email;
    private final Role role;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(Integer id, String username, String email, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));

        @Override
        public Collection<? extends GrantedAuthority> grantedAuthorities(){
            return authorities;
        }


    }
*/

}
