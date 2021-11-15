package hu.elte.teamfinder;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.AccountUserDetails;
import hu.elte.teamfinder.security.AccountRole;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class TeamFinderModelTests
{
    @Test
    public void testAccountSetOfGrantedAuthorities()
    {
        AccountUserDetails accountUserDetails = new AccountUserDetails(new AccountModel());
        Set<? extends GrantedAuthority> studentAuthorities = AccountRole.STANDARD.getGrantedAuthorities();
        Assert.assertEquals(studentAuthorities, accountUserDetails.getAuthorities());
    }

}
