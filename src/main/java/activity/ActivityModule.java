package activity;

import com.google.inject.AbstractModule;
import implementations.UserProfile;
import interfaces.IProfile;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class ActivityModule extends AbstractModule {

    private static final Region REGION = Region.US_WEST_2;
    private static final DynamoDbClient dynamoDBClient = DynamoDbClient.builder().region(REGION).credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build();

    @Override
    protected void configure() {
        bind(IProfile.class).to(UserProfile.class);
    }
}
