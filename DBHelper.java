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

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String PATIENT_TABLE_NAME = "Patient";
    public static final String PATIENT_COLUMN_ID = "test_id";
    public static final String PATIENT_PATIENT_ID = "patient_id";
    public static final String PATIENT_COLUMN_NAME = "patient_name";
    public static final String PATIENT_COLUMN_MOBILE = "patient_mobile";
    public static final String PATIENT_COLUMN_EMAIL = "patient_email";
    public static final String PATIENT_COLUMN_GENDER = "patient_gender";
    public static final String PATIENT_COLUMN_AGE = "patient_age";
    public static final String PATIENT_COLUMN_DATE = "test_date";
    public static final String PATIENT_COLUMN_TIME = "test_time";

    public static final String PATIENT_COLUMN_CONSULTANT = "consultant";
    public static final String PATIENT_COLUMN_SPECIALIZED = "specialized_by";
    public static final String PATIENT_COLUMN_REFERED = "refered_by";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.e("Table_Created ","Patient");
        db.execSQL(
                "CREATE TABLE " + PATIENT_TABLE_NAME
                        + " (" + PATIENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + PATIENT_PATIENT_ID + " TEXT, "
                        + PATIENT_COLUMN_NAME + " TEXT, "
                        + PATIENT_COLUMN_MOBILE + " TEXT, "
                        + PATIENT_COLUMN_EMAIL + " TEXT, "
                        + PATIENT_COLUMN_GENDER + " TEXT, "
                        + PATIENT_COLUMN_AGE + " TEXT, "
                        + PATIENT_COLUMN_DATE + " TEXT, "
                        + PATIENT_COLUMN_TIME + " TEXT )");

                        Log.e("Table_Created ","Patient");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+PATIENT_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertTest  (String patient_id, String name, String phone, String email, String gender, String age, String date, String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_PATIENT_ID, patient_id);
        contentValues.put(PATIENT_COLUMN_NAME, name);
        contentValues.put(PATIENT_COLUMN_MOBILE, phone);
        contentValues.put(PATIENT_COLUMN_EMAIL, email);
        contentValues.put(PATIENT_COLUMN_GENDER, gender);
        contentValues.put(PATIENT_COLUMN_AGE, age);
        contentValues.put(PATIENT_COLUMN_DATE, date);
        contentValues.put(PATIENT_COLUMN_TIME, time);
        db.insert(PATIENT_TABLE_NAME, null, contentValues);

        db.close();
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where id="+id+"", null );
        res.close();
        db.close();
        return res;
    }
    public boolean CheckIsMobileNumberAlreadyInDBorNot(String fieldValue) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String Query = "Select * from " + PATIENT_TABLE_NAME + " where " + PATIENT_COLUMN_MOBILE + " = " + fieldValue;
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public boolean CheckIsEmailAlreadyInDBorNot(String email) {
        SQLiteDatabase sqldb = this.getReadableDatabase();

        Cursor cursor = sqldb.rawQuery("Select * from " + PATIENT_TABLE_NAME + " where patient_email =\'"+ email+"\'",null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public Integer getLatestPatientTest(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_PATIENT_ID+"='"+id+"' order by "+PATIENT_COLUMN_TIME+" desc ", null );

        if(res.getCount()==0){
            res.close();
            db.close();
            return 0;
        }else {
            res.moveToFirst();
            Integer ss = Integer.parseInt(res.getString(res.getColumnIndex(PATIENT_COLUMN_ID))) + 1;
            res.close();
            db.close();
            return ss;
        }
    }


    public Cursor getPatientTestdeatils(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME+" where "+PATIENT_COLUMN_NAME, null );
        res.close();
        db.close();
        return res;
    }
    public String getGender(String id)
    {
        String con;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct "+PATIENT_COLUMN_GENDER+" from "+PATIENT_TABLE_NAME, null );
        res.moveToFirst();

      //  Cursor res =  db.rawQuery( "select distinct "+PATIENT_PATIENT_ID,PATIENT_COLUMN_NAME,PATIENT_COLUMN_AGE,PATIENT_COLUMN_GENDER,PATIENT_COLUMN_DATE,PATIENT_COLUMN_REFERED+" from "+PATIENT_TABLE_NAME+" where "+PATIENT_COLUMN_NAME+"='"+id+"'order by"+PATIENT_COLUMN_TIME+" desc ", null );
       // res.moveToFirst();

        con=res.getString(res.getColumnIndex(PATIENT_COLUMN_GENDER));

        res.close();
        db.close();
        return con;
    }

    public String[] getAllPatientData(String id)
    {
        String con[] = new String[6];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct "+PATIENT_PATIENT_ID+", "+PATIENT_COLUMN_NAME+", "+PATIENT_COLUMN_AGE+", "+PATIENT_COLUMN_GENDER+", "+PATIENT_COLUMN_DATE+", "+PATIENT_COLUMN_REFERED+" from "+PATIENT_TABLE_NAME+" where "+PATIENT_COLUMN_NAME+"='"+id+"'", null );
        res.moveToFirst();
        con[0]=res.getString(res.getColumnIndex(PATIENT_PATIENT_ID));
        con[1]=res.getString(res.getColumnIndex(PATIENT_COLUMN_NAME));
        con[2]=res.getString(res.getColumnIndex(PATIENT_COLUMN_AGE));
        con[3]=res.getString(res.getColumnIndex(PATIENT_COLUMN_GENDER));
        con[4]=res.getString(res.getColumnIndex(PATIENT_COLUMN_DATE));
        con[5]=res.getString(res.getColumnIndex(PATIENT_COLUMN_REFERED));

        res.close();
        db.close();
        return con;
    }




    public String getID(String id)
    {

        String con;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct "+PATIENT_PATIENT_ID+" from "+PATIENT_TABLE_NAME, null );
        res.moveToFirst();
        con=res.getString(res.getColumnIndex(PATIENT_PATIENT_ID));
        res.close();
        db.close();
        return con;
    }
    public String getAge(String id)
    {

        String con;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct "+PATIENT_COLUMN_AGE+" from "+PATIENT_TABLE_NAME, null );
        res.moveToFirst();
        con=res.getString(res.getColumnIndex(PATIENT_COLUMN_AGE));
        res.close();
        db.close();
        return con;
    }
    public String getDate(String id)
    {

        String con;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct "+PATIENT_COLUMN_DATE+" from "+PATIENT_TABLE_NAME, null );
        res.moveToFirst();
        con=res.getString(res.getColumnIndex(PATIENT_COLUMN_DATE));
        res.close();
        db.close();
        return con;
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

    public boolean updateTest (Integer id, String patient_id, String name, String phone, String email, String gender, String age, String date, String time, String consultant, String specialization, String refered_by)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_PATIENT_ID, patient_id);
        contentValues.put(PATIENT_COLUMN_NAME, name);
        contentValues.put(PATIENT_COLUMN_MOBILE, phone);
        contentValues.put(PATIENT_COLUMN_EMAIL, email);
        contentValues.put(PATIENT_COLUMN_GENDER, gender);
        contentValues.put(PATIENT_COLUMN_AGE, age);
        contentValues.put(PATIENT_COLUMN_DATE, date);
        contentValues.put(PATIENT_COLUMN_TIME, time);
        contentValues.put(PATIENT_COLUMN_CONSULTANT, consultant);
        contentValues.put(PATIENT_COLUMN_SPECIALIZED, specialization);
        contentValues.put(PATIENT_COLUMN_REFERED, refered_by);
        db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});

        db.close();
        return true;
    }


    public Integer deleteTest (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PATIENT_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllTest()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
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



    public String[] getConsultantList()
    {

String[] con=null;

        //hp = new HashMap();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct "+PATIENT_COLUMN_CONSULTANT+" from "+PATIENT_TABLE_NAME+"  order by "+PATIENT_COLUMN_ID+" desc limit 0,200", null );
        res.moveToFirst();
con=new String[res.getCount()];
        int i=0;
        while(res.isAfterLast() == false){

            con[i]=res.getString(res.getColumnIndex(PATIENT_COLUMN_CONSULTANT));
           i++;
            res.moveToNext();
        }
        res.close();
        db.close();
        return con;
    }




    public String[] getSpecialaizationList()
    {

        String[] con=null;

        //hp = new HashMap();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct "+PATIENT_COLUMN_SPECIALIZED+" from "+PATIENT_TABLE_NAME+" order by "+PATIENT_COLUMN_ID+" desc limit 0,200 ", null );
        res.moveToFirst();
        con=new String[res.getCount()];
        int i=0;
        while(res.isAfterLast() == false){

            con[i]=res.getString(res.getColumnIndex(PATIENT_COLUMN_SPECIALIZED));
            i++;
            res.moveToNext();
        }
        res.close();
        db.close();
        return con;
    }



    public String[] getReferedByList()
    {

        String[] con=null;

        //hp = new HashMap();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select distinct "+PATIENT_COLUMN_REFERED+" from "+PATIENT_TABLE_NAME+"  order by "+PATIENT_COLUMN_ID+" desc limit 0,200", null );
        res.moveToFirst();
        con=new String[res.getCount()];
        int i=0;
        while(res.isAfterLast() == false){

            con[i]=res.getString(res.getColumnIndex(PATIENT_COLUMN_REFERED));
            i++;
            res.moveToNext();
        }
        res.close();
        db.close();
        return con;
    }



    public Cursor getSearchCursorOut(String pid, String pname, String gen, String age, String date, String mob, String email)
    {


        String query=" where 1";
        if(!pid.contentEquals("")){
            query =query+" and "+PATIENT_PATIENT_ID+" ='"+pid+"'";
        }


        if(!pname.contentEquals("")){
            query =query+" and "+PATIENT_COLUMN_NAME+" like '%" + pname +"%'";
        }

        if(!gen.contains("Gender")&!gen.contentEquals("")){
            query =query+" and "+PATIENT_COLUMN_GENDER+" ='"+gen+"'";
        }

        if(!age.contentEquals("")){
            query =query+" and "+PATIENT_COLUMN_AGE+" ='"+age+"'";
        }

        if(!date.contentEquals("")){
            query =query+" and "+PATIENT_COLUMN_DATE+" ='"+date+"'";
        }

        if(!mob.contentEquals("")){
            query =query+" and "+PATIENT_COLUMN_MOBILE+" ='"+mob+"'";
        }

        if(!email.contentEquals("")){
            query =query+" and "+PATIENT_COLUMN_EMAIL+" ='"+email+"'";
        }

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+PATIENT_TABLE_NAME + query+" limit 0, 200", null );


        Log.e("raw query", "select * from " + PATIENT_TABLE_NAME + query + " limit 0, 200");

       return res;
    }

}