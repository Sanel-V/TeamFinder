package hu.elte.teamfinder.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum AccountRole {
    ADMIN(
            new HashSet<AccountPermission>(
                    List.of(
                            AccountPermission.ACCOUNT_WRITE,
                            AccountPermission.ACCOUNT_READ,
                            AccountPermission.PROFILE_WRITE,
                            AccountPermission.PROFILE_READ))),
    STANDARD(
            new HashSet<AccountPermission>(
                    List.of(
                            AccountPermission.ACCOUNT_WRITE,
                            AccountPermission.ACCOUNT_READ,
                            AccountPermission.PROFILE_WRITE,
                            AccountPermission.PROFILE_READ))),
    NEW_USER(
            new HashSet<AccountPermission>(
                    List.of(
                            AccountPermission.ACCOUNT_WRITE,
                            AccountPermission.ACCOUNT_READ,
                            AccountPermission.PROFILE_WRITE,
                            AccountPermission.PROFILE_READ)));

    private final Set<AccountPermission> permissions;

    AccountRole(Set<AccountPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AccountPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions =
                getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
