package ragul.srushty.com.neuroeye;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static int life=0;

    Button circle1,circle2,circle3,circle4,circle5,circle6,circle7,circle8,circle9,circle10,circle11,circle12,circle13,circle14,circle15,circle16;
    Button circlea,circleb,circlec,circled,circlee,circlef,circleg,circleh;
    Button circlecenter;
    Button lifebutton1;
    Button lifebutton2;
    Button lifebutton3;
    boolean centercircle_pressed=true;
    String level="Level 1";
    String dotsize="2mm";

    boolean allquardents=true;

    long startTime=0, endTime=0,finaltime=0;
    Boolean firsttime=false;
    TextView timertext;
    ImageView background_image;

    int min=1,max=17;
    int lastvalue=0;

    int checkbox_clicked=0;

    CheckBox checkbox1,checkbox2,checkbox3,checkbox4,checkbox5,checkbox6,checkbox7,checkbox8;
    Boolean  checkbox1_flag=true,checkbox2_flag=true,checkbox3_flag=true,checkbox4_flag=true,checkbox5_flag=true,checkbox6_flag=true,checkbox7_flag=true,checkbox8_flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        life=0;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.e("ApplicationTagName", "Display width in px is " + metrics.widthPixels);
        Log.e("ApplicationTagName", "Display width in px is " + metrics.heightPixels);

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
        circlecenter= (Button) findViewById(R.id.CircleCenter);

        timertext= (TextView) findViewById(R.id.timertext1);

        lifebutton1= (Button) findViewById(R.id.life1);
        lifebutton2= (Button) findViewById(R.id.life2);
        lifebutton3= (Button) findViewById(R.id.life3);

        checkbox1 = (CheckBox) findViewById(R.id.radial1);
        checkbox2 = (CheckBox) findViewById(R.id.radial2);
        checkbox3 = (CheckBox) findViewById(R.id.radial3);
        checkbox4 = (CheckBox) findViewById(R.id.radial4);
        checkbox5 = (CheckBox) findViewById(R.id.radial5);
        checkbox6 = (CheckBox) findViewById(R.id.radial6);
        checkbox7 = (CheckBox) findViewById(R.id.radial7);
        checkbox8 = (CheckBox) findViewById(R.id.radial8);

        checkbox1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkbox1_flag=!checkbox1_flag;
                Log.e("Radial","1 "+checkbox1_flag);
                if(checkbox1_flag){
                    checkbox_clicked--;
                    circle4.setBackgroundColor(Color.parseColor("#FF0000"));
                    circlea.setBackgroundColor(Color.parseColor("#FF0000"));
                    circle1.setBackgroundColor(Color.parseColor("#FF0000"));
                }else {
                    checkbox_clicked++;
                }
                if(checkbox_clicked>7){
                    checkboxselectall();
                }
            }
        });

        checkbox2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkbox2_flag=!checkbox2_flag;
                Log.e("Radial","2 "+checkbox2_flag);
                if(checkbox2_flag){
                    checkbox_clicked--;
                    circle6.setBackgroundColor(Color.parseColor("#FFE000"));
                    circleh.setBackgroundColor(Color.parseColor("#FFE000"));
                    circle3.setBackgroundColor(Color.parseColor("#FFE000"));
                }else {
                    checkbox_clicked++;
                }
                if(checkbox_clicked>7){
                    checkboxselectall();
                }
            }
        });
        checkbox3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkbox3_flag=!checkbox3_flag;
                Log.e("Radial","3 "+checkbox3_flag);
                if(checkbox3_flag){
                    checkbox_clicked--;
                    circle10.setBackgroundColor(Color.parseColor("#46FF00"));
                    circleg.setBackgroundColor(Color.parseColor("#46FF00"));
                    circle11.setBackgroundColor(Color.parseColor("#46FF00"));
                }else {
                    checkbox_clicked++;
                }
                if(checkbox_clicked>7){
                    checkboxselectall();
                }
            }
        });
        checkbox4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkbox4_flag=!checkbox4_flag;
                Log.e("Radial","4 "+checkbox4_flag);
                if(checkbox4_flag){
                    checkbox_clicked--;
                    circle14.setBackgroundColor(Color.parseColor("#00FFC9"));
                    circlef.setBackgroundColor(Color.parseColor("#00FFC9"));
                    circle15.setBackgroundColor(Color.parseColor("#00FFC9"));
                }else {
                    checkbox_clicked++;
                }
                if(checkbox_clicked>7){
                    checkboxselectall();
                }
            }
        });
        checkbox5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkbox5_flag=!checkbox5_flag;
                Log.e("Radial","5 "+checkbox5_flag);
                if(checkbox5_flag){
                    checkbox_clicked--;
                    circle13.setBackgroundColor(Color.parseColor("#00BDFF"));
                    circlee.setBackgroundColor(Color.parseColor("#00BDFF"));
                    circle16.setBackgroundColor(Color.parseColor("#00BDFF"));
                }else {
                    checkbox_clicked++;
                }
                if(checkbox_clicked>7){
                    checkboxselectall();
                }
            }
        });
        checkbox6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkbox6_flag=!checkbox6_flag;
                Log.e("Radial","6 "+checkbox6_flag);
                if(checkbox6_flag){
                    checkbox_clicked--;
                    circle12.setBackgroundColor(Color.parseColor("#9B00FF"));
                    circled.setBackgroundColor(Color.parseColor("#9B00FF"));
                    circle7.setBackgroundColor(Color.parseColor("#9B00FF"));
                }else {
                    checkbox_clicked++;
                }
                if(checkbox_clicked>7){
                    checkboxselectall();
                }
            }
        });
        checkbox7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkbox7_flag=!checkbox7_flag;
                Log.e("Radial","7 "+checkbox7_flag);
                if(checkbox7_flag){
                    checkbox_clicked--;
                    circle9.setBackgroundColor(Color.parseColor("#FF00E0"));
                    circlec.setBackgroundColor(Color.parseColor("#FF00E0"));
                    circle8.setBackgroundColor(Color.parseColor("#FF00E0"));
                }else {
                    checkbox_clicked++;
                }
                if(checkbox_clicked>7){
                    checkboxselectall();
                }
            }
        });
        checkbox8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkbox8_flag=!checkbox8_flag;
                Log.e("Radial","8 "+checkbox8_flag);
                if(checkbox8_flag){
                    checkbox_clicked--;
                    circle5.setBackgroundColor(Color.parseColor("#FF8300"));
                    circleb.setBackgroundColor(Color.parseColor("#FF8300"));
                    circle2.setBackgroundColor(Color.parseColor("#FF8300"));
                }else {
                    checkbox_clicked++;
                }
                if(checkbox_clicked>7){
                    checkboxselectall();
                }
            }
        });


        circlecenter.setBackgroundColor(Color.GREEN);
        circle1.setBackgroundColor(Color.parseColor("#FF0000"));
        circle2.setBackgroundColor(Color.parseColor("#FF8300"));
        circle3.setBackgroundColor(Color.parseColor("#FFE000"));
        circle4.setBackgroundColor(Color.parseColor("#FF0000"));
        circle5.setBackgroundColor(Color.parseColor("#FF8300"));
        circle6.setBackgroundColor(Color.parseColor("#FFE000"));
        circle7.setBackgroundColor(Color.parseColor("#9B00FF"));
        circle8.setBackgroundColor(Color.parseColor("#FF00E0"));
        circle9.setBackgroundColor(Color.parseColor("#FF00E0"));
        circle10.setBackgroundColor(Color.parseColor("#FF0078"));
        circle11.setBackgroundColor(Color.parseColor("#FF0078"));
        circle12.setBackgroundColor(Color.parseColor("#9B00FF"));
        circle13.setBackgroundColor(Color.parseColor("#00BDFF"));
        circle14.setBackgroundColor(Color.parseColor("#00FFC9"));
        circle15.setBackgroundColor(Color.parseColor("#00FFC9"));
        circle16.setBackgroundColor(Color.parseColor("#00BDFF"));
        circlea.setBackgroundColor(Color.parseColor("#FF0000"));
        circleb.setBackgroundColor(Color.parseColor("#FF8300"));
        circlec.setBackgroundColor(Color.parseColor("#FF00E0"));
        circled.setBackgroundColor(Color.parseColor("#9B00FF"));
        circlee.setBackgroundColor(Color.parseColor("#00BDFF"));
        circlef.setBackgroundColor(Color.parseColor("#00FFC9"));
        circleg.setBackgroundColor(Color.parseColor("#FF0078"));
        circleh.setBackgroundColor(Color.parseColor("#FFE000"));

        final Random r = new Random();

        background_image=(ImageView) findViewById(R.id.backgnd_image);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.e("item", (String) parent.getItemAtPosition(position));
                String level_selected = (String) parent.getItemAtPosition(position);
                if(level_selected.contains("Level 1")){
                    level="Level 1";
                    if(dotsize.matches("2mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommcirclewithline);
                        min=1;
                        max=9;
                    }else if(dotsize.matches("4mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmcirclewithline);
                        min=1;
                        max=9;
                    }else {
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmcirclewithlines);
                        min=1;
                        max=9;
                    }
                    randomnumber();
                }else if(level_selected.contains("Level 2")){
                    if(dotsize.matches("2mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommcirclewithline);
                    }else if(dotsize.matches("4mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmcirclewithline);
                    }else {
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmcirclewithlines);
                    }
                    min=17;
                    max=25;
                    randomnumber();
                }else if(level_selected.contains("Level 3")){
                    level="Level 3";
                    if(dotsize.matches("2mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommcirclewithline);
                    }else if(dotsize.matches("4mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmcirclewithline);
                    }else {
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmcirclewithlines);
                    }
                    min=9;
                    max=17;
                    randomnumber();
                }else if(level_selected.contains("Level 4")){
                    level="Level 4";
                    if(dotsize.matches("2mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommcircle);
                    }else if(dotsize.matches("4mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmcircle);
                    }else {
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmcircle);
                    }
                    min=1;
                    max=25;
                    randomnumber();
                }else if(level_selected.contains("Level 5")){
                    level="Level 5";
                    if(dotsize.matches("2mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommdots);
                    }else if(dotsize.matches("4mm")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmdots);
                    }else {
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmdots);
                    }
                    min=1;
                    max=25;
                    randomnumber();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

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
                    if(level.matches("Level 1")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommcirclewithline);
                    }else if(level.matches("Level 2")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommcirclewithline);
                    }else if(level.matches("Level 3")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommcirclewithline);
                    }else if(level.matches("Level 4")){
                        background_image.setBackgroundResource(R.drawable.rsz_twommcircle);
                    }else{
                        background_image.setBackgroundResource(R.drawable.rsz_twommdots);
                    }
                }else if(level_selected.contains("4mm")){
                    dotsize="4mm";
                    if(level.matches("Level 1")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmcirclewithline);
                    }else if(level.matches("Level 2")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmcirclewithline);
                    }else if(level.matches("Level 3")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmcirclewithline);
                    }else if(level.matches("Level 4")){
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmcircle);
                    }else{
                        background_image.setBackgroundResource(R.drawable.rsz_fourmmdots);
                    }
                }else{
                    dotsize="8mm";
                    if(level.matches("Level 1")){
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmcirclewithlines);
                    }else if(level.matches("Level 2")){
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmcirclewithlines);
                    }else if(level.matches("Level 3")){
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmcirclewithlines);
                    }else if(level.matches("Level 4")){
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmcircle);
                    }else{
                        background_image.setBackgroundResource(R.drawable.rsz_eightmmdots);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        findViewById(R.id.CircleCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(centercircle_pressed){
                    centercircle_pressed=false;
                    ColorDrawable buttonColor = (ColorDrawable) circle1.getBackground();
                    int colorId = buttonColor.getColor();
                    if(firsttime){
                        //stop Time
                        endTime=System.currentTimeMillis();
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
                    int i1 = r.nextInt(max - min) + min;
                    Log.e("Random1 Number R","="+i1);
                    select_quardent(i1);}
            }
        });
        findViewById(R.id.Circle1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Radial","1");

                ColorDrawable buttonColor = (ColorDrawable) circle1.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65536){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    if(life>2){
                        complete_dialog();
                    }
                    Log.e("Life exeedes","="+life);
                }else{
                    if(firsttime){
                        //stop Time
                        endTime=System.currentTimeMillis();
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
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","1 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","2");
                ColorDrawable buttonColor = (ColorDrawable) circle2.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-32000){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","2 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Radial","3");
                ColorDrawable buttonColor = (ColorDrawable) circle3.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-8192){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","3 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","4");

                ColorDrawable buttonColor = (ColorDrawable) circle4.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65536){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number ","8 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","5");
                ColorDrawable buttonColor = (ColorDrawable) circle5.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);

                if(colorId==-32000){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","7 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Radial","6");
                ColorDrawable buttonColor = (ColorDrawable) circle6.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-8192){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){

                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Game Over");
                        alertDialog.setMessage("You have lost all your 3 life by touching RED dots \n press OK to restart the game");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(MainActivity.this,MainActivityL.class);
                                        startActivity(i);
                                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                                        finish();
                                    }
                                });
                        alertDialog.show();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","16 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","7");
                ColorDrawable buttonColor = (ColorDrawable) circle7.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-6618881){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{

                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time  :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","15 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","8");
                ColorDrawable buttonColor = (ColorDrawable) circle8.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65312){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time  :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","11 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","9");
                ColorDrawable buttonColor = (ColorDrawable) circle9.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65312){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","4 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","10");

                ColorDrawable buttonColor = (ColorDrawable) circle10.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65416){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","5 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Radial","11");
                ColorDrawable buttonColor = (ColorDrawable) circle11.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65416){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","6 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","12");
                ColorDrawable buttonColor = (ColorDrawable) circle12.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-6618881){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{

                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","9 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","13");
                ColorDrawable buttonColor = (ColorDrawable) circle13.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-16728577){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{

                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","12 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","14");

                ColorDrawable buttonColor = (ColorDrawable) circle14.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);

                if(colorId==-16711735){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","14 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","15");
                ColorDrawable buttonColor = (ColorDrawable) circle15.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-16711735){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{

                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","13 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circle16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","16");
                ColorDrawable buttonColor = (ColorDrawable) circle16.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-16728577){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","10 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });

        //**************************************************
        findViewById(R.id.Circlea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","17");
                ColorDrawable buttonColor = (ColorDrawable) circlea.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65536){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    if(life>2){
                        complete_dialog();
                    }
                    Log.e("Life exeedes","="+life);
                }else{
                    if(firsttime){
                        //stop Time
                        endTime=System.currentTimeMillis();
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
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","17 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circleb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","18");
                ColorDrawable buttonColor = (ColorDrawable) circleb.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-32000){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","18 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circlec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","19");

                ColorDrawable buttonColor = (ColorDrawable) circlec.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65312){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","19 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circled).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Radial","20");
                ColorDrawable buttonColor = (ColorDrawable) circled.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-6618881){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","20 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circlee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Radial","21");
                ColorDrawable buttonColor = (ColorDrawable) circlee.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);

                if(colorId==-16728577){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","21 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circlef).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","22");

                ColorDrawable buttonColor = (ColorDrawable) circlef.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-16711735){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","22 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circleg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","23");
                ColorDrawable buttonColor = (ColorDrawable) circleg.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-65416){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{

                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time  :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);

                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","23 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });
        findViewById(R.id.Circleh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Radial","24");
                ColorDrawable buttonColor = (ColorDrawable) circleh.getBackground();
                int colorId = buttonColor.getColor();
                Log.e("Current Color Number","="+colorId);
                if(colorId==-8192){
                    life++;
                    if(life==1){
                        lifebutton1.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==2){
                        lifebutton2.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    else if(life==3){
                        lifebutton3.setBackgroundResource(R.drawable.redroundbutton);
                    }
                    Log.e("Life exeedes","="+life);
                    if(life>2){
                        complete_dialog();
                    }
                }else{
                    //stop Time
                    endTime=System.currentTimeMillis();
                    finaltime=endTime-startTime;
                    Log.e("Time Diff","in MS"+finaltime);
                    timertext.setText("Reaction Time  :"+finaltime+" ms");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startTime=System.currentTimeMillis();
                        }
                    }, 10);
                    ResetColor();
                    int i1=0;
                    while (true){
                        i1 = r.nextInt(max - min) + min;
                        if(i1==lastvalue){
                            Log.e("Random1 Number","Same Value");
                        }else {
                            Log.e("Random1 Number","24 ="+i1);
                            lastvalue=i1;
                            break;
                        }
                    }
                    select_quardent(i1);
                }
            }
        });

    }

    public void ResetColor(){

        circle4.setBackgroundColor(Color.parseColor("#FF0000"));
        circlea.setBackgroundColor(Color.parseColor("#FF0000"));
        circle1.setBackgroundColor(Color.parseColor("#FF0000"));

        circle6.setBackgroundColor(Color.parseColor("#FFE000"));
        circleh.setBackgroundColor(Color.parseColor("#FFE000"));
        circle3.setBackgroundColor(Color.parseColor("#FFE000"));

        circle10.setBackgroundColor(Color.parseColor("#FF0078"));
        circleg.setBackgroundColor(Color.parseColor("#FF0078"));
        circle11.setBackgroundColor(Color.parseColor("#FF0078"));

        circle14.setBackgroundColor(Color.parseColor("#00FFC9"));
        circlef.setBackgroundColor(Color.parseColor("#00FFC9"));
        circle15.setBackgroundColor(Color.parseColor("#00FFC9"));

        circle13.setBackgroundColor(Color.parseColor("#00BDFF"));
        circlee.setBackgroundColor(Color.parseColor("#00BDFF"));
        circle16.setBackgroundColor(Color.parseColor("#00BDFF"));

        circle12.setBackgroundColor(Color.parseColor("#9B00FF"));
        circled.setBackgroundColor(Color.parseColor("#9B00FF"));
        circle7.setBackgroundColor(Color.parseColor("#9B00FF"));

        circle9.setBackgroundColor(Color.parseColor("#FF00E0"));
        circlec.setBackgroundColor(Color.parseColor("#FF00E0"));
        circle8.setBackgroundColor(Color.parseColor("#FF00E0"));

        circle5.setBackgroundColor(Color.parseColor("#FF8300"));
        circleb.setBackgroundColor(Color.parseColor("#FF8300"));
        circle2.setBackgroundColor(Color.parseColor("#FF8300"));

        circlecenter.setBackgroundColor(Color.RED);
    }
    public void complete_dialog(){

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Game Over");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setMessage(" You have lost all your 3 life by touching RED dots \n Press OK to restart the game");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MainActivity.this,MainActivityL.class);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                        finish();
                    }
                });
        alertDialog.show();
    }
    public void backclick(View view) {
        Intent i1 = new Intent(this, MainActivityL.class);
        startActivity(i1);
        finish();
    }
    public void select_quardent(final int i1){
        circlecenter.setBackgroundColor(Color.WHITE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                circlecenter.setBackgroundColor(Color.parseColor("#2E4053"));
                if(allquardents){
                    Log.e("All Quardents",""+allquardents);
                    changeCircleGreen(i1);
                }
            }
        }, 500);
    }
    final Random r1 = new Random();
    public void changeCircleGreen(int i1){
        Log.e("Rabdoms Value",""+i1);
        if(i1 == 1){
            if(checkbox1_flag){
                circle1.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==2 ){
            if(checkbox8_flag){
                circle2.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==3 ){
            if(checkbox2_flag){
                circle3.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==4 ){
            if(checkbox7_flag){
                circle8.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==5 ){
            if(checkbox6_flag){
                circle7.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==6 ){
            if(checkbox5_flag){
                circle16.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==7 ){
            if(checkbox4_flag){
                circle15.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==8 ){
            if(checkbox3_flag){
                circle11.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==9 ){
            if(checkbox1_flag){
                circle4.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==10 ){
            if(checkbox8_flag){
                circle5.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==11 ){
            if(checkbox2_flag){
                circle6.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==12 ){
            if(checkbox7_flag){
                circle9.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==13 ){
            if(checkbox6_flag){
                circle12.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==14 ){
            if(checkbox4_flag){
                circle14.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==15 ){
            if(checkbox5_flag){
                circle13.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==16 ){
            if(checkbox3_flag){
                circle10.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==17 ){
            if(checkbox1_flag){
                circlea.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==18 ){
            if(checkbox8_flag){
                circleb.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==19 ){
            if(checkbox7_flag){
                circlec.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==20 ){

            if(checkbox6_flag){
                circled.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==21 ){

            if(checkbox5_flag){
                circlee.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==22 ){

            if(checkbox4_flag){
                circlef.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==23 ){

            if(checkbox3_flag){
                circleg.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }else if(i1 ==24 ){

            if(checkbox2_flag){
                circleh.setBackgroundColor(Color.WHITE);
            }else {
                changeCircleGreen(r1.nextInt(max - min) + min);
            }

        }
    }



    ArrayList<Integer> list = new ArrayList<Integer>();
    public void randomnumber(){

        for (int i=min; i<max+1; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
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

    public void checkboxselectall(){
        checkbox_clicked=0;
        checkbox1.setChecked(true);
        checkbox2.setChecked(true);
        checkbox3.setChecked(true);
        checkbox4.setChecked(true);
        checkbox5.setChecked(true);
        checkbox6.setChecked(true);
        checkbox7.setChecked(true);
        checkbox8.setChecked(true);

        checkbox1_flag=true;
        checkbox2_flag=true;
        checkbox3_flag=true;
        checkbox4_flag=true;
        checkbox5_flag=true;
        checkbox6_flag=true;
        checkbox7_flag=true;
        checkbox8_flag=true;

        Toast.makeText(MainActivity.this, "All The Radial's can't be UnChecked!",Toast.LENGTH_SHORT).show();
    }
}
