package UserStatsDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "user.db";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE = "user";

    public static final String TRAINING = "times";
    public static final String  HEIGHT = "height";
    public static final String  WEIGHT = "weight";


    public static final String CREATE_USER_TABLE = "CREATE TABLE "
            + USER_TABLE
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HEIGHT + " INTEGER, " + WEIGHT + " INTEGER, " + TRAINING + "INTEGER)";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
