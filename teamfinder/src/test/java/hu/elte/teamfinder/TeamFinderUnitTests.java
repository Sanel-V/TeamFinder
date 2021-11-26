package hu.elte.teamfinder;

import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.models.AccountDetails;
import hu.elte.teamfinder.security.AccountRole;
import hu.elte.teamfinder.utils.StringSetConverter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class TeamFinderUnitTests {

    @Test
    public void testStringSetConverterUtilSetToString() {
        StringSetConverter converter = new StringSetConverter();
        Set<String> dummySet = new HashSet<>();
        dummySet.add("one");
        dummySet.add("one");
        dummySet.add("two");
        dummySet.add("three");
        String expected = "one;two;three";

        String actual = converter.convertToDatabaseColumn(dummySet);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStringSetConverterUtilStringToSet() {
        StringSetConverter converter = new StringSetConverter();
        String str = "one;two;three";
        Set<String> expected = new HashSet<>();
        expected.add("one");
        expected.add("one");
        expected.add("two");
        expected.add("three");

        Set<String> actual = converter.convertToEntityAttribute(str);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAccountRoleGetGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("account:read"));
        authorities.add(new SimpleGrantedAuthority("account:write"));

        Set<SimpleGrantedAuthority> actualAuthorities = AccountRole.ADMIN.getGrantedAuthorities();

        Assert.assertEquals(authorities, actualAuthorities);
    }

    @Test
    public void testAccountUserDetailsAccountRolesToGrantedAuthoritySetFromAccount() {
        Account account = new Account("bob", "password");
        Set<SimpleGrantedAuthority> standardAuthorities =
                AccountRole.STANDARD.getGrantedAuthorities();

        Set<SimpleGrantedAuthority> actualAuthorities =
                AccountDetails.accountRolesToGrantedAuthoritySet(account);

        Assert.assertEquals(standardAuthorities, actualAuthorities);
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
        Set<? extends GrantedAuthority> studentAuthorities =
                AccountRole.STANDARD.getGrantedAuthorities();

        accountDetails = new AccountDetails(new Account("bob", "password"));

        Assert.assertEquals("bob", accountDetails.getUsername());
        Assert.assertEquals("password", accountDetails.getPassword());
        Assert.assertEquals(studentAuthorities, accountDetails.getAuthorities());
    }

    //////////////////////////////
    // JwtUtil.java tests

}
