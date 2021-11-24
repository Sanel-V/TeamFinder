package hu.elte.teamfinder.controllers;

import hu.elte.teamfinder.models.Profile;
import hu.elte.teamfinder.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Profile> getProfileById(@PathVariable("id") Integer id) {
        Profile profile = profileService.getProfileById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/allprofiles")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.findAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
        Profile updatedProfile = profileService.updateProfile(profile);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    // Deleting profile is only possible if you delete the account
}
