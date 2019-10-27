package interfaces;

import mapper.Profile;
import response.ProfileResponse;
import spark.Request;

public interface IProfile {

    ProfileResponse createProfile(Request request);

    Profile getProfile(Request request);

}
