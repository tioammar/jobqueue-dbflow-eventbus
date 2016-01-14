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
        return new FlowCursorList<>(true, SQLite.select()
                .from(Example.class).orderBy(Example_Table.id, false));
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
