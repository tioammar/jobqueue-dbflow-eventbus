package in.lovelacetech.pln.model;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by tioammar on 14/01/16.
 */

@Database(name = AppData.DB_NAME, version = AppData.DB_VERSION)
public class AppData {

    public static final String DB_NAME = "pln";
    public static final int DB_VERSION = 1;
}
