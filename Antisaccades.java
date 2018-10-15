package ragul.srushty.com.neuroeye;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

public class Antisaccades extends AppCompatActivity {

    RelativeLayout layoutOfPopup;
    RelativeLayout print_layour3;
    public static int REQUEST_PERMISSIONS = 1;
    Button Left3,Left2,Left1,Right1,Right2,Right3;
    RelativeLayout RLLeft3,RLLeft2,RLLeft1,RLRight1,RLRight2,RLRight3;
    TextView RandomTitle,LabelLeft3,LabelLeft2,LabelLeft1,LabelRight1,LabelRight2,LabelRight3;
    Button printid;
    Button printpdf;
    boolean boolean_permission;
    Bitmap bitmap;
    String targetPdf;
    File filePath;
    Boolean boolean_save;
    Button close;

    TextView patient_nameview,date_view,totaltime_view,correct_stage1,wrong_stage1,avarage_reaction_time_stage1,correct_stage2,wrong_stage2,avarage_reaction_time_stage2,correct_stage3,wrong_stage3,avarage_reaction_time_stage3;
    long totalduration;

    int TouchCount=0;
    ArrayList<String> list = new ArrayList<String>();
    String local_patientid;
    int local_loggedInTestId;
    String local_loggedin;
    String local_patientage;
    String local_patienttestdate;
    String local_patientgender;
    String local_patientemail;
    String local_patientphonenumber;
    String local_patient_history_test_value="000";

    ProgressDialog progressDialog;

    DBHelperAntiscades mydbanti;
    long start;
    long Stage1_time_tkn,Stage2_time_tkn,Stage3_time_tkn;
    int Stage1_crt_oj=0,Stage2_crt_oj=0,Stage3_crt_oj=0,Stage1_wng_oj=0,Stage2_wng_oj=0,Stage3_wng_oj=0;
    private long timeElapsed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.antisaccades);

        mydbanti=new DBHelperAntiscades(this);
        start = System.currentTimeMillis();
        fn_permission();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        list.add(new String("#0048ff"));
        list.add(new String("#ff0004"));

        final String loggedIn = "logged_in";
        final String patientId = "logged_in_patientId";
        final String patientage = "logged_in_patientage";
        final String patienttestdate = "logged_in_patienttestdate";
        final String patientgender = "logged_in_patientgender";
        final String loggedInTestId = "logged_in_test_id";
        final String patientemail = "logged_in_patientemail";
        final String patientphonenumber = "logged_in_patientphonenumber";

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Antisaccades.this);
        local_loggedin= prefs.getString(loggedIn,"");
        local_patientid=prefs.getString(patientId,"");
        local_patientage=prefs.getString(patientage, "");
        local_patienttestdate=prefs.getString(patienttestdate, "");
        local_patientgender=prefs.getString(patientgender, "");
        local_patientemail=prefs.getString(patientemail,"");
        local_patientphonenumber=prefs.getString(patientphonenumber, "");
        local_loggedInTestId=prefs.getInt(loggedInTestId, 0);


        layoutOfPopup= (RelativeLayout) findViewById(R.id.layoutOfPopup);
        print_layour3 = (RelativeLayout) findViewById(R.id.print_layour3);

        LabelLeft3  =(TextView) findViewById(R.id.Stage3Blue);
        LabelLeft2  =(TextView) findViewById(R.id.Stage2Blue);
        LabelLeft1  =(TextView) findViewById(R.id.Stage1Blue);
        LabelRight1 =(TextView) findViewById(R.id.Stage1Red);
        LabelRight2 =(TextView) findViewById(R.id.Stage2Red);
        LabelRight3 =(TextView) findViewById(R.id.Stage3Red);
        RandomTitle =(TextView) findViewById(R.id.Randomtitle);

        LabelLeft3.setVisibility(View.GONE);
        LabelLeft2.setVisibility(View.GONE);
        LabelRight2.setVisibility(View.GONE);
        LabelRight3.setVisibility(View.GONE);

        Left3 =(Button) findViewById(R.id.BlueButton3);
        Left2 =(Button) findViewById(R.id.BlueButton2);
        Left1 =(Button) findViewById(R.id.BlueButton1);
        Right1 =(Button) findViewById(R.id.RedButton1);
        Right2 =(Button) findViewById(R.id.RedButton2);
        Right3 =(Button) findViewById(R.id.RedButton3);

        Left3.setVisibility(View.GONE);
        Left2.setVisibility(View.GONE);
        Right2.setVisibility(View.GONE);
        Right3.setVisibility(View.GONE);

        RLLeft3 =(RelativeLayout) findViewById(R.id.RLleftbutton3);
        RLLeft2 =(RelativeLayout) findViewById(R.id.RLleftbutton2);
        RLLeft1 =(RelativeLayout) findViewById(R.id.RLleftbutton1);
        RLRight1 =(RelativeLayout) findViewById(R.id.RLrightbutton1);
        RLRight2 =(RelativeLayout) findViewById(R.id.RLrightbutton2);
        RLRight3 =(RelativeLayout) findViewById(R.id.RLrightbutton3);

        patient_nameview=(TextView) findViewById(R.id.patient_nameview);
        date_view = (TextView) findViewById(R.id.date_view);
        totaltime_view=(TextView) findViewById(R.id.totaltime_view);

        correct_stage1=(TextView) findViewById(R.id.correct_stage1);
        wrong_stage1=(TextView) findViewById(R.id.wrong_stage1);
        avarage_reaction_time_stage1=(TextView) findViewById(R.id.avarage_reaction_time_stage1);

        correct_stage2=(TextView) findViewById(R.id.correct_stage2);
        wrong_stage2=(TextView) findViewById(R.id.wrong_stage2);
        avarage_reaction_time_stage2=(TextView) findViewById(R.id.avarage_reaction_time_stage2);

        correct_stage3=(TextView) findViewById(R.id.correct_stage3);
        wrong_stage3=(TextView) findViewById(R.id.wrong_stage3);
        avarage_reaction_time_stage3=(TextView) findViewById(R.id.avarage_reaction_time_stage3);

        Left3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouchCount++;
                Log.e("Left 3","Clicked");
                changeButtoncolor(RLLeft3);
                changeTitletextcolor();

                if(TouchCount==15){
                    LabelLeft3.setVisibility(View.GONE);
                    LabelRight3.setVisibility(View.GONE);
                    Left3.setVisibility(View.GONE);
                    Right3.setVisibility(View.GONE);
                    layoutOfPopup.setVisibility(View.VISIBLE);

                    Stage3_crt_oj=correct_ojt_pressed-Stage2_crt_oj-Stage1_crt_oj;
                    Stage3_wng_oj=wrong_ojt_pressed-Stage2_wng_oj-Stage1_wng_oj;
                    Stage3_time_tkn=System.currentTimeMillis()-start;
                    Stage3_time_tkn=Stage3_time_tkn-Stage2_time_tkn-Stage1_time_tkn;

                    totalduration = Stage1_time_tkn+Stage2_time_tkn+Stage3_time_tkn; //Total Duratiom
                    patient_nameview.setText(local_patientid);
                    date_view.setText(local_patienttestdate);
                    totaltime_view.setText(totalduration+"");

                    correct_stage1.setText(Stage1_crt_oj+"");
                    wrong_stage1.setText(Stage1_wng_oj+"");
                    avarage_reaction_time_stage1.setText(Stage1_time_tkn+"");

                    correct_stage2.setText(Stage2_crt_oj+"");
                    wrong_stage2.setText(Stage2_wng_oj+"");
                    avarage_reaction_time_stage2.setText(Stage2_time_tkn+"");

                    correct_stage3.setText(Stage3_crt_oj+"");
                    wrong_stage3.setText(Stage3_wng_oj+"");
                    avarage_reaction_time_stage3.setText(Stage3_time_tkn+"");


                    Log.e("Total duration"," "+totalduration);

                    mydbanti.insertantiscades(local_patientid,local_loggedin,local_loggedInTestId,totalduration,Stage1_time_tkn,Stage1_crt_oj,Stage1_wng_oj,Stage2_time_tkn,Stage2_crt_oj,Stage2_wng_oj,Stage3_time_tkn,Stage3_crt_oj,Stage3_wng_oj);
                    Log.e("Rg Correct Objects S3 "," "+Stage3_crt_oj);
                    Log.e("Rg Wrong Objects S3"," "+Stage3_wng_oj);
                    Log.e("Rg Time Tkn S3"," "+Stage3_time_tkn);
                }
            }
        });

        Left2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouchCount++;
                Log.e("Left 2","Clicked");
                changeButtoncolor(RLLeft2);
                changeTitletextcolor();
                if(TouchCount==10 ){
                    LabelLeft2.setVisibility(View.GONE);
                    LabelRight2.setVisibility(View.GONE);
                    Left2.setVisibility(View.GONE);
                    Right2.setVisibility(View.GONE);

                    LabelLeft3.setVisibility(View.VISIBLE);
                    LabelRight3.setVisibility(View.VISIBLE);
                    Left3.setVisibility(View.VISIBLE);
                    Right3.setVisibility(View.VISIBLE);

                    Stage2_time_tkn=System.currentTimeMillis()-start;
                    Stage2_time_tkn=Stage2_time_tkn-Stage1_time_tkn;
                    Stage2_crt_oj=correct_ojt_pressed-Stage1_crt_oj;
                    Stage2_wng_oj=wrong_ojt_pressed-Stage1_wng_oj;

                    Log.e("Rg Correct Objects S2 "," "+Stage2_crt_oj);
                    Log.e("Rg Wrong Objects S2"," "+Stage2_wng_oj);
                    Log.e("Rg Time Tkn S2"," "+Stage2_time_tkn);
                }

            }
        });
        Left1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouchCount++;
                Log.e("Left 1","Clicked");
                changeButtoncolor(RLLeft1);
                changeTitletextcolor();
                if(TouchCount==5){
                    LabelLeft1.setVisibility(View.GONE);
                    LabelRight1.setVisibility(View.GONE);
                    Left1.setVisibility(View.GONE);
                    Right1.setVisibility(View.GONE);

                    LabelLeft2.setVisibility(View.VISIBLE);
                    LabelRight2.setVisibility(View.VISIBLE);
                    Left2.setVisibility(View.VISIBLE);
                    Right2.setVisibility(View.VISIBLE);

                    Stage1_time_tkn=System.currentTimeMillis()-start;
                    Stage1_crt_oj=correct_ojt_pressed;
                    Stage1_wng_oj=wrong_ojt_pressed;

                    Log.e("Rg Correct Objects S1 "," "+Stage1_crt_oj);
                    Log.e("Rg Wrong Objects S1"," "+Stage1_wng_oj);
                    Log.e("Rg Time Tkn S1"," "+Stage1_time_tkn);
                }

            }
        });
        Right1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouchCount++;
                Log.e("Right 1","Clicked");
                changeButtoncolor(RLRight1);
                changeTitletextcolor();
                if(TouchCount==5 ){
                    LabelLeft1.setVisibility(View.GONE);
                    LabelRight1.setVisibility(View.GONE);
                    Left1.setVisibility(View.GONE);
                    Right1.setVisibility(View.GONE);

                    LabelLeft2.setVisibility(View.VISIBLE);
                    LabelRight2.setVisibility(View.VISIBLE);
                    Left2.setVisibility(View.VISIBLE);
                    Right2.setVisibility(View.VISIBLE);

                    Stage1_time_tkn=System.currentTimeMillis()-start;
                    Stage1_crt_oj=correct_ojt_pressed;
                    Stage1_wng_oj=wrong_ojt_pressed;

                    Log.e("Rg Correct Objects S1 "," "+Stage1_crt_oj);
                    Log.e("Rg Wrong Objects S1"," "+Stage1_wng_oj);
                    Log.e("Rg Time Tkn S1"," "+Stage1_time_tkn);
                }

            }
        });
        Right2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouchCount++;
                Log.e("Right 2","Clicked");
                changeButtoncolor(RLRight2);
                changeTitletextcolor();
                if(TouchCount==10 ){
                    LabelLeft2.setVisibility(View.GONE);
                    LabelRight2.setVisibility(View.GONE);
                    Left2.setVisibility(View.GONE);
                    Right2.setVisibility(View.GONE);

                    LabelLeft3.setVisibility(View.VISIBLE);
                    LabelRight3.setVisibility(View.VISIBLE);
                    Left3.setVisibility(View.VISIBLE);
                    Right3.setVisibility(View.VISIBLE);
                    Stage2_time_tkn=System.currentTimeMillis()-start;
                    Stage2_time_tkn=Stage2_time_tkn-Stage1_time_tkn;

                    Stage2_crt_oj=correct_ojt_pressed-Stage1_crt_oj;
                    Stage2_wng_oj=wrong_ojt_pressed-Stage1_wng_oj;

                    Log.e("Rg Correct Objects S2 "," "+Stage2_crt_oj);
                    Log.e("Rg Wrong Objects S2"," "+Stage2_wng_oj);
                    Log.e("Rg Time Tkn S2"," "+Stage2_time_tkn);
                }

            }
        });
        Right3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TouchCount++;
                Log.e("Right 3","Clicked");
                changeButtoncolor(RLRight3);
                changeTitletextcolor();
                if(TouchCount==15){
                    LabelLeft3.setVisibility(View.GONE);
                    LabelRight3.setVisibility(View.GONE);
                    Left3.setVisibility(View.GONE);
                    Right3.setVisibility(View.GONE);
                    layoutOfPopup.setVisibility(View.VISIBLE);

                    Stage3_time_tkn=System.currentTimeMillis()-start;
                    Stage3_time_tkn=Stage3_time_tkn-Stage2_time_tkn-Stage1_time_tkn;
                    Stage3_crt_oj=correct_ojt_pressed-Stage2_crt_oj-Stage1_crt_oj;
                    Stage3_wng_oj=wrong_ojt_pressed-Stage2_wng_oj-Stage1_wng_oj;

                    totalduration = Stage1_time_tkn+Stage2_time_tkn+Stage3_time_tkn; //Total Duratiom
                    patient_nameview.setText(local_patientid);
                    date_view.setText(local_patienttestdate);
                    totaltime_view.setText(totalduration+"");

                    correct_stage1.setText(Stage1_crt_oj+"");
                    wrong_stage1.setText(Stage1_wng_oj+"");
                    avarage_reaction_time_stage1.setText(Stage1_time_tkn+"");

                    correct_stage2.setText(Stage2_crt_oj+"");
                    wrong_stage2.setText(Stage2_wng_oj+"");
                    avarage_reaction_time_stage2.setText(Stage2_time_tkn+"");

                    correct_stage3.setText(Stage3_crt_oj+"");
                    wrong_stage3.setText(Stage3_wng_oj+"");
                    avarage_reaction_time_stage3.setText(Stage3_time_tkn+"");


                    Log.e("Total duration"," "+totalduration);

                    mydbanti.insertantiscades(local_patientid,local_loggedin,local_loggedInTestId,totalduration,Stage1_time_tkn,Stage1_crt_oj,Stage1_wng_oj,Stage2_time_tkn,Stage2_crt_oj,Stage2_wng_oj,Stage3_time_tkn,Stage3_crt_oj,Stage3_wng_oj);
                    Log.e("Rg Correct Objects S3 "," "+Stage3_crt_oj);
                    Log.e("Rg Wrong Objects S3"," "+Stage3_wng_oj);
                    Log.e("Rg Time Tkn S3"," "+Stage3_time_tkn);
                }

            }
        });

        printid =(Button) findViewById(R.id.printid);
        printpdf=(Button) findViewById(R.id.printpdf);

        close=(Button) findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Antisaccades.this,MainActivityL.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });

        printpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boolean_permission) {
                    progressDialog = new ProgressDialog(Antisaccades.this);
                    progressDialog.setMessage("Please wait");
                    bitmap = loadBitmapFromView(print_layour3, print_layour3.getWidth(), print_layour3.getHeight());
                    createPdf1();
                } else { }
            }
        });


        printid.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                progressDialog = new ProgressDialog(Antisaccades.this);
                progressDialog.setMessage("Please wait");
                bitmap = loadBitmapFromView(print_layour3, print_layour3.getWidth(), print_layour3.getHeight());
                createPdf();
                filePath = new File(targetPdf);
                Intent intent = getPackageManager().getLaunchIntentForPackage("jp.co.canon.bsd.ad.pixmaprint");
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setDataAndType(Uri.fromFile(filePath), "application/pdf");
                startActivity(intent);
            }
        });
    }

    public void randomnumber(){
        Collections.shuffle(list);
    }
    String color,leftcolor,rightcolor;
    Boolean TextColorisBlue=true;
    public void changeTitletextcolor(){
        int r = (int) (Math.random()*2);
        color = new String [] {"RED","BLUE"}[r];
        randomnumber();
        leftcolor=list.get(0);
        rightcolor=list.get(1);

        if (color.equals("BLUE"))
        {
            TextColorisBlue=true;
            RandomTitle.setTextColor(Color.BLUE);
            RandomTitle.setText("BLUE");
        }else {
            TextColorisBlue=false;
            RandomTitle.setTextColor(Color.RED);
            RandomTitle.setText("RED");
        }

           RLLeft1.setBackgroundColor(Color.parseColor(leftcolor));
           RLLeft2.setBackgroundColor(Color.parseColor(leftcolor));
           RLLeft3.setBackgroundColor(Color.parseColor(leftcolor));
           RLRight1.setBackgroundColor(Color.parseColor(rightcolor));
           RLRight2.setBackgroundColor(Color.parseColor(rightcolor));
           RLRight3.setBackgroundColor(Color.parseColor(rightcolor));
    }
   int correct_ojt_pressed=0,wrong_ojt_pressed=0;

    public void changeButtoncolor(RelativeLayout Buttontouched){
        ColorDrawable buttonColor = (ColorDrawable) Buttontouched.getBackground();
        int colorId = buttonColor.getColor();

        if(TextColorisBlue &&  colorId == -65532){
            correct_ojt_pressed++;
        }else if(!TextColorisBlue &&  colorId == -16758529) {
            correct_ojt_pressed++;
        }else {
            wrong_ojt_pressed++;
        }
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

        targetPdf = "/sdcard/NeuroEye1/Anitsccades"+local_loggedin+"_"+ts+".pdf";
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Printing Successfully" , Toast.LENGTH_LONG).show();
            //btn_generate.setText("Check PDF");
            boolean_save=true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        //close the document
        document.close();
    }
    private void createPdf1(){
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

        targetPdf = "/sdcard/NeuroEye/"+local_loggedin+"_"+ts+"_Antisaccdes.pdf";
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF Saved Sucessfully" , Toast.LENGTH_LONG).show();
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

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Antisaccades.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(Antisaccades.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Antisaccades.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(Antisaccades.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
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
    public void backclick(View view) {
        Intent i1 = new Intent(this, MainActivityL.class);
        startActivity(i1);
        finish();
    }
}