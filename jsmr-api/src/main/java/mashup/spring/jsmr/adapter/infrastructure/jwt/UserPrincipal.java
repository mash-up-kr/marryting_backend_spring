package mashup.spring.jsmr.adapter.infrastructure.jwt;

import mashup.spring.jsmr.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static mashup.spring.jsmr.domain.user.UserRole.USER;

public class UserPrincipal implements UserDetails {

    private final Long userId;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long userId, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        var authorities = Collections.singletonList(new SimpleGrantedAuthority(USER.name()));
        return new UserPrincipal(user.getId(), authorities);
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}

