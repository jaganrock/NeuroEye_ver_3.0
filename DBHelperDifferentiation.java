package ragul.srushty.com.neuroeye;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class DBHelperDifferentiation extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDbDifferentiation.db";
    public static final String PATIENT_COLUMN_ID = "test_id";
    public static final String PATIENT_PATIENT_ID = "patient_id";
    public static final String PATIENT_PATIENT_NAME = "patient_name";
    public static final String PATIENT_TEST_ID = "patient_test_id";
    public static final String PATIENT_DIFFERENTIATION_TABLE = "patient_differentiation";
    public static final String PATIENT_DIFFERENTIATION_TOTAL_TIME = "totaltime";
    public static final String PATIENT_DIFFERENTIATION_AVERAGE_TIME = "avgtime";
    public static final String PATIENT_DIFFERENTIATION_WRONG_OBJECT = "wrongobjects";

    private HashMap hp;


    public DBHelperDifferentiation(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.e("Table_Created "," Differentiation");

        db.execSQL(
                "CREATE TABLE " + PATIENT_DIFFERENTIATION_TABLE
                        + " (" + PATIENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + PATIENT_PATIENT_ID + " TEXT, "
                        + PATIENT_TEST_ID + " TEXT, "
                        + PATIENT_PATIENT_NAME + " TEXT, "
                        + PATIENT_DIFFERENTIATION_TOTAL_TIME + " TEXT, "
                        + PATIENT_DIFFERENTIATION_AVERAGE_TIME + " TEXT, "
                        + PATIENT_DIFFERENTIATION_WRONG_OBJECT + " TEXT) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+PATIENT_DIFFERENTIATION_TABLE);
        onCreate(db);
    }

    public boolean insertDifferentiation  (String patient_id, int test_id,String patient_name,long totaltime,long avgtime,int wrongobjects)
    {

        Log.e("insertDifferentiation "," 1");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_PATIENT_ID, patient_id);
        contentValues.put(PATIENT_TEST_ID, test_id);
        contentValues.put(PATIENT_PATIENT_NAME, patient_name);
        contentValues.put(PATIENT_DIFFERENTIATION_TOTAL_TIME, totaltime);
        contentValues.put(PATIENT_DIFFERENTIATION_AVERAGE_TIME, avgtime);
        contentValues.put(PATIENT_DIFFERENTIATION_WRONG_OBJECT, wrongobjects);
        db.insert(PATIENT_DIFFERENTIATION_TABLE, null, contentValues);

        Log.e("Total Time Diff 11","in MS 2 "+totaltime);
        Log.e("Average Time 11","in MS 2 "+avgtime);
        Log.e("Wrong Oblect Pressed 11","2 "+wrongobjects);
        db.close();
        return true;

    }

    public String totaltime(String id)
    {
        Log.e("Total Time","Executed 1 "+ id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_DIFFERENTIATION_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        Log.e("Total Time","Executed 2");
        if(res.getCount()==0){
            res.close();
            db.close();
            Log.e("Total Time","Executed 3");
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_DIFFERENTIATION_TOTAL_TIME));
            res.close();
            db.close();
            return ss;
        }
    }

    public String avgtime(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_DIFFERENTIATION_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_DIFFERENTIATION_AVERAGE_TIME));
            res.close();
            db.close();
            return ss;
        }
    }

    public String wrongobject(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_DIFFERENTIATION_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_DIFFERENTIATION_WRONG_OBJECT));
            res.close();
            db.close();
            return ss;
        }
    }
}