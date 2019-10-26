package implementations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import lombok.extern.slf4j.Slf4j;
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
            client.createTable(createTableRequest);
        } catch (ResourceInUseException e) {
            // thrown if the table already exists, in which case it is a no-op.
            log.info("Table already created "+clazz.getCanonicalName());
        }

    }

    public ProfileResponse create(Profile profile) {
        System.out.println("DynamoMapper "+dynamoDBMapper);
        dynamoDBMapper.save(profile);
        return new ProfileResponse(ResponseCode.OK);
    }
}
