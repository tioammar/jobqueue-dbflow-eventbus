package in.lovelacetech.pln.vo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import in.lovelacetech.pln.model.AppData;

/**
 * Created by tioammar on 13/01/16.
 */

@Table(database = AppData.class, name = Example.TABLE_NAME)
public class Example extends BaseModel {

    public static final String TABLE_NAME = "example";

    @Column
    @PrimaryKey(autoincrement = true)
    public int id;

    @Column
    public String text;
}
