package in.lovelacetech.pln.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tioammar on 13/01/16.
 */
public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "example.db";
    private static final int DB_VERSION = 1;

    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_COMMAND = "CREATE TABLE " + Contract.ExampleColumn.TABLE_NAME + " ( " +
                Contract.ExampleColumn._ID + " INTEGER PRIMARY KEY, " +
                Contract.ExampleColumn.COLUMN_TEXT + " TEXT NOT NULL );";
        db.execSQL(SQL_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.PATH_EXAMPLE);
        onCreate(db);
    }
}
