package activity;

import com.google.inject.Guice;
import com.google.inject.Injector;
import common.Keys;
import component.CreateProfileComponent;
import mapper.Profile;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Spark;


public class MainActivity {


    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ActivityModule());
        CreateProfileComponent instance = injector.getInstance(CreateProfileComponent.class);
        Spark.port(Integer.parseInt(System.getenv("PORT")));
        Spark.get("/createProfile", ((request, response) -> processRequest(request, response, instance)));
    }

    private static String processRequest(Request request, Response response, CreateProfileComponent createProfileComponent) {

        String firstName = request.queryParams(Keys.FIRST_NAME);
        String lastName = request.queryParams(Keys.LAST_NAME);
        String email = request.queryParams(Keys.EMAIL);
        String userId = request.queryParams(Keys.USER_ID);


        Profile profile = new Profile();
        profile.setEmail(email);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setUserId(userId);
        System.out.println("Calling request with "+profile);
        return createProfileComponent.createProfile(profile).getResponseCode().name();

    }

}
