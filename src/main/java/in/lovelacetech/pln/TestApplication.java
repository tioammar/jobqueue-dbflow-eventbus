package in.lovelacetech.pln;

import android.app.Application;

import in.lovelacetech.pln.di.component.AppComponent;
import in.lovelacetech.pln.di.AppModule;
import in.lovelacetech.pln.di.component.DaggerAppComponent;

/**
 * Created by Aditya Amirullah on 1/13/2016.
 */
public class TestApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
