package mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@DynamoDBTable(tableName = "Profile")
public class Profile {
    private String id = UUID.randomUUID().toString();
    private final String userId;
    private final String email;
    private final String firstName;
    private final String lastName;

    @DynamoDBHashKey
    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

}
