package in.lovelacetech.pln.vo;

import java.io.Serializable;

import in.lovelacetech.pln.provider.Contract;

/**
 * Created by tioammar on 13/01/16.
 */
public class Example implements Serializable {

    public int id;
    public String text;

    public final static String[] PROJECTION = {
            Contract.ExampleColumn._ID,
            Contract.ExampleColumn.COLUMN_TEXT
    };

    public static final int _ID = 0;
    public static final int TEXT = 1;
}
