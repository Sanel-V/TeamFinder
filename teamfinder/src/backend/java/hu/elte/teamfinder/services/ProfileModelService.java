package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.ProfileModel;
import hu.elte.teamfinder.repos.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileModelService {

    private final ProfileRepository profileRepository;

    @Autowired
    public  ProfileModelService(@Qualifier("fakeProfile") ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public ProfileModel getProfileById(Integer id) {
        return profileRepository.getAccountById(id).orElseThrow();
    }

    public List<ProfileModel> getAllProfiles() {
        return profileRepository.getAllProfiles();
    }

    public ProfileModel updateProfile(ProfileModel profile) {
        return profileRepository.updateProfile(profile).orElseThrow();
    }
}
