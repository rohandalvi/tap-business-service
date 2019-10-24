package interfaces;

import response.ProfileResponse;

public interface IProfile {

    ProfileResponse createProfile(String userId, String password, String firstName, String lastName);

}
