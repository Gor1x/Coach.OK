package UserStatsDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public final class DBHelper {

    private static DBHelper instance;
    private SQLHelper dbHelper;
    private SQLiteDatabase db;

    private DBHelper(Context context){
        dbHelper = new SQLHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static DBHelper getInstance(Context context){

        synchronized (DBHelper.class) {
            if (instance == null)
                instance = new DBHelper(context);
        }
        return instance;
    }

    public void addUsers(Users user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLHelper.TRAINING, user.getTimes());
        contentValues.put(SQLHelper.HEIGHT, user.getHeight());
        contentValues.put(SQLHelper.WEIGHT, user.getWeight());

        db.insert(SQLHelper.USER_TABLE, null, contentValues);
    }

    public List<Users> getUsers(){

        List<Users> result = new ArrayList<>();

        Cursor cursor = db.query(SQLHelper.USER_TABLE, new String[]{"*"},
                null, null, null, null, null);

        int times = cursor.getColumnIndex(SQLHelper.TRAINING);
        int height = cursor.getColumnIndex(SQLHelper.HEIGHT);
        int weight = cursor.getColumnIndex(SQLHelper.WEIGHT);

        while(cursor.moveToNext()) {
            result.add(new Users( cursor.getInt(height),
                    cursor.getInt(weight),
                    cursor.getInt(times)));
        }

        return result;

    }

}
