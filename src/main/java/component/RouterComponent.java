package component;

import common.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

public class RouterComponent {

    private static final Logger log = LoggerFactory.getLogger(RouterComponent.class);
    GenericComponent genericComponent;

    public RouterComponent(GenericComponent genericComponent) {
        this.genericComponent = genericComponent;
        Spark.port(Integer.parseInt(System.getenv("PORT")));
    }
    public void route() {
        log.info("Initialized router");

        path("/profile", () -> {
            post("/create", ((request, response) -> process(request, response, RequestType.CREATE_PROFILE)));
            get("/get", ((request, response) -> process(request, response, RequestType.GET_PROFILE)));
        });
    }

    private String process(Request request, Response response, RequestType requestType) {
        System.out.println(String.format("Received request to process"));
        switch (requestType) {
            case CREATE_PROFILE:
                log.info("Routing to profile creation");
                System.out.println(String.format("Routing to profile creation"));
                return genericComponent.process(request, requestType);
            case GET_PROFILE:
                log.info("Routing to get profile");
                return genericComponent.process(request, requestType);
            default: throw new RuntimeException("Request type "+requestType+" not implemented");
        }
    }


}
