package ragul.srushty.com.neuroeye;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import ragul.srushty.com.neuroeye.data.Car;
import com.jaredrummler.materialspinner.MaterialSpinner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.codecrafters.tableview.SortState;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.providers.SortStateViewProvider;

public class PatientSearch extends Activity implements OnClickListener, SortStateViewProvider {
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText edittext;
    Calendar myCalendar = Calendar.getInstance();

    private static final String[][] DATA_TO_SHOW = {{"This", "is", "a", "test"},
            {"and", "a", "second", "test"}};
    Context context;

    DBHelper mydb;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideKeyboard(this, findViewById(android.R.id.content).getWindowToken());
        return super.dispatchTouchEvent(ev);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientsearch);

        Log.e("Oncreate on"," Patient Search");
        mydb = new DBHelper(this);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.gender);
        spinner.setBackgroundResource(R.drawable.back_spinner);
        spinner.setTextColor(Color.BLACK);
        spinner.setItems("Select Gender", "Male", "Female");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

            }
        });

        int layoutItemId = android.R.layout.simple_dropdown_item_1line;
        DBHelper mdb = new DBHelper(this);

        edittext = (EditText) findViewById(R.id.date);
        edittext.setShowSoftInputOnFocus(false);
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(edittext.getWindowToken(), 0);


        final SortableCarTableView carTableView = (SortableCarTableView) findViewById(R.id.patient_table);
        if (carTableView != null) {
            try {
                carTableView.setDataAdapter(new CarTableDataAdapter(this, DataFactory.createCarList("", "", "", "", "", "", "",  PatientSearch.this)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.light_prime_color, typedValue, true);
            int color = typedValue.data;
            carTableView.setHeaderBackgroundColor(color);
            carTableView.addDataClickListener(new CarClickListener());
            carTableView.addDataLongClickListener(new CarLongClickListener());
        }

        final EditText patient_id = (EditText) findViewById(R.id.patient_id);
        final EditText patient_name = (EditText) findViewById(R.id.name);
        final MaterialSpinner gender_array = (MaterialSpinner) findViewById(R.id.gender);
        final EditText age = (EditText) findViewById(R.id.age);
        final EditText dates = (EditText) findViewById(R.id.date);
        final EditText mobile_number = (EditText) findViewById(R.id.mobile_number);
        final EditText email_address = (EditText) findViewById(R.id.email_address);

        mehdi.sakout.fancybuttons.FancyButton fabsub = (mehdi.sakout.fancybuttons.FancyButton) findViewById(R.id.save);

        fabsub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String vpatient_id = patient_id.getText().toString();
                String vpatient_name = patient_name.getText().toString();
                String vage = age.getText().toString();
                String vdate = dates.getText().toString();
                String vmobile_number = mobile_number.getText().toString();
                String vemail_address = email_address.getText().toString();
                String vgender = gender_array.getText().toString();

                if (carTableView != null) {
                    try {
                        carTableView.setDataAdapter(new CarTableDataAdapter(PatientSearch.this, DataFactory.createCarList(vpatient_id, vpatient_name, vgender, vage, vdate, vmobile_number, vemail_address, PatientSearch.this)));
                        Log.e("quried data", vpatient_id + vpatient_name + vgender + vage + vdate + vmobile_number + vemail_address);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = getTheme();
                    theme.resolveAttribute(R.attr.light_prime_color, typedValue, true);
                    int color = typedValue.data;
                    carTableView.setHeaderBackgroundColor(color);
                    carTableView.addDataClickListener(new CarClickListener());
                    carTableView.addDataLongClickListener(new CarLongClickListener());
                }
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(edittext.getWindowToken(), 0);

                    new DatePickerDialog(context, R.style.MyDatePickerDialogTheme, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                }
            }
        });
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PatientSearch.this,  R.style.MyDatePickerDialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
        context = this;
    }

    public void backclick(View view) {
        Intent i1 = new Intent(this, MainActivityL.class);
        startActivity(i1);
        finish();
    }

    @Override
    public void onClick(View v) {
        showDialog(0);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
        }
    };

    @Override
    public int getSortStateViewResource(SortState state) {
        return 0;
    }


    private static class MySortStateViewProvider implements SortStateViewProvider {

        private static final int NO_IMAGE_RES = -1;

        @Override
        public int getSortStateViewResource(SortState state) {
            switch (state) {
                case SORTABLE:
                    //   return R.mipmap.ic_sortable;
                case SORTED_ASC:
                    // return R.mipmap.ic_sorted_asc;
                case SORTED_DESC:
                    // return R.mipmap.ic_sorted_desc;
                default:
                    return NO_IMAGE_RES;
            }
        }
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edittext.setText(sdf.format(myCalendar.getTime()));
    }
    public int getAge(long bageInMillis) {
        long ageInMillis = new Date().getTime() - bageInMillis;
        Date age = new Date(ageInMillis);
        return age.getYear();
    }
    char m ='\u27a9';
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    private class CarClickListener implements TableDataClickListener<Car> {

        @Override
        public void onDataClicked(final int rowIndex, final Car clickedData) {

            Date tdate = clickedData.getTestDate();
            Date cdate = new Date();

            Log.e("DateChecker_1", String.valueOf(tdate) + "----" + cdate);

            long duration = cdate.getTime() - tdate.getTime();

            Log.e("DateChecker_totdays_1", "" + duration / DAY);
            long diff = duration / DAY;

            int yr = 366;

            long fduration = tdate.getTime() + duration;
            Log.e("DateChecker_2", String.valueOf(duration) + "----" + fduration);

            final int curage = (int) (diff / yr);
            //final Integer curage = getAge(duration);
            Log.e("DateChecker_3", String.valueOf(curage));
            Integer ccurage;
            if (curage > 0) {
                ccurage = Integer.parseInt(clickedData.getPatientAge()) + curage;
            } else {
                ccurage = Integer.parseInt(clickedData.getPatientAge());
            }

            Log.e("DateChecker_4", String.valueOf(ccurage));
            final Integer cccurage = ccurage;
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            final String vdate = sdf.format(cal.getTime());

            Long tsLong = System.currentTimeMillis() / 1000;
            final String ts = tsLong.toString();

            AlertDialog.Builder adb = new AlertDialog.Builder(PatientSearch.this);

            adb.setTitle(clickedData.getPatientName());
            adb.setMessage("Are you sure to login with this user?");

            adb.setIcon(android.R.drawable.ic_dialog_alert);

            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    mydb.insertTest(clickedData.getPatientId(), clickedData.getPatientName(), clickedData.getPatientMobile(), clickedData.getPatientEmail(), clickedData.getPatientGender(), cccurage.toString(), vdate, ts);

                    final String loggedIn = "logged_in";
                    final String patientId = "logged_in_patientId";
                    final String patientage = "logged_in_patientage";
                    final String patienttestdate = "logged_in_patienttestdate";
                    final String patientgender = "logged_in_patientgender";
                    final String loggedInTestId = "logged_in_test_id";
                    final String patientemail = "logged_in_patientemail";
                    final String patientphonenumber = "logged_in_patientphonenumber";

                    Integer ss = mydb.getLatestPatientTest(clickedData.getPatientId());

                    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(loggedIn,clickedData.getPatientName());
                    editor.putString(patientId, clickedData.getPatientId());
                    editor.putString(patientage, cccurage.toString());
                    editor.putString(patienttestdate, vdate);
                    editor.putString(patientgender, clickedData.getPatientGender());
                    editor.putString(patientemail, clickedData.getPatientEmail());
                    editor.putString(patientphonenumber, clickedData.getPatientMobile());
                    editor.putInt(loggedInTestId, ss);
                    editor.commit();

                    mydb.getLatestPatientTest(clickedData.getPatientId());
                    Intent in = new Intent(context, SelectingModeActivity.class);
                    startActivity(in);
                    finish();
                }
            });
            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            adb.show();
            final String carString = "Click: " + clickedData.getPatientName() + " " + clickedData.getPatientId();
          //  Toast.makeText(context, carString, Toast.LENGTH_SHORT).show();
        }
    }

    private class CarLongClickListener implements TableDataLongClickListener<Car> {
        @Override
        public boolean onDataLongClicked(final int rowIndex, final Car clickedData) {
            final String carString = "Click: " + clickedData.getPatientName() + " " + clickedData.getPatientId();
          //  Toast.makeText(context, carString, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public static void hideKeyboard(Context context, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}