package in.lovelacetech.pln.job;

import android.support.annotation.IntDef;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import in.lovelacetech.pln.di.component.AppComponent;
import in.lovelacetech.pln.job.exception.NetworkException;

/**
 * Created by Aditya Amirullah on 1/13/2016.
 */
abstract public class BaseJob extends Job {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({UI_HIGH, BACKGROUND})
    public @interface Priority {}

    public static final int UI_HIGH = 2;
    public static final int BACKGROUND = 1;

    public BaseJob(Params params) {
        super(params);
    }

    public void inject(AppComponent component){

    }

    // network check
    protected boolean shouldRetry(Throwable throwable){
        if (throwable instanceof NetworkException){
            NetworkException exception = (NetworkException) throwable;
            return exception.shoudRetry();
        }
        return false;
    }
}
