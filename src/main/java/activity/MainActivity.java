package activity;

import com.google.inject.Guice;
import com.google.inject.Injector;
import component.CreateProfileComponent;
import spark.Spark;

public class MainActivity {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ActivityModule());
        CreateProfileComponent instance = injector.getInstance(CreateProfileComponent.class);
        Spark.get("/createProfile", ((request, response) -> "Create profile requested"));
    }
}
