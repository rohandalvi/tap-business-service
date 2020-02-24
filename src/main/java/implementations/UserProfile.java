package implementations;

import common.Keys;
import interfaces.IProfile;
import lombok.AllArgsConstructor;
import mapper.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.ProfileResponse;
import spark.Request;

@AllArgsConstructor
public class UserProfile implements IProfile {

    private static final Logger log = LoggerFactory.getLogger(UserProfile.class);
    private ProfileDao profileDao;

    @Override
    public ProfileResponse createProfile(Request request) {
        log.info("Creating profile from UserProfile class {}", request);
        System.out.println(String.format("Creating profile from UserProfile class %s ", request));
        return profileDao.create(transform(request));
    }

    @Override
    public Profile getProfile(Request request) {
        return profileDao.get(transform(request));
    }

    private Profile transform(Request request) {
        String userId = request.queryParams(Keys.USER_ID);
        String email = request.queryParams(Keys.EMAIL);
        String firstName = request.queryParams(Keys.FIRST_NAME);
        String lastName = request.queryParams(Keys.LAST_NAME);

        Profile profile = new Profile();
        if ( userId!=null ) {
            profile.setUserId(userId);
        }
        if ( email!=null ) {
            profile.setEmail(email);
        }
        if ( firstName != null) {
            profile.setFirstName(firstName);
        }
        if ( lastName!=null ) {
            profile.setLastName(lastName);
        }

        return profile;
    }
}
