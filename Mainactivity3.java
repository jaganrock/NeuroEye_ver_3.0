package ragul.srushty.com.neuroeye;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import com.jaredrummler.materialspinner.MaterialSpinner;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ragul.srushty.com.neuroeye.data.Car;
import de.codecrafters.tableview.SortState;
import de.codecrafters.tableview.providers.SortStateViewProvider;

public class Mainactivity3 extends Activity implements OnClickListener, SortStateViewProvider {
    private ImageButton ib;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText edittext;
    Calendar myCalendar = Calendar.getInstance();
    EditText _editText;

    private static final String[][] DATA_TO_SHOW = { { "This", "is", "a", "test" },
            { "and", "a", "second", "test" } };

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.patiententry);
        final DBHelper    mydb = new DBHelper(this);
        View decorView = getWindow().getDecorView();
        final EditText patient_id = (EditText) findViewById(R.id.patient_id);
        final EditText patient_name = (EditText) findViewById(R.id.name);

        final MaterialSpinner gender_array = (MaterialSpinner) findViewById(R.id.gender);
        final EditText age = (EditText) findViewById(R.id.age);
        final EditText dates = (EditText) findViewById(R.id.date);
        final EditText mobile_number = (EditText) findViewById(R.id.mobile_number);
        final EditText email_address = (EditText) findViewById(R.id.email_address);


        mehdi.sakout.fancybuttons.FancyButton fabgen=(mehdi.sakout.fancybuttons.FancyButton) findViewById(R.id.generate);
        mehdi.sakout.fancybuttons.FancyButton fabsub=(mehdi.sakout.fancybuttons.FancyButton) findViewById(R.id.save);

        fabgen.setOnClickListener(new OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          patient_id.setText(mydb.getNewPatientId()+"");
                                          Log.e("Patient Id",""+mydb.getNewPatientId());
                                      }
                                  });

                fabsub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String vpatient_id=  patient_id.getText().toString();
                String vpatient_name=  patient_name.getText().toString();
                String vage=age.getText().toString();
                String vdate=dates.getText().toString();
                String vmobile_number=mobile_number.getText().toString();
                String vemail_address=email_address.getText().toString();
                String vgender = gender_array.getText().toString();

                try {
                    int num = Integer.parseInt((vpatient_id));
                  } catch (NumberFormatException e) {
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.nan_error));
                    return;
                }
                if(mydb.patient_id_exists(vpatient_id)){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.pid_exists));
                    return;
                }
                if(vpatient_name.length()==0){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.pname_empty));
                    return;
                }
                if(vpatient_name.matches(" ")){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.pname_empty));
                    return;
                }
                Log.e("Patient",""+validateLetters(vpatient_name));
                if(!validateLetters(vpatient_name)){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.pname_empty_invalid));
                    return;
                }
                if(vgender.contains("Select Gender")){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.gender_required));
                    return;
                }
                if(vage.length()==0){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.age_required));
                    return;
                }
//                if(vage.length()<4){
//                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.age_required_invaild));
//                    return;
//                }
                if(Integer.parseInt(vage)<17){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.age_required_above));
                    return;
                }
                if(Integer.parseInt(vage)>99){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.age_required_below ));
                    return;
                }
                if(vdate.length()==0){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.date_required));
                    return;
                }
                Log.e("email",""+isValidEmail(vemail_address));
                if(!isValidEmail(vemail_address)){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.email_id));
                    return;
                }
                Log.e("Email Present",""+mydb.CheckIsEmailAlreadyInDBorNot(vemail_address));
                if(mydb.CheckIsEmailAlreadyInDBorNot(vemail_address)){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.email_id_already));
                    return;
                }

                if(vmobile_number.length()==0||vmobile_number.length()!=10){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.mob_not_valid));
                    return;
                }
                Log.e("Mobile Number Present",""+mydb.CheckIsMobileNumberAlreadyInDBorNot(vmobile_number));
                if(mydb.CheckIsMobileNumberAlreadyInDBorNot(vmobile_number)){
                    validation_dialog(getResources().getString(R.string.error),getResources().getString(R.string.mob_already_present));
                    return;
                }
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();

                mydb.insertTest(vpatient_id, vpatient_name, vmobile_number, vemail_address, vgender, vage, vdate, ts);

                final String loggedIn = "logged_in";
                final String patientId = "logged_in_patientId";
                final String patientage = "logged_in_patientage";
                final String patienttestdate = "logged_in_patienttestdate";
                final String patientgender = "logged_in_patientgender";
                final String loggedInTestId = "logged_in_test_id";
                final String patientemail = "logged_in_patientemail";
                final String patientphonenumber = "logged_in_patientphonenumber";

                Integer ss = mydb.getLatestPatientTest(vpatient_id);

                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(loggedIn,vpatient_name);
                editor.putString(patientId, vpatient_id);
                editor.putString(patientage, vage);
                editor.putString(patienttestdate, vdate);
                editor.putString(patientgender, vgender);
                editor.putString(patientemail, vemail_address);
                editor.putString(patientphonenumber, vmobile_number);
                editor.putInt(loggedInTestId, ss);
                editor.commit();

                Intent in = new Intent(context, SelectingModeActivity.class);
                startActivity(in);
                finish();
            }
        });

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
        spinner.setItems("Select Gender", "Male", "Female");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
               // Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        int layoutItemId = android.R.layout.simple_dropdown_item_1line;
        String[] dogsArr = getResources().getStringArray(R.array.dogs_list);
        List<String> dogList = Arrays.asList(dogsArr);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layoutItemId, dogList);

        edittext =(EditText) findViewById(R.id.date);
        edittext.setShowSoftInputOnFocus(false);
        InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(edittext.getWindowToken(), 0);

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

                    new DatePickerDialog(Mainactivity3.this,  R.style.MyDatePickerDialogTheme, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    } else {
                    Log.e("date","focus true");
                    }
            }
        });
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Mainactivity3.this,  R.style.MyDatePickerDialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
        context=this;
    }
    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean validateLetters(String txt) {

        String regx = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();

    }
    public void backbutton(View view) {
        Intent i1 = new Intent(this, MainActivityL.class);
        startActivity(i1);
        finish();
    }
    @Override
    public void onClick(View v) {
        showDialog(0);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideKeyboard(this, findViewById(android.R.id.content).getWindowToken());
        return super.dispatchTouchEvent(ev);
    }
    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
        //    et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
          //          + selectedYear);
        }
    };
    void validation_dialog(String title, String content) {

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        dialog.dismiss();
                    }
                })
                .show();
    }
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

    public static void hideKeyboard(Context context, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}