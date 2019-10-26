package component;

import implementations.UserProfile;
import lombok.RequiredArgsConstructor;
import mapper.Profile;
import response.ProfileResponse;

@RequiredArgsConstructor
public class CreateProfileComponent {

    private final UserProfile userProfile;

    public ProfileResponse createProfile(Profile profile) {
        System.out.println("Creating profile "+profile);
        return userProfile.createProfile(profile);
    }
}
