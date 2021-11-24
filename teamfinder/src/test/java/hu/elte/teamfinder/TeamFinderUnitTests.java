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
    public void testAccountUserDetailsAccountRolesToGrantedAuthoritySet() {
        Account account = new Account("bob", "password");
        Set<SimpleGrantedAuthority> standardAuthorities =
                AccountRole.STANDARD.getGrantedAuthorities();

        Set<SimpleGrantedAuthority> actualAuthorities =
                AccountDetails.accountRolesToGrantedAuthoritySet(account);

        Assert.assertEquals(standardAuthorities, actualAuthorities);
    }

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
}
