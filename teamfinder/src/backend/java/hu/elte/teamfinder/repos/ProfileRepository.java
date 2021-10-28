package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.ProfileModel;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    Optional<ProfileModel> getAccountById(Integer id);

    List<ProfileModel> getAllProfiles();

    Optional<ProfileModel> updateProfile(ProfileModel profile);
}
