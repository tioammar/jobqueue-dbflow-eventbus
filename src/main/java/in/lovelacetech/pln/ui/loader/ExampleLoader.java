package in.lovelacetech.pln.ui.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.raizlabs.android.dbflow.list.FlowCursorList;

import in.lovelacetech.pln.model.ExampleModel;
import in.lovelacetech.pln.vo.Example;

/**
 * Created by tioammar on 18/01/16.
 */
public class ExampleLoader extends AsyncTaskLoader<FlowCursorList<Example>> {

    private FlowCursorList<Example> mCursor;

    public ExampleLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mCursor == null){
            forceLoad();
        }
        super.onStartLoading();
    }

    @Override
    public FlowCursorList<Example> loadInBackground() {
        mCursor =  new ExampleModel().query();
        return mCursor;
    }
}
