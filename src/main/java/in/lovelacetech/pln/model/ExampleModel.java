package in.lovelacetech.pln.model;

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
    public FlowCursorList<Example> query() {
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
}
