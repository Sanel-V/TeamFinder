package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.AccountUserDetails;

import java.util.Optional;

public interface AccountRepository //TODO: extend JPA repository
{
    Optional<AccountUserDetails> selectAccountUserDetailsByEmail(String email);
}