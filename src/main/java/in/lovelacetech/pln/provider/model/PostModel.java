package in.lovelacetech.pln.provider.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import in.lovelacetech.pln.provider.Contract;
import in.lovelacetech.pln.provider.Provider;

/**
 * Created by tioammar on 13/01/16.
 */
public class PostModel implements BaseModel {

    @Override
    public Uri insert(SQLiteDatabase db,
                      @NonNull Uri uri,
                      ContentValues values) {
        long id = db.insertWithOnConflict(Contract.ExampleColumn.TABLE_NAME, null, values,
                        SQLiteDatabase.CONFLICT_REPLACE);
        return Contract.ExampleColumn.buildUri(Long.toString(id));
    }

    @Override
    public int update(SQLiteDatabase db,
                      @NonNull Uri uri,
                      ContentValues values,
                      String selection, String[] selectionArgs) {
        return db.update(Contract.ExampleColumn.TABLE_NAME, values,
                selection, selectionArgs);
    }

    @Override
    public int delete(SQLiteDatabase db,
                      @NonNull Uri uri,
                      String selection, String[] selectionArgs) {
        if (selection == null) selection = "1";
        return db.delete(Contract.ExampleColumn.TABLE_NAME,
                selection, selectionArgs);
    }

    @Override
    public Cursor query(SQLiteDatabase db,
                        int match,
                        @NonNull Uri uri,
                        String[] projection,
                        String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor cursor;
        switch (match){
            case Provider.EXAMPLE:
                cursor = db.query(Contract.ExampleColumn.TABLE_NAME, projection,
                        selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case Provider.EXAMPLE_ID:
                String id = uri.getLastPathSegment();
                cursor = db.query(Contract.ExampleColumn.TABLE_NAME, projection,
                        Contract.ExampleColumn._ID + " = ? ",
                        new String[]{id}, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return cursor;
    }
}
