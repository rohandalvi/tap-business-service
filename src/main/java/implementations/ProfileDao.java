package implementations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import lombok.extern.slf4j.Slf4j;
import mapper.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.ProfileResponse;
import response.ResponseCode;

import java.util.List;
import java.util.stream.Collectors;


public class ProfileDao {

    private static Logger log = LoggerFactory.getLogger(ProfileDao.class);
    AmazonDynamoDBClient client;
    DynamoDBMapper dynamoDBMapper;
    public ProfileDao(AmazonDynamoDBClient client) {
        this.client = client;
        dynamoDBMapper = new DynamoDBMapper(client);
    }

    public void createTable(Class clazz) {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(clazz);
        createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
        try {
            System.out.println("Creating table "+createTableRequest);
            client.createTable(createTableRequest);
        } catch (ResourceInUseException e) {

            // thrown if the table already exists, in which case it is a no-op.
            System.out.println("Table already created "+clazz.getCanonicalName());
        } catch (Exception e) {
            System.out.println("Some exception occurred " + e);
        }

    }

    public ProfileResponse create(Profile profile) {
        createTable(Profile.class);
        System.out.println("DynamoMapper "+dynamoDBMapper);
        try {
            dynamoDBMapper.save(profile);
        } catch (Exception e) {
            System.out.println("Encountered exception "+e);
        }

        return new ProfileResponse(ResponseCode.OK);
    }

    public Profile get(String userId) {
        PaginatedQueryList query = dynamoDBMapper.query(Profile.class, buildQueryExpression(userId));
        Profile profile = (Profile) query.stream().findFirst().get();
        return profile;
    }

    private DynamoDBQueryExpression buildQueryExpression(String userId) {
        Profile profile = new Profile();
        profile.setUserId(userId);
        return new DynamoDBQueryExpression<Profile>().withHashKeyValues(profile);
    }
}
