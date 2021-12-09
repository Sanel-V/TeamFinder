package hu.elte.teamfinder.viewmodels;

import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.security.AccountRole;

import java.util.Set;

public class AccountViewModel {
    private String email;
    private String password;
    private Set<AccountRole> roles;
    private boolean isEnabled;

    public AccountViewModel(Account account) {
        email = account.getEmail();
        password = account.getPassword();
        roles = account.getRoles();
        isEnabled = account.isEnabled();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AccountRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AccountRole> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
