package hu.elte.teamfinder;

import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.models.AccountDetails;
import hu.elte.teamfinder.security.AccountRole;
import hu.elte.teamfinder.utils.StringAccountRoleSetConverter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class TeamFinderUnitTests {

    @Test
    public void testStringSetConverterUtilAccountRoleSetToString() {
        StringAccountRoleSetConverter converter = new StringAccountRoleSetConverter();
        Set<AccountRole> dummySet = new HashSet<>();
        dummySet.add(AccountRole.STANDARD);
        dummySet.add(AccountRole.ADMIN);
        String expected = "ADMIN;STANDARD";

        String actual = converter.convertToDatabaseColumn(dummySet);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStringSetConverterUtilAccountRoleStringToSet() {
        StringAccountRoleSetConverter converter = new StringAccountRoleSetConverter();
        String str = "ADMIN;STANDARD";
        Set<AccountRole> expected = new HashSet<>();
        expected.add(AccountRole.STANDARD);
        expected.add(AccountRole.ADMIN);

        Set<AccountRole> actual = converter.convertToEntityAttribute(str);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAccountRoleGetGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("account:read"));
        authorities.add(new SimpleGrantedAuthority("account:write"));
        authorities.add(new SimpleGrantedAuthority("profile:read"));
        authorities.add(new SimpleGrantedAuthority("profile:write"));

        Set<SimpleGrantedAuthority> actualAuthorities = AccountRole.ADMIN.getGrantedAuthorities();

        Assert.assertEquals(authorities, actualAuthorities);
    }

    @Test
    public void testAccountUserDetailsAccountRolesToGrantedAuthoritySetFromAccount() {
        Account account = new Account("bob", "password");
        Set<SimpleGrantedAuthority> newUserAuthorities =
                AccountRole.NEW_USER.getGrantedAuthorities();

        Set<SimpleGrantedAuthority> actualAuthorities =
                AccountDetails.accountRolesToGrantedAuthoritySet(account);

        Assert.assertEquals(newUserAuthorities, actualAuthorities);
    }

    @Test
    public void testAccountUserDetailsAccountRolesToGrantedAuthoritySetFromSet() {
        // Expected
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(AccountRole.ADMIN.getGrantedAuthorities());
        authorities.addAll(AccountRole.STANDARD.getGrantedAuthorities());
        // AccountRole set to pass to function
        Set<AccountRole> roles = new HashSet<>();
        roles.add(AccountRole.ADMIN);
        roles.add(AccountRole.STANDARD);

        Set<SimpleGrantedAuthority> actualAuthorities =
                AccountDetails.accountRolesToGrantedAuthoritySet(roles);

        Assert.assertEquals(authorities, actualAuthorities);
    }

    /*
    @Test
    public void testAccountUserDetailsAccountRolesToGrantedAuthoritySetFromStringArray()
    {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(AccountRole.ADMIN.getGrantedAuthorities());
        String[] strings = {"ADMIN"};

        Set<SimpleGrantedAuthority> actualAuthorities = AccountDetails.accountRolesToGrantedAuthoritySet(strings);

        Assert.assertEquals(authorities, actualAuthorities);

    }

     */
    @Test
    public void testPassingAccountToAccountUserDetails() {
        AccountDetails accountDetails;
        Set<? extends GrantedAuthority> newUserAuthorities =
                AccountRole.NEW_USER.getGrantedAuthorities();

        accountDetails = new AccountDetails(new Account("bob", "password"));

        Assert.assertEquals("bob", accountDetails.getUsername());
        Assert.assertEquals("password", accountDetails.getPassword());
        Assert.assertEquals(newUserAuthorities, accountDetails.getAuthorities());
    }

    //////////////////////////////
    // JwtUtil.java tests

}
