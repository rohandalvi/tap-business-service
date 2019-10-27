package component;

import common.RequestType;
import implementations.UserProfile;
import interfaces.IProfile;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

public class RouterComponent {

    GenericComponent genericComponent;
    public RouterComponent(GenericComponent genericComponent) {
        this.genericComponent = genericComponent;
        Spark.port(Integer.parseInt(System.getenv("PORT")));
    }
    public void route() {
        path("/profile", () -> {
            post("/create", ((request, response) -> process(request, response, RequestType.CREATE_PROFILE)));
        });
    }

    private String process(Request request, Response response, RequestType requestType) {
        switch (requestType) {
            case CREATE_PROFILE:
                return genericComponent.process(request, requestType).name();
            default: throw new RuntimeException("Request type "+requestType+" not implemented");
        }
    }


}
