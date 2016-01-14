package in.lovelacetech.pln.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import in.lovelacetech.pln.R;
import in.lovelacetech.pln.TestApplication;
import in.lovelacetech.pln.di.component.ActivityComponent;
import in.lovelacetech.pln.di.component.DaggerActivityComponent;

/**
 * Created by Aditya Amirullah on 1/13/2016.
 */
abstract public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mComponent;


    abstract boolean setUseToolbar();
    abstract protected String setTitle();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setUpToolbar();

        if (setUseToolbar()) {
            ActionBar ab = getSupportActionBar();
            if (ab != null){
                ab.setTitle(setTitle());
            }
        }
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerActivityComponent.builder()
                .appComponent(getApp().getAppComponent())
                .build();
    }

    protected TestApplication getApp(){
        return (TestApplication) getApplicationContext();
    }

    protected ActivityComponent getComponent(){
        return mComponent;
    }
}
