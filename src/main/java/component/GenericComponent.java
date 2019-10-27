package component;

import common.RequestType;
import response.ResponseCode;

public abstract class GenericComponent<T> {
    public abstract String process(T t, RequestType requestType);
}
