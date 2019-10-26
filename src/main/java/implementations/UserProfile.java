package implementations;

import interfaces.IProfile;
import lombok.AllArgsConstructor;
import mapper.Profile;
import response.ProfileResponse;

@AllArgsConstructor
public class UserProfile implements IProfile {

    private ProfileDao profileDao;
    @Override
    public ProfileResponse createProfile(String userId, String email, String firstName, String lastName) {
        Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setEmail(email);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        return profileDao.create(profile);
    }
}
