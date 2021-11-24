package hu.elte.teamfinder;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.AccountUserDetails;
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
        AccountModel account = new AccountModel("bob", "password");
        Set<SimpleGrantedAuthority> standardAuthorities =
                AccountRole.STANDARD.getGrantedAuthorities();

        Set<SimpleGrantedAuthority> actualAuthorities =
                AccountUserDetails.accountRolesToGrantedAuthoritySet(account);

        Assert.assertEquals(standardAuthorities, actualAuthorities);
    }

    @Test
    public void testPassingAccountToAccountUserDetails() {
        AccountUserDetails accountUserDetails;
        Set<? extends GrantedAuthority> studentAuthorities =
                AccountRole.STANDARD.getGrantedAuthorities();

        accountUserDetails = new AccountUserDetails(new AccountModel("bob", "password"));

        Assert.assertEquals("bob", accountUserDetails.getUsername());
        Assert.assertEquals("password", accountUserDetails.getPassword());
        Assert.assertEquals(studentAuthorities, accountUserDetails.getAuthorities());
    }
}
