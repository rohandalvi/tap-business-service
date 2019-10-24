package implementations;

import interfaces.IProfile;
import lombok.AllArgsConstructor;
import mapper.Profile;
import response.ProfileResponse;

@AllArgsConstructor
public class UserProfile implements IProfile {

    ProfileDao profileDao;
    @Override
    public ProfileResponse createProfile(String userId, String email, String firstName, String lastName) {
        Profile profile = new Profile(userId, email, firstName, lastName);
        return profileDao.create(profile);
    }
}
