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

public class DBHelperPatientLastTest extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBNameNeuroTest.db";
    public static final String PATIENT_TABLE_NAME = "neurolasttest";
    public static final String PATIENT_COLUMN_ID = "test_id";
    public static final String PATIENT_PATIENT_ID = "patient_id";
    public static final String PATIENT_TEST_ID = "patient_test_id";
    public static final String PATIENT_NAME = "patient_name";
    public static final String PATIENT_NEURO_TEST = "neuro_test";
    public static final String PATIENT_TOTAL_TIME = "patient_total_time";
    public static final String PATIENT_DOT_SIZE = "patient_dot_size";
    public static final String PATIENT_QUADRANT_ONE = "patient_quardent_one";
    public static final String PATIENT_QUADRANT_TWO = "patient_quardent_two";
    public static final String PATIENT_QUADRANT_THREE = "patient_quardent_three";
    public static final String PATIENT_QUADRANT_FOUR = "patient_quardent_four";
    public static final String PATIENT_ACCURACY = "patient_accuracy";


    public static final String PATIENT_RADIAL1_REACTION_TIME = "patient_radial1_reaction_time";
    public static final String PATIENT_RADIAL2_REACTION_TIME = "patient_radial2_reaction_time";
    public static final String PATIENT_RADIAL3_REACTION_TIME = "patient_radial3_reaction_time";
    public static final String PATIENT_RADIAL4_REACTION_TIME = "patient_radial4_reaction_time";
    public static final String PATIENT_RADIAL5_REACTION_TIME = "patient_radial5_reaction_time";
    public static final String PATIENT_RADIAL6_REACTION_TIME = "patient_radial6_reaction_time";
    public static final String PATIENT_RADIAL7_REACTION_TIME = "patient_radial7_reaction_time";
    public static final String PATIENT_RADIAL8_REACTION_TIME = "patient_radial8_reaction_time";


    private HashMap hp;

    public DBHelperPatientLastTest(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.e("Table_Created ","Testing");
        db.execSQL(
                "CREATE TABLE " + PATIENT_TABLE_NAME
                        + " (" + PATIENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + PATIENT_PATIENT_ID + " TEXT, "
                        + PATIENT_NEURO_TEST + " TEXT, "
                        + PATIENT_TEST_ID + " TEXT, "
                        + PATIENT_NAME + " TEXT, "
                        + PATIENT_TOTAL_TIME + " TEXT, "
                        + PATIENT_DOT_SIZE + " TEXT, "
                        + PATIENT_QUADRANT_ONE + " TEXT, "
                        + PATIENT_QUADRANT_TWO + " TEXT, "
                        + PATIENT_QUADRANT_THREE + " TEXT, "
                        + PATIENT_QUADRANT_FOUR + " TEXT, "
                        + PATIENT_ACCURACY + " TEXT, "

                        + PATIENT_RADIAL1_REACTION_TIME + " TEXT, "
                        + PATIENT_RADIAL2_REACTION_TIME + " TEXT, "
                        + PATIENT_RADIAL3_REACTION_TIME + " TEXT, "
                        + PATIENT_RADIAL4_REACTION_TIME + " TEXT, "
                        + PATIENT_RADIAL5_REACTION_TIME + " TEXT, "
                        + PATIENT_RADIAL6_REACTION_TIME + " TEXT, "
                        + PATIENT_RADIAL7_REACTION_TIME + " TEXT, "
                        + PATIENT_RADIAL8_REACTION_TIME + " TEXT) "
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+PATIENT_TABLE_NAME);

        onCreate(db);
    }
    public boolean insertTest  (String patient_id, int test_id,String patient_name, long time_ms, long total_ms, String dot_size, long q1, long q2, long q3, long q4,String accuracy,long patient_radial1_reaction_time,long patient_radial2_reaction_time,long patient_radial3_reaction_time,long patient_radial4_reaction_time,long patient_radial5_reaction_time,long patient_radial6_reaction_time,long patient_radial7_reaction_time,long patient_radial8_reaction_time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_PATIENT_ID, patient_id);
        contentValues.put(PATIENT_TEST_ID, test_id);
        contentValues.put(PATIENT_NAME, patient_name);
        contentValues.put(PATIENT_NEURO_TEST, time_ms);
        contentValues.put(PATIENT_TOTAL_TIME, total_ms);
        contentValues.put(PATIENT_DOT_SIZE, dot_size);
        contentValues.put(PATIENT_QUADRANT_ONE, q1);
        contentValues.put(PATIENT_QUADRANT_TWO, q2);
        contentValues.put(PATIENT_QUADRANT_THREE, q3);
        contentValues.put(PATIENT_QUADRANT_FOUR, q4);
        contentValues.put(PATIENT_ACCURACY, accuracy);

        contentValues.put(PATIENT_RADIAL1_REACTION_TIME, patient_radial1_reaction_time);
        contentValues.put(PATIENT_RADIAL2_REACTION_TIME, patient_radial2_reaction_time);
        contentValues.put(PATIENT_RADIAL3_REACTION_TIME, patient_radial3_reaction_time);
        contentValues.put(PATIENT_RADIAL4_REACTION_TIME, patient_radial4_reaction_time);
        contentValues.put(PATIENT_RADIAL5_REACTION_TIME, patient_radial5_reaction_time);
        contentValues.put(PATIENT_RADIAL6_REACTION_TIME, patient_radial6_reaction_time);
        contentValues.put(PATIENT_RADIAL7_REACTION_TIME, patient_radial7_reaction_time);
        contentValues.put(PATIENT_RADIAL8_REACTION_TIME, patient_radial8_reaction_time);
        db.insert(PATIENT_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }


    public Cursor getData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"='"+id, null );
        res.close();
        db.close();
        return res;
    }
    public String getLatestPatientTest(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_NEURO_TEST));
            res.close();
            db.close();
            return ss;
        }
    }
    public String getPatientTotalTime(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_TOTAL_TIME));
            res.close();
            db.close();
            return ss;
        }
    }
    public String getPatientDotSize(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_DOT_SIZE));
            res.close();
            db.close();
            return ss;
        }
    }
    public String getPatientAccuracy(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_ACCURACY));
            res.close();
            db.close();
            return ss;
        }
    }
    public String getPatientQuadrantOne(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_QUADRANT_ONE));
            res.close();
            db.close();
            return ss;
        }
    }
    public String getPatientQuadrantTwo(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_QUADRANT_TWO));
            res.close();
            db.close();
            return ss;
        }
    }    public String getPatientQuadrantThree(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_QUADRANT_THREE));
            res.close();
            db.close();
            return ss;
        }
    }    public String getPatientQuadrantFour(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"="+id, null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return "0";
        }else {
            res.moveToLast();
            String ss = res.getString(res.getColumnIndex(PATIENT_QUADRANT_FOUR));
            res.close();
            db.close();
            return ss;
        }
    }
    public Integer getNewPatientId(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" order by "+PATIENT_PATIENT_ID+" desc", null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return 3456;
        }else {
            res.moveToFirst();
            Integer ss = Integer.parseInt(res.getString(res.getColumnIndex(PATIENT_PATIENT_ID))) + 1;
            res.close();
            db.close();
            return ss;
        }
    }
    public Boolean patient_id_exists(String pid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+" = '"+pid+"' ", null );
        if(res.getCount()==0){
            res.close();
            db.close();
            return false;
        }else {
            res.moveToFirst();
            Integer ss = Integer.parseInt(res.getString(res.getColumnIndex(PATIENT_PATIENT_ID))) + 1;
            res.close();
            db.close();
            return true;
        }
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PATIENT_TABLE_NAME);

        return numRows;
    }
    public boolean updateTest (Integer id, String patient_id, String waveform, String bpdata, String bphigh, int waveindex){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_PATIENT_ID, patient_id);
        contentValues.put(PATIENT_TEST_ID, bphigh);
        contentValues.put(PATIENT_NEURO_TEST, waveform);
        db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        db.close();
        return true;
    }
    public Integer deleteTest (Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PATIENT_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList<String> getAllTest(){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PATIENT_COLUMN_ID)));
            res.moveToNext();
        }
        res.close();
        db.close();
        return array_list;
    }
}