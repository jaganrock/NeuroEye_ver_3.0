package ragul.srushty.com.neuroeye;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

public class DifferentiationTaskActivity extends AppCompatActivity {
    Context context;
    int background_image;
    DBHelperPatientLastTest mypatientvital;
    DBHelperDifferentiation mydbdif;

    ImageButton ImageBt1 ;
    ImageButton ImageBt2 ;
    ImageButton ImageBt3 ;
    ImageButton ImageBt4 ;
    ImageButton ImageBt5 ;
    ImageButton ImageBt6 ;
    ImageButton ImageBt7 ;
    ImageButton ImageBt8 ;
    ImageButton ImageBt9 ;
    ImageButton ImageBt10;
    ImageButton ImageBt11;
    ImageButton ImageBt12;
    ImageButton ImageBt13;
    ImageButton ImageBt14;
    ImageButton ImageBt15;
    ImageButton ImageBt16;
    ImageButton ImageBt17;
    ImageButton ImageBt18;
    ImageButton ImageBt19;
    ImageButton ImageBt20;
    ImageButton ImageBt21;
    ImageButton ImageBt22;
    ImageButton ImageBt23;
    ImageButton ImageBt24;
    ImageButton ImageBt25;
    ImageButton ImageBt26;
    ImageButton ImageBt27;
    ImageButton ImageBt28;
    ImageButton ImageBt29;
    ImageButton ImageBt30;
    ImageButton ImageBt31;
    ImageButton ImageBt32;

    Button instruction_dialog;
    Button finish_dialog,print_button,export_pdf;

    RelativeLayout RLimage1 ;
    RelativeLayout RLimage2 ;
    RelativeLayout RLimage3 ;
    RelativeLayout RLimage4 ;
    RelativeLayout RLimage5 ;
    RelativeLayout RLimage6 ;
    RelativeLayout RLimage7 ;
    RelativeLayout RLimage8 ;
    RelativeLayout RLimage9 ;
    RelativeLayout RLimage10;
    RelativeLayout RLimage11;
    RelativeLayout RLimage12;
    RelativeLayout RLimage13;
    RelativeLayout RLimage14;
    RelativeLayout RLimage15;
    RelativeLayout RLimage16;
    RelativeLayout RLimage17;
    RelativeLayout RLimage18;
    RelativeLayout RLimage19;
    RelativeLayout RLimage20;
    RelativeLayout RLimage21;
    RelativeLayout RLimage22;
    RelativeLayout RLimage23;
    RelativeLayout RLimage24;
    RelativeLayout RLimage25;
    RelativeLayout RLimage26;
    RelativeLayout RLimage27;
    RelativeLayout RLimage28;
    RelativeLayout RLimage29;
    RelativeLayout RLimage30;
    RelativeLayout RLimage31;
    RelativeLayout RLimage32;

    RelativeLayout RLinstruction;
    RelativeLayout RL_finish;
    int stage_increment=1;
    long startTime=0, endTime=0,finaltime=0;
    int Wrong_object=0;
    TextView Total_Time,patient_name,test_taken,accuarcy;
    TextView Average_Time;
    TextView Wrong_Objects;
    ProgressDialog progressDialog;
    RelativeLayout ll_pdflayout;
    String targetPdf;
    File filePath;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;

    boolean boolean_save;

    String local_loggedin;
    String local_patientid;
    String local_patientage;
    String local_patienttestdate;
    String local_patientgender;
    String local_patientemail;
    String local_patientphonenumber;
    String local_patient_history_test_value="000";
    int local_loggedInTestId;

    ArrayList<RelativeLayout> list = new ArrayList<RelativeLayout>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskactivity);

        mypatientvital=new DBHelperPatientLastTest(this);
        mydbdif=new DBHelperDifferentiation(this);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        final String loggedIn = "logged_in";
        final String patientId = "logged_in_patientId";
        final String patientage = "logged_in_patientage";
        final String patienttestdate = "logged_in_patienttestdate";
        final String patientgender = "logged_in_patientgender";
        final String loggedInTestId = "logged_in_test_id";
        final String patientemail = "logged_in_patientemail";
        final String patientphonenumber = "logged_in_patientphonenumber";



        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DifferentiationTaskActivity.this);
        local_loggedin= prefs.getString(loggedIn,"");
        local_patientid=prefs.getString(patientId,"");
        local_patientage=prefs.getString(patientage, "");
        local_patienttestdate=prefs.getString(patienttestdate, "");
        local_patientgender=prefs.getString(patientgender, "");
        local_patientemail=prefs.getString(patientemail,"");
        local_patientphonenumber=prefs.getString(patientphonenumber, "");
        local_loggedInTestId=prefs.getInt(loggedInTestId, 0);

        Log.e("User Details",local_loggedin+" , "+local_patientid+" , "+local_patientage+" , "+local_patienttestdate+" , "+local_patientgender+" , "+local_patientemail+" , "+local_patientphonenumber+" , "+local_loggedInTestId);

        String local_patient_history_test_value2 = mypatientvital.getLatestPatientTest(local_patientid);
        String last_test_Total_time_taken = mypatientvital.getPatientTotalTime(local_patientid);
        String last_test_Dot_Size = mypatientvital.getPatientDotSize(local_patientid);
        String last_test_accuracy = mypatientvital.getPatientAccuracy(local_patientid);
        String last_test_q1 = mypatientvital.getPatientQuadrantOne(local_patientid);
        String last_test_q2 = mypatientvital.getPatientQuadrantTwo(local_patientid);
        String last_test_q3 = mypatientvital.getPatientQuadrantThree(local_patientid);
        String last_test_q4 = mypatientvital.getPatientQuadrantFour(local_patientid);

        ll_pdflayout = (RelativeLayout) findViewById(R.id.print_layout);
        fn_permission();
        Log.e("Average Time",""+local_patient_history_test_value2);
        Log.e("Total Time",""+last_test_Total_time_taken);
        Log.e("Dot Size",""+last_test_Dot_Size);
        Log.e("Accuracy",""+last_test_accuracy);
        Log.e("Quardent 1",""+last_test_q1);
        Log.e("Quardent 2",""+last_test_q2);
        Log.e("Quardent 3",""+last_test_q3);
        Log.e("Quardent 4",""+last_test_q4);


        patient_name   =(TextView) findViewById(R.id.patient_name);
        test_taken =(TextView) findViewById(R.id.date_taken);
        Total_Time   =(TextView) findViewById(R.id.result_tot_time);
        Average_Time =(TextView) findViewById(R.id.result_avg_time);
        Wrong_Objects=(TextView) findViewById(R.id.result_wrong_obj);
        accuarcy=(TextView) findViewById(R.id.accuarcy);

        instruction_dialog=(Button) findViewById(R.id.instruction_dialogue);
        print_button=(Button) findViewById(R.id.Print_Button_differentiation);
        export_pdf=(Button) findViewById(R.id.pdf_differentiation);
        finish_dialog=(Button) findViewById(R.id.finish_dialogue);
        RLimage1 =(RelativeLayout) findViewById(R.id.RLimg1);
        RLimage2 =(RelativeLayout) findViewById(R.id.RLimg2);
        RLimage3 =(RelativeLayout) findViewById(R.id.RLimg3);
        RLimage4 =(RelativeLayout) findViewById(R.id.RLimg4);
        RLimage5 =(RelativeLayout) findViewById(R.id.RLimg5);
        RLimage6 =(RelativeLayout) findViewById(R.id.RLimg6);
        RLimage7 =(RelativeLayout) findViewById(R.id.RLimg7);
        RLimage8 =(RelativeLayout) findViewById(R.id.RLimg8);
        RLimage9 =(RelativeLayout) findViewById(R.id.RLimg9);
        RLimage10=(RelativeLayout) findViewById(R.id.RLimg10);
        RLimage11=(RelativeLayout) findViewById(R.id.RLimg11);
        RLimage12=(RelativeLayout) findViewById(R.id.RLimg12);
        RLimage13=(RelativeLayout) findViewById(R.id.RLimg13);
        RLimage14=(RelativeLayout) findViewById(R.id.RLimg14);
        RLimage15=(RelativeLayout) findViewById(R.id.RLimg15);
        RLimage16=(RelativeLayout) findViewById(R.id.RLimg16);
        RLimage17=(RelativeLayout) findViewById(R.id.RLimg17);
        RLimage18=(RelativeLayout) findViewById(R.id.RLimg18);
        RLimage19=(RelativeLayout) findViewById(R.id.RLimg19);
        RLimage20=(RelativeLayout) findViewById(R.id.RLimg20);
        RLimage21=(RelativeLayout) findViewById(R.id.RLimg21);
        RLimage22=(RelativeLayout) findViewById(R.id.RLimg22);
        RLimage23=(RelativeLayout) findViewById(R.id.RLimg23);
        RLimage24=(RelativeLayout) findViewById(R.id.RLimg24);
        RLimage25=(RelativeLayout) findViewById(R.id.RLimg25);
        RLimage26=(RelativeLayout) findViewById(R.id.RLimg26);
        RLimage27=(RelativeLayout) findViewById(R.id.RLimg27);
        RLimage28=(RelativeLayout) findViewById(R.id.RLimg28);
        RLimage29=(RelativeLayout) findViewById(R.id.RLimg29);
        RLimage30=(RelativeLayout) findViewById(R.id.RLimg30);
        RLimage31=(RelativeLayout) findViewById(R.id.RLimg31);
        RLimage32=(RelativeLayout) findViewById(R.id.RLimg32);
        RLinstruction=(RelativeLayout) findViewById(R.id.instruction_RL);
        RL_finish=(RelativeLayout) findViewById(R.id.finish_RL2);

        String Background_Colour="#000000";

        RLimage1.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage2.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage3.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage4.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage5.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage6.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage7.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage8.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage9.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage10.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage11.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage12.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage13.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage14.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage15.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage16.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage17.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage18.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage19.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage20.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage21.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage22.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage23.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage24.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage25.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage26.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage27.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage28.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage29.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage30.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage31.setBackgroundColor(Color.parseColor(Background_Colour));
        RLimage32.setBackgroundColor(Color.parseColor(Background_Colour));

        ImageBt1 = (ImageButton) findViewById(R.id.im1);
        ImageBt2 = (ImageButton) findViewById(R.id.im2);
        ImageBt3 = (ImageButton) findViewById(R.id.im3);
        ImageBt4 = (ImageButton) findViewById(R.id.im4);
        ImageBt5 = (ImageButton) findViewById(R.id.im5);
        ImageBt6 = (ImageButton) findViewById(R.id.im6);
        ImageBt7 = (ImageButton) findViewById(R.id.im7);
        ImageBt8 = (ImageButton) findViewById(R.id.im8);
        ImageBt9 = (ImageButton) findViewById(R.id.im9);
        ImageBt10 = (ImageButton) findViewById(R.id.im10);
        ImageBt11 = (ImageButton) findViewById(R.id.im11);
        ImageBt12 = (ImageButton) findViewById(R.id.im12);
        ImageBt13 = (ImageButton) findViewById(R.id.im13);
        ImageBt14 = (ImageButton) findViewById(R.id.im14);
        ImageBt15 = (ImageButton) findViewById(R.id.im15);
        ImageBt16 = (ImageButton) findViewById(R.id.im16);
        ImageBt17 = (ImageButton) findViewById(R.id.im17);
        ImageBt18 = (ImageButton) findViewById(R.id.im18);
        ImageBt19 = (ImageButton) findViewById(R.id.im19);
        ImageBt20 = (ImageButton) findViewById(R.id.im20);
        ImageBt21 = (ImageButton) findViewById(R.id.im21);
        ImageBt22 = (ImageButton) findViewById(R.id.im22);
        ImageBt23 = (ImageButton) findViewById(R.id.im23);
        ImageBt24 = (ImageButton) findViewById(R.id.im24);
        ImageBt25 = (ImageButton) findViewById(R.id.im25);
        ImageBt26 = (ImageButton) findViewById(R.id.im26);
        ImageBt27 = (ImageButton) findViewById(R.id.im27);
        ImageBt28 = (ImageButton) findViewById(R.id.im28);
        ImageBt29 = (ImageButton) findViewById(R.id.im29);
        ImageBt30 = (ImageButton) findViewById(R.id.im30);
        ImageBt31 = (ImageButton) findViewById(R.id.im31);
        ImageBt32 = (ImageButton) findViewById(R.id.im32);


        print_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DifferentiationTaskActivity.this);
                progressDialog.setMessage("Please wait");
                bitmap = loadBitmapFromView(ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight());
                createPdf1();
                filePath = new File(targetPdf);
                Intent intent = getPackageManager().getLaunchIntentForPackage("jp.co.canon.bsd.ad.pixmaprint");
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setDataAndType(Uri.fromFile(filePath), "application/pdf");
                startActivity(intent);
            }
        });
        export_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boolean_permission) {
                    progressDialog = new ProgressDialog(DifferentiationTaskActivity.this);
                    progressDialog.setMessage("Please wait");
                    bitmap = loadBitmapFromView(ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight());
                    createPdf();
                } else {
                }
            }
        });

        instruction_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RLinstruction.setVisibility(View.GONE);
                stages(stage_increment);
            }
        });
        finish_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(DifferentiationTaskActivity.this, MainActivityL.class);
                startActivity(i1);
                finish();
            }
        });
        list.add(RLimage1);
        list.add(RLimage2);
        list.add(RLimage3);
        list.add(RLimage4);
        list.add(RLimage5);
        list.add(RLimage6);
        list.add(RLimage7);
        list.add(RLimage8);
        list.add(RLimage9);
        list.add(RLimage10);
        list.add(RLimage11);
        list.add(RLimage12);
        list.add(RLimage13);
        list.add(RLimage14);
        list.add(RLimage15);
        list.add(RLimage16);
        list.add(RLimage17);
        list.add(RLimage18);
        list.add(RLimage19);
        list.add(RLimage20);
        list.add(RLimage21);
        list.add(RLimage22);
        list.add(RLimage23);
        list.add(RLimage24);
        list.add(RLimage25);
        list.add(RLimage26);
        list.add(RLimage27);
        list.add(RLimage28);
        list.add(RLimage29);
        list.add(RLimage30);
        list.add(RLimage31);
        list.add(RLimage32);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startTime=System.currentTimeMillis();
            }
        }, 10);
        Collections.shuffle(list);

        //stages(stage_increment);

        ImageBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage1.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage2.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage3.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage4.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage5.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage6.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage7.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage8.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage9.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage10.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage11.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage12.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage13.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage14.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage15.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage16.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage17.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage18.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage19.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage20.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage21.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage22.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage23.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage24.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage25.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage26.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage27.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage28.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage29.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage30.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage31.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);

                    }
                }
            }
        });
        ImageBt32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable viewColor = (ColorDrawable) RLimage32.getBackground();
                int colorId = viewColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId == -16711928 || colorId == -65536 ){
                    if(colorId == -65536){
                        Wrong_object++;
                    }
                    stage_increment++;
                    stages(stage_increment);
                    if(stage_increment == 16){
                        stage_increment=0;
                        endTime=System.currentTimeMillis();
                        finaltime=endTime-startTime;
                        Log.e("Total Time Diff","in MS"+finaltime);
                        Log.e("Average Time ","in MS"+finaltime/15);
                        Log.e("Wrong Oblect Pressed ",""+Wrong_object);
                        RL_finish.setVisibility(View.VISIBLE);
                        final_result(finaltime,finaltime/15,Wrong_object);
                    }

                }
            }
        });

    }
    public void backclick(View view) {
        Intent i1 = new Intent(this, MainActivityL.class);
        startActivity(i1);
        finish();
    }

    public void twoTargetButtons(){
        list.get(0).setBackgroundColor(Color.parseColor("#00FF08"));
        list.get(1).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(2).setBackgroundColor(Color.parseColor("#000000"));
        list.get(3).setBackgroundColor(Color.parseColor("#000000"));
        list.get(4).setBackgroundColor(Color.parseColor("#000000"));
        list.get(5).setBackgroundColor(Color.parseColor("#000000"));
        list.get(6).setBackgroundColor(Color.parseColor("#000000"));
        list.get(7).setBackgroundColor(Color.parseColor("#000000"));
        list.get(8).setBackgroundColor(Color.parseColor("#000000"));
        list.get(9).setBackgroundColor(Color.parseColor("#000000"));
        list.get(10).setBackgroundColor(Color.parseColor("#000000"));
        list.get(11).setBackgroundColor(Color.parseColor("#000000"));
        list.get(12).setBackgroundColor(Color.parseColor("#000000"));
        list.get(13).setBackgroundColor(Color.parseColor("#000000"));
        list.get(14).setBackgroundColor(Color.parseColor("#000000"));
        list.get(15).setBackgroundColor(Color.parseColor("#000000"));
        list.get(16).setBackgroundColor(Color.parseColor("#000000"));
        list.get(17).setBackgroundColor(Color.parseColor("#000000"));
        list.get(18).setBackgroundColor(Color.parseColor("#000000"));
        list.get(19).setBackgroundColor(Color.parseColor("#000000"));
        list.get(20).setBackgroundColor(Color.parseColor("#000000"));
        list.get(21).setBackgroundColor(Color.parseColor("#000000"));
        list.get(22).setBackgroundColor(Color.parseColor("#000000"));
        list.get(23).setBackgroundColor(Color.parseColor("#000000"));
        list.get(24).setBackgroundColor(Color.parseColor("#000000"));
        list.get(25).setBackgroundColor(Color.parseColor("#000000"));
        list.get(26).setBackgroundColor(Color.parseColor("#000000"));
        list.get(27).setBackgroundColor(Color.parseColor("#000000"));
        list.get(28).setBackgroundColor(Color.parseColor("#000000"));
        list.get(29).setBackgroundColor(Color.parseColor("#000000"));
        list.get(30).setBackgroundColor(Color.parseColor("#000000"));
        list.get(31).setBackgroundColor(Color.parseColor("#000000"));
    }

    public void FourTargetButtons(){
        list.get(0).setBackgroundColor(Color.parseColor("#00FF08"));
        list.get(1).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(2).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(3).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(4).setBackgroundColor(Color.parseColor("#000000"));
        list.get(5).setBackgroundColor(Color.parseColor("#000000"));
        list.get(6).setBackgroundColor(Color.parseColor("#000000"));
        list.get(7).setBackgroundColor(Color.parseColor("#000000"));
        list.get(8).setBackgroundColor(Color.parseColor("#000000"));
        list.get(9).setBackgroundColor(Color.parseColor("#000000"));
        list.get(10).setBackgroundColor(Color.parseColor("#000000"));
        list.get(11).setBackgroundColor(Color.parseColor("#000000"));
        list.get(12).setBackgroundColor(Color.parseColor("#000000"));
        list.get(13).setBackgroundColor(Color.parseColor("#000000"));
        list.get(14).setBackgroundColor(Color.parseColor("#000000"));
        list.get(15).setBackgroundColor(Color.parseColor("#000000"));
        list.get(16).setBackgroundColor(Color.parseColor("#000000"));
        list.get(17).setBackgroundColor(Color.parseColor("#000000"));
        list.get(18).setBackgroundColor(Color.parseColor("#000000"));
        list.get(19).setBackgroundColor(Color.parseColor("#000000"));
        list.get(20).setBackgroundColor(Color.parseColor("#000000"));
        list.get(21).setBackgroundColor(Color.parseColor("#000000"));
        list.get(22).setBackgroundColor(Color.parseColor("#000000"));
        list.get(23).setBackgroundColor(Color.parseColor("#000000"));
        list.get(24).setBackgroundColor(Color.parseColor("#000000"));
        list.get(25).setBackgroundColor(Color.parseColor("#000000"));
        list.get(26).setBackgroundColor(Color.parseColor("#000000"));
        list.get(27).setBackgroundColor(Color.parseColor("#000000"));
        list.get(28).setBackgroundColor(Color.parseColor("#000000"));
        list.get(29).setBackgroundColor(Color.parseColor("#000000"));
        list.get(30).setBackgroundColor(Color.parseColor("#000000"));
        list.get(31).setBackgroundColor(Color.parseColor("#000000"));
    }

    public void EightTargetButtons(){
        list.get(0).setBackgroundColor(Color.parseColor("#00FF08"));
        list.get(1).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(2).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(3).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(4).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(5).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(6).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(7).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(8).setBackgroundColor(Color.parseColor("#000000"));
        list.get(9).setBackgroundColor(Color.parseColor("#000000"));
        list.get(10).setBackgroundColor(Color.parseColor("#000000"));
        list.get(11).setBackgroundColor(Color.parseColor("#000000"));
        list.get(12).setBackgroundColor(Color.parseColor("#000000"));
        list.get(13).setBackgroundColor(Color.parseColor("#000000"));
        list.get(14).setBackgroundColor(Color.parseColor("#000000"));
        list.get(15).setBackgroundColor(Color.parseColor("#000000"));
        list.get(16).setBackgroundColor(Color.parseColor("#000000"));
        list.get(17).setBackgroundColor(Color.parseColor("#000000"));
        list.get(18).setBackgroundColor(Color.parseColor("#000000"));
        list.get(19).setBackgroundColor(Color.parseColor("#000000"));
        list.get(20).setBackgroundColor(Color.parseColor("#000000"));
        list.get(21).setBackgroundColor(Color.parseColor("#000000"));
        list.get(22).setBackgroundColor(Color.parseColor("#000000"));
        list.get(23).setBackgroundColor(Color.parseColor("#000000"));
        list.get(24).setBackgroundColor(Color.parseColor("#000000"));
        list.get(25).setBackgroundColor(Color.parseColor("#000000"));
        list.get(26).setBackgroundColor(Color.parseColor("#000000"));
        list.get(27).setBackgroundColor(Color.parseColor("#000000"));
        list.get(28).setBackgroundColor(Color.parseColor("#000000"));
        list.get(29).setBackgroundColor(Color.parseColor("#000000"));
        list.get(30).setBackgroundColor(Color.parseColor("#000000"));
        list.get(31).setBackgroundColor(Color.parseColor("#000000"));
    }

    public void SixteenTargetButtons(){
        list.get(0).setBackgroundColor(Color.parseColor("#00FF08"));
        list.get(1).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(2).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(3).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(4).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(5).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(6).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(7).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(8).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(9).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(10).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(11).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(12).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(13).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(14).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(15).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(16).setBackgroundColor(Color.parseColor("#000000"));
        list.get(17).setBackgroundColor(Color.parseColor("#000000"));
        list.get(18).setBackgroundColor(Color.parseColor("#000000"));
        list.get(19).setBackgroundColor(Color.parseColor("#000000"));
        list.get(20).setBackgroundColor(Color.parseColor("#000000"));
        list.get(21).setBackgroundColor(Color.parseColor("#000000"));
        list.get(22).setBackgroundColor(Color.parseColor("#000000"));
        list.get(23).setBackgroundColor(Color.parseColor("#000000"));
        list.get(24).setBackgroundColor(Color.parseColor("#000000"));
        list.get(25).setBackgroundColor(Color.parseColor("#000000"));
        list.get(26).setBackgroundColor(Color.parseColor("#000000"));
        list.get(27).setBackgroundColor(Color.parseColor("#000000"));
        list.get(28).setBackgroundColor(Color.parseColor("#000000"));
        list.get(29).setBackgroundColor(Color.parseColor("#000000"));
        list.get(30).setBackgroundColor(Color.parseColor("#000000"));
        list.get(31).setBackgroundColor(Color.parseColor("#000000"));
    }

    public void ThirthyTwoTargetButtons(){
        list.get(0).setBackgroundColor(Color.parseColor("#00FF08"));
        list.get(1).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(2).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(3).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(4).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(5).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(6).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(7).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(8).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(9).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(10).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(11).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(12).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(13).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(14).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(15).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(16).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(17).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(18).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(19).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(20).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(21).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(22).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(23).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(24).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(25).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(26).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(27).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(28).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(29).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(30).setBackgroundColor(Color.parseColor("#FF0000"));
        list.get(31).setBackgroundColor(Color.parseColor("#FF0000"));
    }

    public void stages(int stagevalue){
        if(stagevalue == 1 ||stagevalue == 2 ||stagevalue == 3 ){//two
            background_image=R.drawable.black_circle;
            twoTargetButtons();
            Collections.shuffle(list);
        }else if(stagevalue == 4 ||stagevalue == 5 ||stagevalue == 6){//four
            background_image=R.drawable.hexa;
            FourTargetButtons();
            Collections.shuffle(list);
        }else if(stagevalue == 7 ||stagevalue == 8 ||stagevalue == 9){//eight
            background_image=R.drawable.square;
            EightTargetButtons();
            Collections.shuffle(list);
        }else if(stagevalue == 10 ||stagevalue == 11 ||stagevalue == 12){//sixteen
            background_image=R.drawable.triangle;
            SixteenTargetButtons();
            Collections.shuffle(list);
        }else if(stagevalue == 13 ||stagevalue == 14 ||stagevalue == 15) {//tirthy two
            background_image=R.drawable.rectangle;
            ThirthyTwoTargetButtons();
            Collections.shuffle(list);
        }

        ImageBt1.setBackgroundResource(background_image);
        ImageBt2.setBackgroundResource(background_image);
        ImageBt3.setBackgroundResource(background_image);
        ImageBt4.setBackgroundResource(background_image);
        ImageBt5.setBackgroundResource(background_image);
        ImageBt6.setBackgroundResource(background_image);
        ImageBt7.setBackgroundResource(background_image);
        ImageBt8.setBackgroundResource(background_image);
        ImageBt9.setBackgroundResource(background_image);
        ImageBt10.setBackgroundResource(background_image);
        ImageBt11.setBackgroundResource(background_image);
        ImageBt12.setBackgroundResource(background_image);
        ImageBt13.setBackgroundResource(background_image);
        ImageBt14.setBackgroundResource(background_image);
        ImageBt15.setBackgroundResource(background_image);
        ImageBt16.setBackgroundResource(background_image);
        ImageBt17.setBackgroundResource(background_image);
        ImageBt18.setBackgroundResource(background_image);
        ImageBt19.setBackgroundResource(background_image);
        ImageBt20.setBackgroundResource(background_image);
        ImageBt21.setBackgroundResource(background_image);
        ImageBt22.setBackgroundResource(background_image);
        ImageBt23.setBackgroundResource(background_image);
        ImageBt24.setBackgroundResource(background_image);
        ImageBt25.setBackgroundResource(background_image);
        ImageBt26.setBackgroundResource(background_image);
        ImageBt27.setBackgroundResource(background_image);
        ImageBt28.setBackgroundResource(background_image);
        ImageBt29.setBackgroundResource(background_image);
        ImageBt30.setBackgroundResource(background_image);
        ImageBt31.setBackgroundResource(background_image);
        ImageBt32.setBackgroundResource(background_image);
    }

    public void final_result(long totaltime,long avgtime,int wrongobjects){

        Total_Time.setText("Total TM: "+totaltime+"ms");
        Average_Time.setText("Average TM: "+avgtime+"ms");
        Wrong_Objects.setText("Wrong Objects Touched : "+wrongobjects);
        patient_name.setText("Patient Name: "+local_loggedin);
        test_taken.setText("Test Date: "+local_patienttestdate);

        Log.e("Table_Created diff "," 1");

        mydbdif.insertDifferentiation(local_patientid,local_loggedInTestId,local_loggedin,totaltime,avgtime,wrongobjects);

        Log.e("Table_Created diff "," 2");


        if(wrongobjects!=0){
            accuarcy.setText("Accuracy: "+15/wrongobjects+"%");
        }else {
            accuarcy.setText("Accuracy: 100%");
        }

    }
    Bitmap bitmap;
    private void createPdf1(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

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

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }
    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;
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

        targetPdf = "/sdcard/NeuroEye/"+local_loggedin+"_"+ts+"_Differentiation.pdf";
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

    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(DifferentiationTaskActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(DifferentiationTaskActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale(DifferentiationTaskActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(DifferentiationTaskActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
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
