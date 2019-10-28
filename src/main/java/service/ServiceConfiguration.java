package service;

import java.util.List;
import java.util.Map;

public interface ServiceConfiguration {

    String getHostname();

    Map<String, String> getDependentServiceMap();

    List<String> getServices();
}
