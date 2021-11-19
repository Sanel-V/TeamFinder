package hu.elte.teamfinder.controllers;

import hu.elte.teamfinder.models.ProfileModel;
import hu.elte.teamfinder.services.ProfileModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileModelService profileService;

    @Autowired
    public ProfileController(ProfileModelService service) {
        this.profileService = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileModel> getProfileById(@PathVariable("id") Integer id) {
        ProfileModel profile = profileService.getProfileById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/allprofiles")
    public ResponseEntity<List<ProfileModel>> getAllProfiles() {
        List<ProfileModel> profiles = profileService.findAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileModel> updateProfile(@RequestBody ProfileModel profile) {
        ProfileModel updatedProfile = profileService.updateProfile(profile);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    // Deleting profile is only possible if you delete the account
}
