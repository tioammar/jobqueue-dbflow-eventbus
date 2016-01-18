package in.lovelacetech.pln.model;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by tioammar on 14/01/16.
 */
public interface CoreModel<T extends BaseModel> {

    void insert(T t);
    void bulkInsert(List<T> t);
    void delete(T t);
    void update(T t);
    FlowCursorList<T> getAll();
    T get(int id);
}
