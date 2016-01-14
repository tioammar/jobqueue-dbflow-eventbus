package in.lovelacetech.pln.provider.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import in.lovelacetech.pln.provider.Provider;

/**
 * Created by tioammar on 13/01/16.
 */
public class CommonModel {

    PostModel mPostModel;

    public CommonModel() {
        mPostModel = new PostModel();
    }

    public Uri insert(SQLiteDatabase db, int match, @NonNull Uri uri, ContentValues values) {
        Uri retUri;
        switch (match){
            case Provider.EXAMPLE:
                retUri = mPostModel.insert(db, uri, values);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return retUri;
    }

    public int update(SQLiteDatabase db,
                      int match,
                      @NonNull Uri uri,
                      ContentValues values,
                      String selection, String[] selectionArgs) {
        switch (match){
            case Provider.EXAMPLE:
                return mPostModel.update(db, uri, values, selection, selectionArgs);
            default:
                throw new UnsupportedOperationException();
        }
    }

    public int delete(SQLiteDatabase db,
                      int match,
                      @NonNull Uri uri,
                      String selection,
                      String[] selectionArgs) {
        switch (match){
            case Provider.EXAMPLE:
                return mPostModel.delete(db, uri, selection, selectionArgs);
            default:
                throw new UnsupportedOperationException();
        }
    }

    public Cursor query(SQLiteDatabase db,
                        int match,
                        @NonNull Uri uri,
                        String[] projection,
                        String selection, String[] selectionArgs,
                        String sortOrder) {
        switch (match){
            case Provider.EXAMPLE:
                return mPostModel.query(db, match, uri, projection,
                        selection, selectionArgs, sortOrder);
            case Provider.EXAMPLE_ID:
                return mPostModel.query(db, match, uri, projection,
                        selection, selectionArgs, sortOrder);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
