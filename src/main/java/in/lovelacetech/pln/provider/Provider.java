package in.lovelacetech.pln.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import in.lovelacetech.pln.di.ProviderModule;
import in.lovelacetech.pln.di.component.DaggerProviderComponent;
import in.lovelacetech.pln.di.component.ProviderComponent;
import in.lovelacetech.pln.provider.model.CommonModel;

/**
 * Created by tioammar on 13/01/16.
 */
public class Provider extends ContentProvider {

    @Inject
    SqliteHelper mSqliteHelper;

    @Inject
    UriMatcher mUriMatcher;

    @Inject
    CommonModel mModelQualifier;

    ProviderComponent mComponent;

    public static final int EXAMPLE = 100;
    public static final int EXAMPLE_ID = 101;

    @Override
    public boolean onCreate() {
        mComponent = DaggerProviderComponent.builder()
                .providerModule(new ProviderModule(getContext()))
                .build();
        mComponent.inject(this);
        return true;
    }

    public ProviderComponent getComponent(){
        return mComponent;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mSqliteHelper.getReadableDatabase();
        int match = mUriMatcher.match(uri);
        getContentResolver().notifyChange(uri, null);
        return mModelQualifier.query(db, match, uri, projection, selection, selectionArgs, sortOrder);
    }

    private ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int code = mUriMatcher.match(uri);
        switch (code){
            case EXAMPLE:
                return Contract.ExampleColumn.CONTENT_DIR_TYPE;
            case EXAMPLE_ID:
                return Contract.ExampleColumn.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = mSqliteHelper.getWritableDatabase();
        int match = mUriMatcher.match(uri);
        getContentResolver().notifyChange(uri, null);
        return mModelQualifier.insert(db, match, uri, values);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSqliteHelper.getWritableDatabase();
        int match = mUriMatcher.match(uri);
        getContentResolver().notifyChange(uri, null);
        return mModelQualifier.delete(db, match, uri, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSqliteHelper.getWritableDatabase();
        int match = mUriMatcher.match(uri);
        getContentResolver().notifyChange(uri, null);
        return mModelQualifier.update(db, match, uri, values, selection, selectionArgs);
    }
}
