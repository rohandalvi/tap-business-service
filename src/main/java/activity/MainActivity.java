package activity;

import com.google.inject.Guice;
import com.google.inject.Injector;
import component.RouterComponent;


public class MainActivity {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ActivityModule());
        RouterComponent instance = injector.getInstance(RouterComponent.class);
        instance.route();
    }
}
