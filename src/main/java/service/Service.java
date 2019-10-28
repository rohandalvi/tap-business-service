package service;

import net.jmob.guice.conf.core.BindConfig;
import net.jmob.guice.conf.core.InjectConfig;
import net.jmob.guice.conf.core.Syntax;

@BindConfig(value = "app", syntax = Syntax.JSON)
public class Service {

    @InjectConfig("complexEntries")
    private ServiceConfiguration serviceConfiguration;

    public ServiceConfiguration getServiceConfiguration() {
        return serviceConfiguration;
    }


}