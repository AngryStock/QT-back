package qt.qr_backend.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import qt.qr_backend.domain.Ceo;

import java.util.ArrayList;
import java.util.Collection;

public class CeoDetails implements UserDetails {
    private final CeoDTO ceo;

    public CeoDetails(CeoDTO ceo) {
        this.ceo = ceo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return ceo.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return ceo.getPassword();
    }

    @Override
    public String getUsername() {
        return ceo.getLoginId();
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
}
