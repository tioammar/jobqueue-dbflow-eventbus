package in.lovelacetech.pln.ui.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.path.android.jobqueue.JobManager;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import in.lovelacetech.pln.R;
import in.lovelacetech.pln.event.PostEvent;
import in.lovelacetech.pln.job.BaseJob;
import in.lovelacetech.pln.job.TestJob;
import in.lovelacetech.pln.provider.Contract;
import in.lovelacetech.pln.ui.ExampleAdapter;
import in.lovelacetech.pln.vo.Example;

public class MainActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    ExampleAdapter mExampleAdapter;

    @Inject
    JobManager mJobManager;

    @Inject
    EventBus mEventBus;

    @Override
    protected String setTitle() {
        return "PLN Abepura";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);

        mEventBus.register(this);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mJobManager.addJobInBackground(new TestJob(BaseJob.UI_HIGH));
            }
        });

        RecyclerView rv = (RecyclerView) findViewById(R.id.example_list);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        mExampleAdapter = new ExampleAdapter(this, new ExampleAdapter.Listener() {
            @Override
            public void onClickListener(String title) {
                Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
            }
        });
        rv.setAdapter(mExampleAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    boolean setUseToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        return toolbar != null;
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(PostEvent event){
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Contract.ExampleColumn.CONTENT_URI, Example.PROJECTION,
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mExampleAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mExampleAdapter.swapCursor(null);
    }
}
