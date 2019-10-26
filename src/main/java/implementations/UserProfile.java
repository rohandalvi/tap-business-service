package implementations;

import interfaces.IProfile;
import lombok.AllArgsConstructor;
import mapper.Profile;
import response.ProfileResponse;

@AllArgsConstructor
public class UserProfile implements IProfile {

    private ProfileDao profileDao;

    @Override
    public ProfileResponse createProfile(Profile profile) {
        return profileDao.create(profile);
    }
}
