package in.lovelacetech.pln.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by tioammar on 13/01/16.
 */
public class Contract {

    public static final String CONTENT_AUTHORITY = "in.lovelacetech.pln";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_EXAMPLE = "example";

    public static class ExampleColumn implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_EXAMPLE).build();

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EXAMPLE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EXAMPLE;

        public static final String TABLE_NAME = PATH_EXAMPLE;

        public static final String COLUMN_TEXT = "text";

        public static Uri buildUri(String id){
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }
    }
}
