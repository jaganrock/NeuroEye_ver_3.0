package ragul.srushty.com.neuroeye;

import android.content.Context;
import android.database.Cursor;

import ragul.srushty.com.neuroeye.data.Car;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by karthi2 on 9/15/2016.
 */
public final class DataFactory {
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
    /**
     * Creates a list of cars.
     *
     * @return The created list of cars.
     */
    public static List<Car> createCarList(String pid, String pname, String gen, String age, String dates, String mob, String email, Context context) throws ParseException {
      //  final CarProducer audi = new CarProducer(R.drawable.audi, "Audi");

        String str_date="13-09-2011";
        DateFormat formatter ;
        Date date ;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        date = (Date)formatter.parse(str_date);

        String str_date2="12-09-2016";
        DateFormat formatter2 ;
        Date date2 ;
        formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        date2 = (Date)formatter2.parse(str_date2);

        String str_date3="12-09-2016";
        DateFormat formatter3 ;
        Date date3 ;
        formatter3 = new SimpleDateFormat("dd-MM-yyyy");
        date3 = (Date)formatter3.parse(str_date3);


        String str_date4="16-10-2016";
        DateFormat formatter4 ;
        Date date4 ;
        formatter4 = new SimpleDateFormat("dd-MM-yyyy");
        date4 = (Date)formatter4.parse(str_date4);


        String str_date5="18-10-2016";
        DateFormat formatter5 ;
        Date date5 ;
        formatter5 = new SimpleDateFormat("dd-MM-yyyy");
        date5 = (Date)formatter5.parse(str_date5);

        String str_date6="18-10-2016";
        DateFormat formatter6 ;
        Date date6 ;
        formatter6 = new SimpleDateFormat("dd-MM-yyyy");
        date6 = (Date)formatter6.parse(str_date6);

        final DBHelper    mydb = new DBHelper(context);

        final List<Car> cars = new ArrayList<>();
      Cursor cc=  mydb.getSearchCursorOut( pid,pname,  gen, age,  dates,  mob, email);

        cc.moveToFirst();

        while(cc.isAfterLast() == false){



            String str_dateN=cc.getString(cc.getColumnIndex(PATIENT_COLUMN_DATE));
            DateFormat formatterN ;
            Date dateN ;
            formatter6 = new SimpleDateFormat("dd/MM/yy");
            dateN = (Date)formatter6.parse(str_dateN);


            final Car patient1 = new Car(cc.getString(cc.getColumnIndex(PATIENT_COLUMN_NAME)) ,cc.getString(cc.getColumnIndex(PATIENT_PATIENT_ID)), cc.getString(cc.getColumnIndex(PATIENT_COLUMN_GENDER)),cc.getString(cc.getColumnIndex(PATIENT_COLUMN_AGE)), dateN,cc.getString(cc.getColumnIndex(PATIENT_COLUMN_MOBILE)),cc.getString(cc.getColumnIndex(PATIENT_COLUMN_EMAIL)));
            cars.add(patient1);
            cc.moveToNext();
        }





        return cars;
    }

}