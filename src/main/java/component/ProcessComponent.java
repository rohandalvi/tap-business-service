package component;

import common.RequestType;
import interfaces.IProfile;
import lombok.RequiredArgsConstructor;
import response.ResponseCode;
import spark.Request;

@RequiredArgsConstructor
public class ProcessComponent<T> extends GenericComponent<T> {

    private final IProfile profile;

    @Override
    public ResponseCode process(T t, RequestType requestType) {
        switch (requestType) {
            case CREATE_PROFILE:
                Request request = (Request) t;
                profile.createProfile(request);
            default: throw new RuntimeException("Request type "+requestType+" not implemented");
        }

    }

}
