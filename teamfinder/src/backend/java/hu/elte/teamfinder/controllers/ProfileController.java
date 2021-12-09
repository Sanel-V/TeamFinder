package hu.elte.teamfinder.controllers;

import hu.elte.teamfinder.models.Profile;
import hu.elte.teamfinder.services.ProfileService;
import hu.elte.teamfinder.viewmodels.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService service) {
        this.profileService = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileViewModel> getProfileById(@PathVariable("id") Integer id) {
        Profile profile = profileService.getProfileById(id);
        return new ResponseEntity<>(new ProfileViewModel(profile), HttpStatus.OK);
    }

    @GetMapping("/profile/all")
    public ResponseEntity<List<ProfileViewModel>> getAllProfiles() {
        List<ProfileViewModel> profileViewModels = new ArrayList<>();

        List<Profile> profiles = profileService.findAllProfiles();
        profiles.forEach(profile -> profileViewModels.add(new ProfileViewModel(profile)));

        return new ResponseEntity<>(profileViewModels, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileViewModel> updateProfile(@RequestBody Profile profile) {
        Profile updatedProfile = profileService.updateProfile(profile);
        return new ResponseEntity<>(new ProfileViewModel(updatedProfile), HttpStatus.OK);
    }

    // Deleting profile is only possible if you delete the account
}
