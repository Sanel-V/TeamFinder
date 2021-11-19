package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.ProfileModel;
import hu.elte.teamfinder.repos.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileModelService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileModelService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileModel createProfile(ProfileModel profile) {
        return profileRepository.save(profile);
    }

    public ProfileModel getProfileById(Integer id) {
        return profileRepository.getById(id);
        /*
        return profileRepository.getAccountById(id).orElseThrow();*/
    }

    public List<ProfileModel> findAllProfiles() {
        return profileRepository.findAll();
    }

    public ProfileModel updateProfile(ProfileModel profile) {
        return profileRepository.save(profile);
    }

    public Boolean deleteProfile(Integer id) {
        profileRepository.deleteById(id);
        return true;
    }
}
