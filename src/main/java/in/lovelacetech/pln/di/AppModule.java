package in.lovelacetech.pln.di;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import in.lovelacetech.pln.TestApplication;
import in.lovelacetech.pln.job.BaseJob;

/**
 * Created by Aditya Amirullah on 1/13/2016.
 */
@Module
public class AppModule {

    private final TestApplication mApp;

    public AppModule(TestApplication app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public JobManager jobManager(){
        Configuration config = new Configuration.Builder(mApp)
                .consumerKeepAlive(45)
                .maxConsumerCount(3)
                .minConsumerCount(1)
                .injector(new DependencyInjector() {
                    @Override
                    public void inject(Job job) {
                        if (job instanceof BaseJob) {
                            ((BaseJob) job).inject(mApp.getAppComponent());
                        }
                    }
                })
                .build();
        return new JobManager(mApp, config);
    }

    @Provides
    @Singleton
    public EventBus eventBus() {
        return new EventBus();
    }
}
