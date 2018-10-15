package ragul.srushty.com.neuroeye;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelperAntiscades extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDbAntiscades.db";
    public static final String PATIENT_COLUMN_ID = "test_id";
    public static final String PATIENT_PATIENT_ID = "patient_id";
    public static final String PATIENT_PATIENT_NAME = "patient_name";
    public static final String PATIENT_TEST_ID = "patient_test_id";
    public static final String PATIENT_ANTISCADES_TABLE = "patient_antiscades";
    public static final String PATIENT_ANTISCADES_TOTAL_TIME = "totaltime";
    public static final String PATIENT_ANTISCADES_AVERAGE_TIME_SATGE1 = "avgtime_stage1";
    public static final String PATIENT_ANTISCADES_AVERAGE_TIME_SATGE2 = "avgtime_stage2";
    public static final String PATIENT_ANTISCADES_AVERAGE_TIME_SATGE3 = "avgtime_stage3";
    public static final String PATIENT_ANTISCADES_CORRECT_SATGE1 = "correct_stage1";
    public static final String PATIENT_ANTISCADES_WRONG_SATGE1 = "wrong_stage1";
    public static final String PATIENT_ANTISCADES_CORRECT_SATGE2 = "correct_stage2";
    public static final String PATIENT_ANTISCADES_WRONG_SATGE2 = "wrong_stage2";
    public static final String PATIENT_ANTISCADES_CORRECT_SATGE3 = "correct_stage3";
    public static final String PATIENT_ANTISCADES_WRONG_SATGE3 = "wrong_stage3";





    public DBHelperAntiscades(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.e("Table_Created ","Versalab");

        db.execSQL(
                "CREATE TABLE " + PATIENT_ANTISCADES_TABLE
                        + " (" + PATIENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + PATIENT_PATIENT_ID + " TEXT, "
                        + PATIENT_PATIENT_NAME + " TEXT, "
                        + PATIENT_TEST_ID + " TEXT, "
                        + PATIENT_ANTISCADES_TOTAL_TIME + " TEXT, "
                        + PATIENT_ANTISCADES_AVERAGE_TIME_SATGE1 + " TEXT, "
                        + PATIENT_ANTISCADES_CORRECT_SATGE1 + " TEXT, "
                        + PATIENT_ANTISCADES_WRONG_SATGE1 + " TEXT, "
                        + PATIENT_ANTISCADES_AVERAGE_TIME_SATGE2 + " TEXT, "
                        + PATIENT_ANTISCADES_CORRECT_SATGE2 + " TEXT, "
                        + PATIENT_ANTISCADES_WRONG_SATGE2 + " TEXT, "
                        + PATIENT_ANTISCADES_AVERAGE_TIME_SATGE3 + " TEXT, "
                        + PATIENT_ANTISCADES_CORRECT_SATGE3 + " TEXT, "
                        + PATIENT_ANTISCADES_WRONG_SATGE3 + " TEXT) "

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+PATIENT_ANTISCADES_TABLE);
        onCreate(db);
    }

    public boolean insertantiscades  (String patient_id,String patient_name, int test_id,long totaltime,long avgtime_stage1,int correct_stage1,int wrong_stage1,long avgtime_stage2,int correct_stage2,long wrong_stage2,long avgtime_stage3,int correct_stage3,long wrong_stage3)
    {

        Log.e("insertAntiscades "," 1");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_PATIENT_ID, patient_id);
        contentValues.put(PATIENT_PATIENT_NAME, patient_name);
        contentValues.put(PATIENT_TEST_ID, test_id);
        contentValues.put(PATIENT_ANTISCADES_TOTAL_TIME, totaltime);
        contentValues.put(PATIENT_ANTISCADES_AVERAGE_TIME_SATGE1, avgtime_stage1);
        contentValues.put(PATIENT_ANTISCADES_AVERAGE_TIME_SATGE2, avgtime_stage2);
        contentValues.put(PATIENT_ANTISCADES_AVERAGE_TIME_SATGE3, avgtime_stage3);
        contentValues.put(PATIENT_ANTISCADES_CORRECT_SATGE1, correct_stage1);
        contentValues.put(PATIENT_ANTISCADES_WRONG_SATGE1, wrong_stage1);
        contentValues.put(PATIENT_ANTISCADES_CORRECT_SATGE2, correct_stage2);
        contentValues.put(PATIENT_ANTISCADES_WRONG_SATGE2, wrong_stage2);
        contentValues.put(PATIENT_ANTISCADES_CORRECT_SATGE3, correct_stage3);
        contentValues.put(PATIENT_ANTISCADES_WRONG_SATGE3, wrong_stage3);
        db.insert(PATIENT_ANTISCADES_TABLE, null, contentValues);
        db.close();
        return true;
    }

    public String gettotaltime(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_TOTAL_TIME));
            res.close();
            db.close();
            return ss;
        }
    }

    public String getavgtime_stage1(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_AVERAGE_TIME_SATGE1));
            res.close();
            db.close();
            return ss;
        }
    }

    public String getavgtime_stage2(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_AVERAGE_TIME_SATGE2));
            res.close();
            db.close();
            return ss;
        }
    }

    public String getavgtime_stage3(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_AVERAGE_TIME_SATGE3));
            res.close();
            db.close();
            return ss;
        }
    }

    public String getcorrect_stage1(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_CORRECT_SATGE1));
            res.close();
            db.close();
            return ss;
        }
    }

    public String getwrong_stage1(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_WRONG_SATGE1));
            res.close();
            db.close();
            return ss;
        }
    }

    public String getcorrect_stage2(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_CORRECT_SATGE2));
            res.close();
            db.close();
            return ss;
        }
    }

    public String getwrong_stage2(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_WRONG_SATGE2));
            res.close();
            db.close();
            return ss;
        }
    }


    public String getcorrect_stage3(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_CORRECT_SATGE3));
            res.close();
            db.close();
            return ss;
        }
    }

    public String getwrong_stage3(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_ANTISCADES_TABLE+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ANTISCADES_WRONG_SATGE3));
            res.close();
            db.close();
            return ss;
        }
    }
}