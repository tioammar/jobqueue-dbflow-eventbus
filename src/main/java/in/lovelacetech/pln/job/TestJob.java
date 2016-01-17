package in.lovelacetech.pln.job;

import android.util.Log;

import com.path.android.jobqueue.Params;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import in.lovelacetech.pln.di.component.AppComponent;
import in.lovelacetech.pln.event.ExampleEvent;
import in.lovelacetech.pln.model.ExampleModel;
import in.lovelacetech.pln.vo.Example;

/**
 * Created by Aditya Amirullah on 1/13/2016.
 */
public class TestJob extends BaseJob {

    private static final String TAG = TestJob.class.getSimpleName();

    @Inject
    ExampleModel mExampleModel;

    @Inject
    EventBus mBus;

    @Override
    public void inject(AppComponent component) {
        super.inject(component);
        component.inject(this);
    }

    public TestJob(@Priority int priority) {
        super(new Params(priority).persist());
    }

    @Override
    public void onAdded() {
        Log.d(TAG, "job added...");
    }

    @Override
    public void onRun() throws Throwable {
        Log.d(TAG, "job running...");

        Example model = new Example();

        model.text = "hoeeeee";
        mExampleModel.insert(model);

        // poke the activity
        mBus.post(new ExampleEvent());
    }

    @Override
    protected void onCancel() {
        Log.d(TAG, "job cancelled...");
    }
}
