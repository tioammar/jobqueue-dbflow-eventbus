package in.lovelacetech.pln.model;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import in.lovelacetech.pln.vo.Example;
import in.lovelacetech.pln.vo.Example_Table;

/**
 * Created by tioammar on 14/01/16.
 */
public class ExampleModel implements CoreModel<Example> {

    public void insert(Example example){
        if (example.text != null){
            example.save();
        } else throw new IllegalArgumentException();
    }

    @Override
    public FlowCursorList<Example> getAll() {
        FlowCursorList<Example> examples = new FlowCursorList<>(false, SQLite.select()
                    .from(Example.class).orderBy(Example_Table.id, false));
        if (examples.getCount() >= 0) {
            return examples;
        } else return null;
    }

    public void bulkInsert(List<Example> examples){
        for (Example example : examples){
            example.insert();
        }
    }

    @Override
    public Example get(int id) {
        Example example = SQLite.select().from(Example.class)
                .where(Example_Table.id.is(id)).querySingle();
        if (example.id == id){
            return example;
        } else throw new RuntimeException();
    }

    @Override
    public void delete(Example example) {
        if (example.id != 0) {
            example.delete();
        } else throw new IllegalArgumentException();
    }

    @Override
    public void update(Example example) {
        if (example.id != 0 && example.text != null){
            example.update();
        } else throw new IllegalArgumentException();
    }

    public static class ExampleLoader extends AsyncTaskLoader<Example> {

        private Example mExample;
        private int mId;

        public ExampleLoader(Context context, int id) {
            super(context);
            mId = id;
        }

        @Override
        protected void onStartLoading() {
            if (mExample == null){
                forceLoad();
            }
            super.onStartLoading();
        }

        @Override
        public Example loadInBackground() {
            mExample = new ExampleModel().get(mId);
            return mExample;
        }
    }

    public static class ExampleListLoader
            extends AsyncTaskLoader<FlowCursorList<Example>> {

        private FlowCursorList<Example> mCursor;

        public ExampleListLoader(Context context) {
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
            mCursor = new ExampleModel().getAll();
            return mCursor;
        }
    }
}
