package implementations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import mapper.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.ProfileResponse;
import response.ResponseCode;


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
            System.out.println("Profile object "+profile);
            dynamoDBMapper.save(profile);
        } catch (Exception e) {
            System.out.println("Encountered exception "+e);
        }

        return new ProfileResponse(ResponseCode.OK);
    }

    public Profile get(Profile profile) {
        try {
            PaginatedQueryList query = dynamoDBMapper.query(Profile.class, buildQueryExpression(profile));
            Profile profile1 = (Profile) query.stream().findFirst().get();
            return profile1;
        } catch (Exception e) {
            System.out.println("Encountered exception "+e);
        }

    }

    private DynamoDBQueryExpression buildQueryExpression(Profile profile) {
        return new DynamoDBQueryExpression<Profile>().withHashKeyValues(profile);
    }
}
