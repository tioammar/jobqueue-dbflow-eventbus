package in.lovelacetech.pln.provider.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by tioammar on 13/01/16.
 */
public interface BaseModel {

    Uri insert(SQLiteDatabase db, @NonNull Uri uri, ContentValues values);

    int update(SQLiteDatabase db, @NonNull Uri uri, ContentValues values,
               String selection, String[] selectionArgs);

    int delete(SQLiteDatabase db, @NonNull Uri uri,
               String selection, String[] selectionArgs);

    Cursor query(SQLiteDatabase db, int match, @NonNull Uri uri,
                 String[] projection,
                 String selection, String[] selectionArgs,
                 String sortOrder);
}
