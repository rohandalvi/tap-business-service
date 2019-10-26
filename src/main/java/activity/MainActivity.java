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
        QueryParamsMap queryParamsMap = request.queryMap();
        String firstName = queryParamsMap.get(Keys.FIRST_NAME).toString();
        String lastName = queryParamsMap.get(Keys.LAST_NAME).toString();
        String email = queryParamsMap.get(Keys.EMAIL).toString();
        String userId = queryParamsMap.get(Keys.USER_ID).toString();


        Profile profile = new Profile(userId, email, firstName, lastName);
        System.out.println("Calling request with "+profile);
        return createProfileComponent.createProfile(profile).getResponseCode().name();

    }

}
