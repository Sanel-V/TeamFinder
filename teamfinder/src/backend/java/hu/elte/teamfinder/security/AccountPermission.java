package hu.elte.teamfinder.security;

import java.util.HashSet;
import java.util.List;

public enum AccountPermission {
    ACCOUNT_READ("account:read"),
    ACCOUNT_WRITE("account:write"),
    PROFILE_READ("profile:read"),
    PROFILE_WRITE("profile:write");

    private final String permission;

    AccountPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
