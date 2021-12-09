package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.Profile;
import hu.elte.teamfinder.repos.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getProfileById(Integer id) {
        return profileRepository.getById(id);
        /*
        return profileRepository.getAccountById(id).orElseThrow();*/
    }

    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Boolean deleteProfile(Integer id) {
        profileRepository.deleteById(id);
        return true;
    }
}
