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
            log.info("Creating table {}", createTableRequest);
            client.createTable(createTableRequest);
        } catch (ResourceInUseException e) {

            // thrown if the table already exists, in which case it is a no-op.
            log.info("Table already created {}", clazz.getCanonicalName());
        } catch (Exception e) {
            log.error("Some exception occurred ", e);
        }

    }

    public ProfileResponse create(Profile profile) {
        createTable(Profile.class);
        try {
            dynamoDBMapper.save(profile);
        } catch (Exception e) {
            log.error("Encountered exception ", e);
        }

        return new ProfileResponse(ResponseCode.OK);
    }

    public Profile get(Profile profile) {
        try {
            PaginatedQueryList query = dynamoDBMapper.query(Profile.class, buildQueryExpression(profile));
            Profile profile1 = (Profile) query.stream().findFirst().get();
            return profile1;
        } catch (Exception e) {
            log.error("Encountered exception ", e);
        }
        return null;
    }

    private DynamoDBQueryExpression buildQueryExpression(Profile profile) {
        return new DynamoDBQueryExpression<Profile>().withHashKeyValues(profile);
    }
}
