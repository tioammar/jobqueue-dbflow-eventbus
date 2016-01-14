package in.lovelacetech.pln.di.component;

import dagger.Component;
import in.lovelacetech.pln.di.scope.ActivityScope;
import in.lovelacetech.pln.ui.activity.MainActivity;

/**
 * Created by Aditya Amirullah on 1/13/2016.
 */
@ActivityScope
@Component(dependencies = {AppComponent.class})
public interface ActivityComponent extends AppComponent{

    void inject(MainActivity activity);
}
