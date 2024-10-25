package it.ultraistinct.ordersmanagement.domain.user.entity;

import it.ultraistinct.ordersmanagement.domain.enums.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class User implements UserDetails {

    @Id
    private Long id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;

    private String role;
    private boolean enabled = true;

    public UserRoleEnum getRole() {
        return UserRoleEnum.fromString(this.role);
    }

    public void setRole(UserRoleEnum userRoleEnum) {
        this.role = userRoleEnum.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
