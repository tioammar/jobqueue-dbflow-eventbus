package in.lovelacetech.pln.job;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.path.android.jobqueue.Params;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import in.lovelacetech.pln.di.component.AppComponent;
import in.lovelacetech.pln.event.PostEvent;
import in.lovelacetech.pln.provider.Contract;

/**
 * Created by Aditya Amirullah on 1/13/2016.
 */
public class TestJob extends BaseJob {

    private static final String TAG = TestJob.class.getSimpleName();

    @Inject
    EventBus mEventBus;

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
        ContentValues values = new ContentValues();
        values.put(Contract.ExampleColumn.COLUMN_TEXT, "Example Text");
        Uri uri = getApplicationContext().getContentResolver().insert(
                Contract.ExampleColumn.CONTENT_URI, values);
        if (uri != null) mEventBus.post(new PostEvent());
    }

    @Override
    protected void onCancel() {
        Log.d(TAG, "job cancelled...");
    }
}