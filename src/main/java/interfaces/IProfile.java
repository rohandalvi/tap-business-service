package interfaces;

import mapper.Profile;
import response.ProfileResponse;

public interface IProfile {

    ProfileResponse createProfile(Profile profile);

}
