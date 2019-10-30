package component;

import common.RequestType;
import interfaces.IProfile;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.ResponseCode;
import spark.Request;

@RequiredArgsConstructor
public class ProcessComponent<T> extends GenericComponent<T> {

    private static final Logger log = LoggerFactory.getLogger(ProcessComponent.class);
    private final IProfile profile;

    @Override
    public String process(T t, RequestType requestType) {
        Request request = (Request) t;
        switch (requestType) {
            case CREATE_PROFILE:

                log.info("Triggering create profile from process component");
                return profile.createProfile(request).getResponseCode().toString();
            case GET_PROFILE:
                log.info("Triggering get profile from process component");
                return profile.getProfile(request).toString();
            default: throw new RuntimeException("Request type "+requestType+" not implemented");
        }

    }

}
