package in.lovelacetech.pln.di.component;

import android.content.UriMatcher;

import javax.inject.Singleton;

import dagger.Component;
import in.lovelacetech.pln.di.ProviderModule;
import in.lovelacetech.pln.provider.Provider;
import in.lovelacetech.pln.provider.SqliteHelper;
import in.lovelacetech.pln.provider.model.CommonModel;

/**
 * Created by tioammar on 13/01/16.
 */

@Component(modules = {ProviderModule.class})
public interface ProviderComponent {

    CommonModel modelQualifer();
    SqliteHelper sqliteHelper();
    UriMatcher uriMatcher();

    void inject(Provider provider);
}
