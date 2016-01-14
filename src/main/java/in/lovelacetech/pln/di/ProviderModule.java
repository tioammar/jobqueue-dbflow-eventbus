package in.lovelacetech.pln.di;

import android.content.Context;
import android.content.UriMatcher;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.lovelacetech.pln.provider.Contract;
import in.lovelacetech.pln.provider.Provider;
import in.lovelacetech.pln.provider.SqliteHelper;
import in.lovelacetech.pln.provider.model.CommonModel;

/**
 * Created by tioammar on 13/01/16.
 */

@Module
public class ProviderModule {

    private Context mContext;

    public ProviderModule(Context context) {
        mContext = context;
    }

    @Provides
    public CommonModel modelQualifier(){
        return new CommonModel();
    }

    @Provides
    public SqliteHelper sqliteHelper(){
        return new SqliteHelper(mContext);
    }

    @Provides
    public UriMatcher uriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Contract.CONTENT_AUTHORITY,
                Contract.PATH_EXAMPLE, Provider.EXAMPLE);
        uriMatcher.addURI(Contract.CONTENT_AUTHORITY,
                Contract.PATH_EXAMPLE + "/#", Provider.EXAMPLE_ID);
        return uriMatcher;
    }
}
