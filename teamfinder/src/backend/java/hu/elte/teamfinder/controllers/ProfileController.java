package hu.elte.teamfinder.controllers;

import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.models.AccountDetails;
import hu.elte.teamfinder.models.Profile;
import hu.elte.teamfinder.services.ProfileService;
import hu.elte.teamfinder.viewmodels.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService service) {
        this.profileService = service;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProfileById(
            @PathVariable("id") Long id, Authentication authentication) {
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        Profile profile = profileService.getProfileById(id);
        if (profile.getPublic() || accountDetails.getAccountId() == id || isAdmin(authentication)) {
            return new ResponseEntity<ProfileViewModel>(
                    new ProfileViewModel(profile), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Access denied", FORBIDDEN);
        }
    }

    @GetMapping("profile/public")
    public ResponseEntity<List<ProfileViewModel>> getAllPublicProfiles() {
        List<ProfileViewModel> profileViewModels = new ArrayList<>();

        List<Profile> profiles = profileService.findAllProfiles();
        // only list public profiles
        profiles.forEach(
                profile -> {
                    if (profile.getPublic()) profileViewModels.add(new ProfileViewModel(profile));
                });

        return new ResponseEntity<>(profileViewModels, HttpStatus.OK);
    }

    @GetMapping("/profile/all")
    public ResponseEntity<?> getAllProfiles(Authentication authentication) {

        if (!isAdmin(authentication)) {
            return new ResponseEntity<String>("Access denied", FORBIDDEN);
        }
        List<ProfileViewModel> profileViewModels = new ArrayList<>();

        List<Profile> profiles = profileService.findAllProfiles();
        profiles.forEach(profile -> profileViewModels.add(new ProfileViewModel(profile)));

        return new ResponseEntity<List<ProfileViewModel>>(profileViewModels, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProfile(
            @PathVariable Long id, @RequestBody Profile profile, Authentication authentication) {
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        if (!(accountDetails.getAccountId() == id || isAdmin(authentication))) {
            return new ResponseEntity<String>("Access denied", FORBIDDEN);
        }
        Profile updatedProfile;
        try {
            updatedProfile = profileService.updateProfile(id, profile);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ProfileViewModel>(
                new ProfileViewModel(updatedProfile), HttpStatus.OK);
    }

    private boolean isAdmin(Authentication authentication) {
        // Only allow if user is admin
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        if (accountDetails.getAuthorities().contains((new SimpleGrantedAuthority("ROLE_ADMIN")))) {
            return true;
        }
        return false;
    }
    // Deleting profile is only possible if you delete the account
}
