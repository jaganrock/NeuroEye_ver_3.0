package ragul.srushty.com.neuroeye;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.print.PrintHelper;
import android.print.PrintManager;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Testing extends AppCompatActivity {

    Button circle1,circle2,circle3,circle4,circle5,circle6,circle7,circle8,circle9,circle10,circle11,circle12,circle13,circle14,circle15,circle16;


    Button btn_generate;
    RelativeLayout ll_pdflayout;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    boolean boolean_save;
    Bitmap bitmap;
    ProgressDialog progressDialog;

    Button circlea,circleb,circlec,circled,circlee,circlef,circleg,circleh;
    Button circle_circle;

    TextView patient_name,Duration_in_seconds,Stimuli_Size,Duration_seconds,Testdate,Average_React_TM,Hits,Missed,Percentage,Q1,Q2,Q3,Q4;

    Button save_pdf,Print_button;
    TextView Radial_1,Radial_2,Radial_3,Radial_4,Radial_5,Radial_6,Radial_7,Radial_8;

    Button lifebutton1;
    Button lifebutton2;
    Button lifebutton3;
    RelativeLayout ok_module;
    RelativeLayout grpbtn;
    String level="Level 1";
    String dotsize="2mm";

    int buttonpressed=0;
    int buttonrounds=0;

    DBHelperPatientLastTest mydb;

    long startTime=0, endTime=0,finaltime=0;
    Boolean firsttime=false;
    TextView timertext;
    ImageView background_image;
    long totaltime=0;
    int min=1,max=16;
    ArrayList<Integer> list = new ArrayList<Integer>();

    String local_loggedin;
    String local_patientid;
    String local_patientage;
    String local_patienttestdate;
    String local_patientgender;
    String local_patientemail;
    String local_patientphonenumber;
    String local_patient_history_test_value="000";
    int local_loggedInTestId;
    String Circle_size="2mm";

    int accuracyTouch=0;
    int button_pressed=72;

    long quadrant1=0;
    long quadrant2=0;
    long quadrant3=0;
    long quadrant4=0;

    long radial_one=0;
    long radial_two=0;
    long radial_three=0;
    long radial_four=0;
    long radial_five=0;
    long radial_six=0;
    long radial_seven=0;
    long radial_eight=0;

    Button person_type_selection;
    Button Spot_graph;
    RelativeLayout person_select;
    boolean touch_center_cicrle_first_time=true;

    private PrintedPdfDocument mDocument;
    private Context mContext;
    private View mView;

    String targetPdf;
    File filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.testing);
        randomnumber();

        mydb = new DBHelperPatientLastTest(this);
        ll_pdflayout = (RelativeLayout) findViewById(R.id.print_layout);
        fn_permission();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.e("ApplicationTagName", "Display width in px is " + metrics.widthPixels);
        Log.e("ApplicationTagName", "Display width in px is " + metrics.heightPixels);

        final String loggedIn = "logged_in";
        final String patientId = "logged_in_patientId";
        final String patientage = "logged_in_patientage";
        final String patienttestdate = "logged_in_patienttestdate";
        final String patientgender = "logged_in_patientgender";
        final String loggedInTestId = "logged_in_test_id";
        final String patientemail = "logged_in_patientemail";
        final String patientphonenumber = "logged_in_patientphonenumber";

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Testing.this);
        local_loggedin= prefs.getString(loggedIn,"");
        local_patientid=prefs.getString(patientId,"");
        local_patientage=prefs.getString(patientage, "");
        local_patienttestdate=prefs.getString(patienttestdate, "");
        local_patientgender=prefs.getString(patientgender, "");
        local_patientemail=prefs.getString(patientemail,"");
        local_patientphonenumber=prefs.getString(patientphonenumber, "");
        local_loggedInTestId=prefs.getInt(loggedInTestId, 0);
        person_select=(RelativeLayout) findViewById(R.id.instruction_RL);

        person_type_selection=(Button) findViewById(R.id.persontype_dialogue);
        save_pdf= (Button)findViewById(R.id.PDF_button);
        Print_button= (Button)findViewById(R.id.Print_button);
        Spot_graph=(Button) findViewById(R.id.graph_button);
        person_type_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_select.setVisibility(View.GONE);
            }
        });


        Print_button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                progressDialog = new ProgressDialog(Testing.this);
                progressDialog.setMessage("Please wait");
                bitmap = loadBitmapFromView(ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight());
                createPdf1("NeuroEye1");
                filePath = new File(targetPdf);
                Intent intent = getPackageManager().getLaunchIntentForPackage("jp.co.canon.bsd.ad.pixmaprint");
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setDataAndType(Uri.fromFile(filePath), "application/pdf");
                startActivity(intent);

            }
        });
        Spot_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Testing.this,LineChartActivity1.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });

        save_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boolean_permission) {
                    progressDialog = new ProgressDialog(Testing.this);
                    progressDialog.setMessage("Please wait");
                    bitmap = loadBitmapFromView(ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight());
                    createPdf("NeuroEye");
//                        saveBitmap(bitmap);
                } else {

                }

            }
        });
        ok_module = (RelativeLayout) findViewById(R.id.module_window);
        grpbtn = (RelativeLayout) findViewById(R.id.grpbtn);
        Button ok_button = (Button) findViewById(R.id.ok_button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Testing.this,MainActivityL.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });
        final Spinner dotsize_Button=(Spinner) findViewById(R.id.dotsize);
        final TextView dotsize_text=(TextView) findViewById(R.id.dotsize_text);
        background_image=(ImageView) findViewById(R.id.backgnd_image);
        final Spinner spinner=(Spinner) findViewById(R.id.spinner1);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (position == 0) {
                    Log.e("Adult","isChecked");
                    dotsize_Button.setVisibility(View.VISIBLE);
                    dotsize_text.setVisibility(View.VISIBLE);
                    dotsize_Button.setSelection(0);
                    background_image.setBackgroundResource(R.drawable.rsz_twommdots);
                } else if (position == 1) {
                    Log.e("Child","isChecked");
                    dotsize_Button.setVisibility(View.GONE);
                    dotsize_text.setVisibility(View.GONE);
                    background_image.setBackgroundResource(R.drawable.rsz_eightmmdots);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        circle1= (Button) findViewById(R.id.Circle1);
        circle2= (Button) findViewById(R.id.Circle2);
        circle3= (Button) findViewById(R.id.Circle3);
        circle4= (Button) findViewById(R.id.Circle4);
        circle5= (Button) findViewById(R.id.Circle5);
        circle6= (Button) findViewById(R.id.Circle6);
        circle7= (Button) findViewById(R.id.Circle7);
        circle8= (Button) findViewById(R.id.Circle8);
        circle9= (Button) findViewById(R.id.Circle9);
        circle10= (Button) findViewById(R.id.Circle10);
        circle11= (Button) findViewById(R.id.Circle11);
        circle12= (Button) findViewById(R.id.Circle12);
        circle13= (Button) findViewById(R.id.Circle13);
        circle14= (Button) findViewById(R.id.Circle14);
        circle15= (Button) findViewById(R.id.Circle15);
        circle16= (Button) findViewById(R.id.Circle16);
        circlea= (Button) findViewById(R.id.Circlea);
        circleb= (Button) findViewById(R.id.Circleb);
        circlec= (Button) findViewById(R.id.Circlec);
        circled= (Button) findViewById(R.id.Circled);
        circlee= (Button) findViewById(R.id.Circlee);
        circlef= (Button) findViewById(R.id.Circlef);
        circleg= (Button) findViewById(R.id.Circleg);
        circleh= (Button) findViewById(R.id.Circleh);
        circle_circle= (Button) findViewById(R.id.CircleCenter);


        patient_name= (TextView) findViewById(R.id.textView1);
        Duration_in_seconds= (TextView) findViewById(R.id.textView2);
        Testdate= (TextView) findViewById(R.id.textView3);
        Stimuli_Size= (TextView) findViewById(R.id.SettingstextView3);
        Duration_seconds= (TextView) findViewById(R.id.ResultsoveraltextView11);
        Average_React_TM= (TextView) findViewById(R.id.ResultsoveraltextView12);
        Hits= (TextView) findViewById(R.id.ResultsoveraltextView2);
        Missed= (TextView) findViewById(R.id.ResultsoveraltextView3);
        Percentage= (TextView) findViewById(R.id.ResultsoveraltextView4);
        Q1= (TextView) findViewById(R.id.quardenttextView02);
        Q2= (TextView) findViewById(R.id.quardenttextView03);
        Q3= (TextView) findViewById(R.id.quardenttextView04);
        Q4= (TextView) findViewById(R.id.quardenttextView05);
        Radial_1= (TextView) findViewById(R.id.RadialtextView11);
        Radial_2= (TextView) findViewById(R.id.RadialtextView12);
        Radial_3= (TextView) findViewById(R.id.RadialtextView13);
        Radial_4= (TextView) findViewById(R.id.RadialtextView14);
        Radial_5= (TextView) findViewById(R.id.RadialtextView1);
        Radial_6= (TextView) findViewById(R.id.RadialtextView2);
        Radial_7= (TextView) findViewById(R.id.RadialtextView3);
        Radial_8= (TextView) findViewById(R.id.RadialtextView4);

        timertext= (TextView) findViewById(R.id.timertext1);
        lifebutton1= (Button) findViewById(R.id.life1);
        lifebutton2= (Button) findViewById(R.id.life2);
        lifebutton3= (Button) findViewById(R.id.life3);

        circle_circle.setBackgroundColor(Color.WHITE);
        circle1.setBackgroundColor(Color.BLACK);
        circle2.setBackgroundColor(Color.BLACK);
        circle3.setBackgroundColor(Color.BLACK);
        circle4.setBackgroundColor(Color.BLACK);
        circle5.setBackgroundColor(Color.BLACK);
        circle6.setBackgroundColor(Color.BLACK);
        circle7.setBackgroundColor(Color.BLACK);
        circle8.setBackgroundColor(Color.BLACK);
        circle9.setBackgroundColor(Color.BLACK);
        circle10.setBackgroundColor(Color.BLACK);
        circle11.setBackgroundColor(Color.BLACK);
        circle12.setBackgroundColor(Color.BLACK);
        circle13.setBackgroundColor(Color.BLACK);
        circle14.setBackgroundColor(Color.BLACK);
        circle15.setBackgroundColor(Color.BLACK);
        circle16.setBackgroundColor(Color.BLACK);
        circlea.setBackgroundColor(Color.BLACK);
        circleb.setBackgroundColor(Color.BLACK);
        circlec.setBackgroundColor(Color.BLACK);
        circled.setBackgroundColor(Color.BLACK);
        circlee.setBackgroundColor(Color.BLACK);
        circlef.setBackgroundColor(Color.BLACK);
        circleg.setBackgroundColor(Color.BLACK);
        circleh.setBackgroundColor(Color.BLACK);

        Spinner dotdropdown = findViewById(R.id.dotsize);
        String[] dot_items = new String[]{"2mm", "4mm", "8mm"};
        ArrayAdapter<String> dot_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dot_items);
        dotdropdown.setAdapter(dot_adapter);

        dotdropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.e("item", (String) parent.getItemAtPosition(position));
                String level_selected = (String) parent.getItemAtPosition(position);
                if(level_selected.contains("2mm")){
                    dotsize="2mm";
                    Circle_size="2mm";
                    background_image.setBackgroundResource(R.drawable.rsz_twommdots);
                    Log.e("Image Dot Size","2");
                }else if(level_selected.contains("4mm")){
                    dotsize="4mm";
                    Circle_size="4mm";
                    background_image.setBackgroundResource(R.drawable.rsz_fourmmdots);
                    Log.e("Image Dot Size","4");
                }else{
                    dotsize="8mm";
                    Circle_size="8mm";
                    background_image.setBackgroundResource(R.drawable.rsz_eightmmdots);
                    Log.e("Image Dot Size","8");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        findViewById(R.id.CircleCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(touch_center_cicrle_first_time){
                    touch_center_cicrle_first_time=false;
                    ColorDrawable buttonColor = (ColorDrawable) circle_circle.getBackground();
                    int colorId = buttonColor.getColor();
                    Log.e("Current Color Number","="+colorId);
                    if(colorId==-7829368){

                    }else{
                        button_pressed--;
                        Log.e("Button Pressed",""+button_pressed);
                        if(colorId == -1){
                            // accuracyTouch++;
                        }
                        if(firsttime){
                            //stop Time
                            endTime=System.currentTimeMillis();
                            totaltime=totaltime+finaltime;
                            finaltime=endTime-startTime;
                            Log.e("Time Diff","in MS"+finaltime);
                            timertext.setText("Reaction Time :"+finaltime+" ms");
                            firsttime=true;
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                startTime=System.currentTimeMillis();
                            }
                        }, 10);

                        ResetColor();
                        Log.e("button Number","="+buttonpressed);
                        if(buttonpressed>23 && buttonrounds<4){
                            Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                            randomnumber();
                        }
                        if(buttonrounds>=4){
                            Log.e("Three Rounds Compeleted","="+buttonrounds);
                            completiondialog();
                        }else{
                            int i1 = list.get(buttonpressed);
                            buttonpressed++;
                            Log.e("Random Number","="+i1);
                            changeCircleGreen(i1);
                        }}}
            }
        });
        findViewById(R.id.Circle1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle1.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    if(firsttime){
                        //stop Time
                        endTime=System.currentTimeMillis();
                        totaltime=totaltime+finaltime;
                        finaltime=endTime-startTime;
                        Log.e("Time Diff","in MS"+finaltime);
                        timertext.setText("Reaction Time :"+finaltime+" ms");
                        firsttime=true;
                    }
                    quadrant1=quadrant1+finaltime;
                    radial_one=radial_one+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle2.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);

                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_eight=radial_eight+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant4=quadrant4+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();
                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable buttonColor = (ColorDrawable) circle3.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){
                }else{
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);

                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_two=radial_two+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant4=quadrant4+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();
                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle4.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){
                }else{
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);

                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_one=radial_one+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant3=quadrant3+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle5.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);

                if(colorId==-7829368){}else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_eight=radial_eight+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant3=quadrant3+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle6.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_two=radial_two+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant2=quadrant2+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle7.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_six=radial_six+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time  :"+finaltime+" ms");
                    quadrant2=quadrant2+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle8.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_seven=radial_seven+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time  :"+finaltime+" ms");
                    quadrant2=quadrant2+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle9.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_seven=radial_seven+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant1=quadrant1+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle10.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_three=radial_three+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant1=quadrant1+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle11.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_three=radial_three+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant4=quadrant4+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle12.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_six=radial_six+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant4=quadrant4+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle13.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_five=radial_five+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant3=quadrant3+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ColorDrawable buttonColor = (ColorDrawable) circle14.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);

                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_four=radial_four+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant3=quadrant3+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circle15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle15.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_four=radial_four+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant2=quadrant2+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);}}
            }
        });
        findViewById(R.id.Circle16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circle16.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){
                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_five=radial_five+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant1=quadrant1+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });

        //****************************************
        findViewById(R.id.Circlea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circlea.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    if(firsttime){
                        //stop Time
                        endTime=System.currentTimeMillis();
                        totaltime=totaltime+finaltime;
                        finaltime=endTime-startTime;
                        radial_one=radial_one+finaltime;
                        Log.e("Time Diff","in MS"+finaltime);
                        timertext.setText("Reaction Time :"+finaltime+" ms");
                        quadrant1=quadrant1+finaltime;
                        firsttime=true;
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circleb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circleb.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_eight=radial_eight+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant4=quadrant4+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();
                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circlec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable buttonColor = (ColorDrawable) circlec.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){
                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_seven=radial_seven+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant4=quadrant4+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();
                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circled).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circled.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){
                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_six=radial_six+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant3=quadrant3+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circlee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circlee.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);

                if(colorId==-7829368){}else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_five=radial_five+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant3=quadrant3+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circlef).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circlef.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_four=radial_four+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    quadrant2=quadrant2+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circleg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circleg.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_three=radial_three+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time  :"+finaltime+" ms");
                    quadrant2=quadrant2+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
        findViewById(R.id.Circleh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ColorDrawable buttonColor = (ColorDrawable) circleh.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-7829368){

                }else{
                    if(colorId == -1){
                        accuracyTouch++;
                    }
                    button_pressed--;
                    Log.e("Button Pressed",""+button_pressed);
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    totaltime=totaltime+finaltime;
                    radial_two=radial_two+finaltime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time  :"+finaltime+" ms");
                    quadrant1=quadrant1+finaltime;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();

                    Log.e("button Number","="+buttonpressed);
                    if(buttonpressed>23 && buttonrounds<4){
                        Log.e("Round Number","="+buttonrounds+" Button Pressed "+buttonpressed);
                        randomnumber();
                    }
                    if(buttonrounds>=4){
                        Log.e("Three Rounds Compeleted","="+buttonrounds);
                        completiondialog();
                    }else{
                        int i1 = list.get(buttonpressed);
                        buttonpressed++;
                        Log.e("Random Number","="+i1);
                        changeCircleGreen(i1);
                    }}
            }
        });
    }
    public void ResetColor(){
        circle1.setBackgroundColor(Color.BLACK);
        circle2.setBackgroundColor(Color.BLACK);
        circle3.setBackgroundColor(Color.BLACK);
        circle4.setBackgroundColor(Color.BLACK);
        circle5.setBackgroundColor(Color.BLACK);
        circle6.setBackgroundColor(Color.BLACK);
        circle7.setBackgroundColor(Color.BLACK);
        circle8.setBackgroundColor(Color.BLACK);
        circle9.setBackgroundColor(Color.BLACK);
        circle10.setBackgroundColor(Color.BLACK);
        circle11.setBackgroundColor(Color.BLACK);
        circle12.setBackgroundColor(Color.BLACK);
        circle13.setBackgroundColor(Color.BLACK);
        circle14.setBackgroundColor(Color.BLACK);
        circle15.setBackgroundColor(Color.BLACK);
        circle16.setBackgroundColor(Color.BLACK);
        circlea.setBackgroundColor(Color.BLACK);
        circleb.setBackgroundColor(Color.BLACK);
        circlec.setBackgroundColor(Color.BLACK);
        circled.setBackgroundColor(Color.BLACK);
        circlee.setBackgroundColor(Color.BLACK);
        circlef.setBackgroundColor(Color.BLACK);
        circleg.setBackgroundColor(Color.BLACK);
        circleh.setBackgroundColor(Color.BLACK);
        circle_circle.setBackgroundColor(Color.BLACK);
    }
    public void randomnumber(){
        buttonpressed=0;
        buttonrounds++;
        for (int i=1; i<25; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
    }

    public void targetpdf()
    {

    }

    public void changeCircleGreen(final int i1){
        circle_circle.setBackgroundColor(Color.WHITE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                circle_circle.setBackgroundColor(Color.BLACK);
                if(i1 == 1){
                    circle1.setBackgroundColor(Color.WHITE);
                }else if(i1 ==2 ){
                    circle2.setBackgroundColor(Color.WHITE);
                }else if(i1 ==3 ){
                    circle3.setBackgroundColor(Color.WHITE);
                }else if(i1 ==4 ){
                    circle8.setBackgroundColor(Color.WHITE);
                }else if(i1 ==5 ){
                    circle7.setBackgroundColor(Color.WHITE);
                }else if(i1 ==6 ){
                    circle16.setBackgroundColor(Color.WHITE);
                }else if(i1 ==7 ){
                    circle15.setBackgroundColor(Color.WHITE);
                }else if(i1 ==8 ){
                    circle11.setBackgroundColor(Color.WHITE);
                }else if(i1 ==9 ){
                    circle4.setBackgroundColor(Color.WHITE);
                }else if(i1 ==10 ){
                    circle5.setBackgroundColor(Color.WHITE);
                }else if(i1 ==11 ){
                    circle6.setBackgroundColor(Color.WHITE);
                }else if(i1 ==12 ){
                    circle9.setBackgroundColor(Color.WHITE);
                }else if(i1 ==13 ){
                    circle12.setBackgroundColor(Color.WHITE);
                }else if(i1 ==14 ){
                    circle14.setBackgroundColor(Color.WHITE);
                }else if(i1 ==15 ){
                    circle13.setBackgroundColor(Color.WHITE);
                }else if(i1 ==16 ){
                    circle10.setBackgroundColor(Color.WHITE);
                }else if(i1 ==17 ){
                    circlea.setBackgroundColor(Color.WHITE);
                }else if(i1 ==18 ){
                    circleb.setBackgroundColor(Color.WHITE);
                }else if(i1 ==19 ){
                    circlec.setBackgroundColor(Color.WHITE);
                }else if(i1 ==20 ){
                    circled.setBackgroundColor(Color.WHITE);
                }else if(i1 ==21 ){
                    circlee.setBackgroundColor(Color.WHITE);
                }else if(i1 ==22 ){
                    circlef.setBackgroundColor(Color.WHITE);
                }else if(i1 ==23 ){
                    circleg.setBackgroundColor(Color.WHITE);
                }else if(i1 ==24 ){
                    circleh.setBackgroundColor(Color.WHITE);
                }
            }
        }, 500);

    }
    public void backclick(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(Testing.this).create();
        alertDialog.setTitle("Exit Test");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Do you want to exit this test without completing? Press Yes to Exit or Press No to Continue");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "No To Continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes To Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Testing.this,MainActivityL.class);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                        finish();
                    }
                });

        alertDialog.show();
    }
    public void completiondialog(){


        grpbtn.setVisibility(View.GONE);
        ok_module.setVisibility(View.VISIBLE);
        long total_avg_ms=(totaltime/72);
        String totalAccuracy=String.valueOf((accuracyTouch*100)/72);


        final String grp_patient_name = "grp_patientname";
        final String grp_tot_time = "grp_totaltime";
        final String grp_test_date = "grp_trstdate";
        final String grp_stimuli_size = "grp_stimulisize";
        final String grp_compl_time = "grp_comlTM";
        final String grp_avg_rect_time = "grp_averagereactiontime";
        final String grp_hits = "grp_hits_value";
        final String grp_missed = "grp_missed_value";
        final String grp_perc = "grp_percentage_value";
        final String grp_q1 = "grp_quadrent1";
        final String grp_q2 = "grp_quadrent2";
        final String grp_q3 = "grp_quadrent3";
        final String grp_q4 = "grp_quadrent4";
        final String grp_radial1 = "grp_radial1_value";
        final String grp_radial2 = "grp_radial2_value";
        final String grp_radial3 = "grp_radial3_value";
        final String grp_radial4 = "grp_radial4_value";
        final String grp_radial5 = "grp_radial5_value";
        final String grp_radial6 = "grp_radial6_value";
        final String grp_radial7 = "grp_radial7_value";
        final String grp_radial8 = "grp_radial8_value";

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(grp_patient_name,local_loggedin);
        editor.putString(grp_tot_time, String.valueOf(totaltime/1000));
        editor.putString(grp_test_date,local_patienttestdate );
        editor.putString(grp_stimuli_size,dotsize );
        editor.putString(grp_compl_time, String.valueOf(totaltime/1000));
        editor.putString(grp_avg_rect_time, String.valueOf(total_avg_ms));
        editor.putString(grp_hits, String.valueOf(accuracyTouch));
        editor.putString(grp_missed, String.valueOf((72-accuracyTouch)));
        editor.putString(grp_perc,totalAccuracy);
        editor.putString(grp_q1, String.valueOf(quadrant1/18));
        editor.putString(grp_q2, String.valueOf(quadrant2/18));
        editor.putString(grp_q3, String.valueOf(quadrant3/18));
        editor.putString(grp_q4, String.valueOf(quadrant4/18));
        editor.putString(grp_radial1, String.valueOf(radial_one/9));
        editor.putString(grp_radial2, String.valueOf(radial_two/9));
        editor.putString(grp_radial3, String.valueOf(radial_three/9));
        editor.putString(grp_radial4, String.valueOf(radial_four/9));
        editor.putString(grp_radial5, String.valueOf(radial_five/9));
        editor.putString(grp_radial6, String.valueOf(radial_six/9));
        editor.putString(grp_radial7, String.valueOf(radial_seven/9));
        editor.putString(grp_radial8, String.valueOf(radial_eight/9));
        editor.commit();

        Log.e("Total time taken ",totaltime+" milliseconds");
        Log.e("Accuracy",""+accuracyTouch);
        Log.e("Button Pressed",""+button_pressed);

        Log.e("Quadrant 1",""+quadrant1);
        Log.e("Quadrant 2",""+quadrant2);
        Log.e("Quadrant 3",""+quadrant3);
        Log.e("Quadrant 4",""+quadrant4);

        Log.e("Average Quadrant 1",""+quadrant1/18);
        Log.e("Average Quadrant 2",""+quadrant2/18);
        Log.e("Average Quadrant 3",""+quadrant3/18);
        Log.e("Average Quadrant 4",""+quadrant4/18);

        Log.e("Average Radial 1",""+radial_one/9);
        Log.e("Average Radial 2",""+radial_two/9);
        Log.e("Average Radial 3",""+radial_three/9);
        Log.e("Average Radial 4",""+radial_four/9);
        Log.e("Average Radial 5",""+radial_five/9);
        Log.e("Average Radial 6",""+radial_six/9);
        Log.e("Average Radial 7",""+radial_seven/9);
        Log.e("Average Radial 8",""+radial_eight/9);

        Log.e("Accuracy Total",""+totalAccuracy);

        Log.e("Total avg time taken ",total_avg_ms+" milliseconds");
        mydb.insertTest(local_patientid,local_loggedInTestId,local_loggedin,total_avg_ms,totaltime,dotsize,quadrant1/18,quadrant2/18,quadrant2/18,quadrant2/18,totalAccuracy,radial_one/9,radial_two/9,radial_three/9,radial_four/9,radial_five/9,radial_six/9,radial_seven/9,radial_eight/9);

        patient_name.setText("Patient: "+local_loggedin);
        Duration_in_seconds.setText("Duration: "+totaltime/1000+" Seconds");
        Testdate.setText("Test Date: "+local_patienttestdate);
        Stimuli_Size.setText(""+dotsize);
        Duration_seconds.setText("Compl TM: "+totaltime/1000+" Seconds");
        Average_React_TM.setText("React TM: "+total_avg_ms+" Seconds");
        Hits.setText("Hits: "+accuracyTouch);
        Missed.setText("Missed: "+(72-accuracyTouch));
        Percentage.setText("Perc: "+totalAccuracy+"%");
        Q1.setText(quadrant1/18+"ms");
        Q2.setText(quadrant2/18+"ms");
        Q3.setText(quadrant3/18+"ms");
        Q4.setText(quadrant4/18+"ms");
        Radial_1.setText("Radial 1 TM: "+radial_one/9+"ms");
        Radial_2.setText("Radial 2 TM: "+radial_two/9+"ms");
        Radial_3.setText("Radial 3 TM: "+radial_three/9+"ms");
        Radial_4.setText("Radial 4 TM: "+radial_four/9+"ms");
        Radial_5.setText("Radial 5 TM: "+radial_five/9+"ms");
        Radial_6.setText("Radial 6 TM: "+radial_six/9+"ms");
        Radial_7.setText("Radial 7 TM: "+radial_seven/9+"ms");
        Radial_8.setText("Radial 8 TM: "+radial_eight/9+"ms");
    }
    public static void hideKeyboard(Context context, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideKeyboard(this, findViewById(android.R.id.content).getWindowToken());
        return super.dispatchTouchEvent(ev);
    }
    private void createPdf1(String fname){

        String NeuroEye= Environment.getExternalStorageDirectory()+"/"+fname;
        File f=new File(NeuroEye);

        if(!f.exists())
            if(!f.mkdir()){
                Log.e("Folder can't be created",NeuroEye);
            }
            else
                Log.e("Folder can be created",NeuroEye);
        else
            Log.e("Already Exits",NeuroEye);


        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        int time = (int) (System.currentTimeMillis());
        Timestamp tsTemp = new Timestamp(time);
        String ts =  tsTemp.toString();
        // write the document content
//        targetPdf = "/sdcard/NeuroEye/demo.pdf";

        targetPdf = "/sdcard/NeuroEye1/"+local_loggedin+".pdf";
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Printing" , Toast.LENGTH_LONG).show();
            //btn_generate.setText("Check PDF");
            boolean_save=true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        //close the document
        document.close();
    }


    private void createPdf(String fname){


        String NeuroEye1= Environment.getExternalStorageDirectory()+"/"+fname;
        File f=new File(NeuroEye1);

        if(!f.exists())
            if(!f.mkdir()){
                Log.e("Folder can't be created",NeuroEye1);
            }
            else
                Log.e("Folder can be created",NeuroEye1);
        else
            Log.e("Already Exits",NeuroEye1);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        int time = (int) (System.currentTimeMillis());
        Timestamp tsTemp = new Timestamp(time);
        String ts =  tsTemp.toString();
        // write the document content
//        targetPdf = "/sdcard/NeuroEye/demo.pdf";

        targetPdf = "/sdcard/NeuroEye/"+local_loggedin+"_"+ts+"_Testing.pdf";
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF Saved Successfully" , Toast.LENGTH_LONG).show();
            //btn_generate.setText("Check PDF");
            boolean_save=true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        //close the document
        document.close();
    }
    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }
    public  Bitmap loadBitmapFromView(View v) {

        Display display = Testing.this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x-(size.x/3);
        int height = size.x -(size.x/8);


        Bitmap b = Bitmap.createBitmap(width , height , Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        // v.layout(size.x/8, size.x/8, v.getLayoutParams().width, v.getLayoutParams().height);
        RelativeLayout.LayoutParams layout_description = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );

        layout_description.leftMargin=30;
        v.setLayoutParams(layout_description);

        v.draw(c);
        return b;
    }
    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Testing.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(Testing.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Testing.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(Testing.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        } else {
            boolean_permission = true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boolean_permission = true;
            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}
