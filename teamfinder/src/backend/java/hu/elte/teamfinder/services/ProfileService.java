package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.Profile;
import hu.elte.teamfinder.repos.ProfileRepository;
import hu.elte.teamfinder.security.AccountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AccountService accountService;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, AccountService accountService) {
        this.profileRepository = profileRepository;
        this.accountService = accountService;
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getProfileById(Long id) {
        return profileRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new UsernameNotFoundException(
                                        String.format("User with id %d not found", id)));
    }

    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }

    /**
     * Update profile credentials. If the user's profile data is only partial, user will be given a
     * NEW_USER role, preventing access to most features of this webapp
     *
     * @param id ID of profile to update
     * @param profile updated profile data
     * @return updated profile
     * @throws UsernameNotFoundException if there's no profile with given id
     */
    public Profile updateProfile(Long id, Profile profile) throws UsernameNotFoundException {
        Profile profileToUpdate = profileRepository.findById(id).get();
        if (profile.getFirstName() != null) {
            profileToUpdate.setFirstName(profile.getFirstName());
        }
        if (profile.getLastName() != null) {
            profileToUpdate.setLastName(profile.getLastName());
        }
        if (profile.getAge() != null) {
            profileToUpdate.setAge(profile.getAge());
        }
        if (profile.getTags() != null) {
            profileToUpdate.setTags(profile.getTags());
        }
        if (profile.getSummary() != null) {
            profileToUpdate.setSummary(profile.getSummary());
        }
        // If data is missing, don't change user role
        // Only name is required
        if (profileToUpdate.getFirstName() == null || profileToUpdate.getLastName() == null) {
            // Don't allow partial profiles to be public
            profileToUpdate.setPublic(false);
            accountService.addAccountRoles(
                    profileToUpdate.getAccountId(),
                    new HashSet<AccountRole>(Arrays.asList(AccountRole.NEW_USER)));
        } else {
            profileToUpdate.setPublic(profile.getPublic());
            accountService.removeAccountRoles(
                    profileToUpdate.getAccountId(),
                    new HashSet<AccountRole>(Arrays.asList(AccountRole.NEW_USER)));
        }

        return profileRepository.save(profile);
    }

    public Boolean deleteProfile(Long id) {
        profileRepository.deleteById(id);
        return true;
    }
}
