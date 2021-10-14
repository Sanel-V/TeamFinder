package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.ProfileModel;

import java.util.ArrayList;
import java.util.Optional;

public interface ProfileRepository {
    Optional<ProfileModel> getAccountById(Integer id);

    ArrayList<ProfileModel> getAllProfiles();
}
