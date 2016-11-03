package park.hyunwoo.releasedj;


import android.app.Application;

import park.hyunwoo.releasedj.di.AppComponent;
import park.hyunwoo.releasedj.di.AppModule;
import park.hyunwoo.releasedj.di.DaggerAppComponent;

public class ReleaseDJApplication extends Application {

    private static ReleaseDJApplication app;
    protected AppComponent component;

    public static ReleaseDJApplication getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        component = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    public AppComponent getComponent() {
        return component;
    }
}