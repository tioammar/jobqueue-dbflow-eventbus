package in.lovelacetech.pln.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.path.android.jobqueue.JobManager;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import in.lovelacetech.pln.R;
import in.lovelacetech.pln.event.ExampleEvent;
import in.lovelacetech.pln.job.BaseJob;
import in.lovelacetech.pln.job.TestJob;
import in.lovelacetech.pln.model.ExampleModel;
import in.lovelacetech.pln.ui.ExampleAdapter;
import in.lovelacetech.pln.vo.Example;

public class MainActivity extends BaseActivity {

    ExampleAdapter mExampleAdapter;
    FlowCursorList<Example> mCursorList;

    @Inject
    JobManager mJobManager;

    @Inject
    EventBus mBus;

    @Override
    protected String setTitle() {
        return "PLN Abepura";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);

        mBus.register(this);

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
        mCursorList = new ExampleModel().query();
        rv.setAdapter(mExampleAdapter);
        mExampleAdapter.swapCursor(mCursorList);
    }

    @Override
    boolean setUseToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        return toolbar != null;
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(ExampleEvent event){
        mCursorList.refresh();
        mExampleAdapter.swapCursor(mCursorList);
    }
}
