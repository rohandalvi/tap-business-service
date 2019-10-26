package component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import implementations.ProfileDao;
import lombok.RequiredArgsConstructor;
import mapper.Profile;
import response.ProfileResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RequiredArgsConstructor
public class CreateProfileComponent {

    private final ProfileDao profileDao;

    public ProfileResponse createProfile(Profile profile) {
        return profileDao.create(profile);
    }
}
