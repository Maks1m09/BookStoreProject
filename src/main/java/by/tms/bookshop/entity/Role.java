package by.tms.bookshop.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER_ROLE, ADMIN_ROLE;

    @Override
    public String getAuthority() {
        return name();
    }
}
