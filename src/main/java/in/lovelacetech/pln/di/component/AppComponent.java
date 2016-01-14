package in.lovelacetech.pln.di.component;

import com.path.android.jobqueue.JobManager;

import javax.inject.Singleton;

import dagger.Component;
import de.greenrobot.event.EventBus;
import in.lovelacetech.pln.di.AppModule;
import in.lovelacetech.pln.job.TestJob;
import in.lovelacetech.pln.model.ExampleModel;

/**
 * Created by Aditya Amirullah on 1/13/2016.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    JobManager jobManager();
    EventBus eventBus();
    ExampleModel exampleModel();

    void inject(TestJob job);
}
