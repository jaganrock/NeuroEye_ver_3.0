
package ragul.srushty.com.neuroeye;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
public class LineChartActivity1 extends Activity {

    private LineChart mChart;
    Button back_button,save_pdf;
    RelativeLayout ok_module;
    Button Spot_graph,PrintButton;
    String[] quadrent_value;
    String targetPdf;
    File filePath;
    int highest_graph_value=0,lowest_graph_value=180000;

    RelativeLayout ll_pdflayout;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    boolean boolean_save;
    Bitmap bitmap;
    ProgressDialog progressDialog;

    String local_loggedin;
    String local_patientid;
    String local_patientage;
    String local_patienttestdate;
    String local_patientgender;
    String local_patientemail;
    String local_patientphonenumber;
    String local_patient_history_test_value="000";
    int local_loggedInTestId;

    TextView patient_name,Duration_in_seconds,Testdate,Stimuli_Size,Duration_seconds,Average_React_TM,Hits,Missed,Percentage,Q1,Q2,Q3,Q4;

    TextView Radial_1,Radial_2,Radial_3,Radial_4,Radial_5,Radial_6,Radial_7,Radial_8;

    String patientname,tot_time,test_date,stimuli_size,comple_time,Average_Reaction_time,Hits_value,Missed_value,percentage;

    String radial_one,radial_two,radial_three,radial_four,radial_five,radial_six,radial_seven,radial_eight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_linechart);

        ll_pdflayout = (RelativeLayout) findViewById(R.id.print_layout);
        fn_permission();
        quadrent_value=new String[4];

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

        final String loggedIn = "logged_in";
        final String patientId = "logged_in_patientId";
        final String patientage = "logged_in_patientage";
        final String patienttestdate = "logged_in_patienttestdate";
        final String patientgender = "logged_in_patientgender";
        final String loggedInTestId = "logged_in_test_id";
        final String patientemail = "logged_in_patientemail";
        final String patientphonenumber = "logged_in_patientphonenumber";

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LineChartActivity1.this);
        local_loggedin= prefs.getString(loggedIn,"");
        local_patientid=prefs.getString(patientId,"");
        local_patientage=prefs.getString(patientage, "");
        local_patienttestdate=prefs.getString(patienttestdate, "");
        local_patientgender=prefs.getString(patientgender, "");
        local_patientemail=prefs.getString(patientemail,"");
        local_patientphonenumber=prefs.getString(patientphonenumber, "");
        local_loggedInTestId=prefs.getInt(loggedInTestId, 0);

        patientname= prefs.getString(grp_patient_name,"");
        tot_time= prefs.getString(grp_tot_time,"");
        test_date= prefs.getString(grp_test_date,"");
        stimuli_size= prefs.getString(grp_stimuli_size,"");
        comple_time= prefs.getString(grp_compl_time,"");
        Average_Reaction_time= prefs.getString(grp_avg_rect_time,"");
        Hits_value= prefs.getString(grp_hits,"");
        Missed_value= prefs.getString(grp_missed,"");
        percentage= prefs.getString(grp_perc,"");
        quadrent_value[0]= prefs.getString(grp_q1,"");
        quadrent_value[1]= prefs.getString(grp_q2,"");
        quadrent_value[2]= prefs.getString(grp_q3,"");
        quadrent_value[3]= prefs.getString(grp_q4,"");
        radial_one= prefs.getString(grp_radial1,"");
        radial_two= prefs.getString(grp_radial2,"");
        radial_three= prefs.getString(grp_radial3,"");
        radial_four= prefs.getString(grp_radial4,"");
        radial_five= prefs.getString(grp_radial5,"");
        radial_six= prefs.getString(grp_radial6,"");
        radial_seven= prefs.getString(grp_radial7,"");
        radial_eight= prefs.getString(grp_radial8,"");

        int q1_integer=Integer.parseInt(quadrent_value[0]);
        int q2_integer=Integer.parseInt(quadrent_value[1]);
        int q3_integer=Integer.parseInt(quadrent_value[2]);
        int q4_integer=Integer.parseInt(quadrent_value[3]);

        if(highest_graph_value<q1_integer){
            highest_graph_value=q1_integer;
        }
        if(highest_graph_value<q2_integer){
            highest_graph_value=q2_integer;
        }
        if(highest_graph_value<q3_integer){
            highest_graph_value=q3_integer;
        }
        if(highest_graph_value<q4_integer){
            highest_graph_value=q4_integer;
        }

        if(lowest_graph_value>q1_integer){
            lowest_graph_value=q1_integer;
        }
        if(lowest_graph_value>q2_integer){
            lowest_graph_value=q2_integer;
        }
        if(lowest_graph_value>q3_integer){
            lowest_graph_value=q3_integer;
        }
        if(lowest_graph_value>q4_integer){
            lowest_graph_value=q4_integer;
        }


        back_button=(Button)findViewById(R.id.Graph_back);
        save_pdf= (Button)findViewById(R.id.PDF_button);
        ok_module = (RelativeLayout) findViewById(R.id.module_window);
        Spot_graph=(Button) findViewById(R.id.graph_button);
        PrintButton=(Button) findViewById(R.id.Print_button);
        PrintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(LineChartActivity1.this);
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
        save_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boolean_permission) {
                    progressDialog = new ProgressDialog(LineChartActivity1.this);
                    progressDialog.setMessage("Please wait");
                    bitmap = loadBitmapFromView(ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight());
                    createPdf();
//                        saveBitmap(bitmap);
                } else {

                }
                createPdf();

            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ok_module.setVisibility(View.VISIBLE);
                patient_name.setText("Patient: "+patientname);
                Duration_in_seconds.setText("Duration: "+tot_time+" Seconds");
                Testdate.setText("Test Date: "+test_date);
                Stimuli_Size.setText(""+stimuli_size);
                Duration_seconds.setText("Compl TM: "+comple_time+" Seconds");
                Average_React_TM.setText("React TM: "+Average_Reaction_time+" Seconds");
                Hits.setText("Hits: "+Hits_value);
                Missed.setText("Missed: "+Missed_value);
                Percentage.setText("Perc: "+percentage+"%");
                Q1.setText(quadrent_value[0]+"ms");
                Q2.setText(quadrent_value[1]+"ms");
                Q3.setText(quadrent_value[2]+"ms");
                Q4.setText(quadrent_value[3]+"ms");
                Radial_1.setText("Radial 1 TM: "+radial_one+"ms");
                Radial_2.setText("Radial 2 TM: "+radial_two+"ms");
                Radial_3.setText("Radial 3 TM: "+radial_three+"ms");
                Radial_4.setText("Radial 4 TM: "+radial_four+"ms");
                Radial_5.setText("Radial 5 TM: "+radial_five+"ms");
                Radial_6.setText("Radial 6 TM: "+radial_six+"ms");
                Radial_7.setText("Radial 7 TM: "+radial_seven+"ms");
                Radial_8.setText("Radial 8 TM: "+radial_eight+"ms");
            }
        });

        Spot_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ok_module.setVisibility(View.GONE);
                patient_name.setText("Patient: "+patientname);
                Duration_in_seconds.setText("Duration: "+tot_time+"Seconds");
                Testdate.setText("Test Date: "+test_date);
                Stimuli_Size.setText(""+stimuli_size);
                Duration_seconds.setText("Compl TM: "+comple_time+"Seconds");
                Average_React_TM.setText("React TM: "+Average_Reaction_time+"Seconds");
                Hits.setText("Hits: "+Hits_value);
                Missed.setText("Missed: "+Missed_value);
                Percentage.setText("Perc: "+percentage+"%");
                Q1.setText(quadrent_value[0]+"ms");
                Q2.setText(quadrent_value[1]+"ms");
                Q3.setText(quadrent_value[2]+"ms");
                Q4.setText(quadrent_value[3]+"ms");
                Radial_1.setText("Radial 1 TM: "+radial_one+"ms");
                Radial_2.setText("Radial 2 TM: "+radial_two+"ms");
                Radial_3.setText("Radial 3 TM: "+radial_three+"ms");
                Radial_4.setText("Radial 4 TM: "+radial_four+"ms");
                Radial_5.setText("Radial 5 TM: "+radial_five+"ms");
                Radial_6.setText("Radial 6 TM: "+radial_six+"ms");
                Radial_7.setText("Radial 7 TM: "+radial_seven+"ms");
                Radial_8.setText("Radial 8 TM: "+radial_eight+"ms");
            }
        });

        Button ok_button = (Button) findViewById(R.id.ok_button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LineChartActivity1.this,MainActivityL.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });
        mChart = findViewById(R.id.chart1);
        mChart.setDrawGridBackground(false);
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        // x-axis limit line
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        llXAxis.setTextColor(Color.WHITE);
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaximum(highest_graph_value+500f);
        leftAxis.setAxisMinimum(lowest_graph_value-100f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        mChart.getAxisRight().setEnabled(false);
        setData(4, 100);
        mChart.animateX(2500);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(LegendForm.LINE);
        // dont forget to refresh the drawing
        // mChart.invalidate();
    }


    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = Float.parseFloat(quadrent_value[i]);
            values.add(new Entry(i, val, " "));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, " Quadrants");
            set1.setDrawIcons(true);
            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(15f, 5f, 0f);
            set1.enableDashedHighlightLine(15f, 5f, 0f);
            set1.setColor(Color.WHITE);
            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(2f);
            set1.setCircleRadius(4f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setValueTextColor(Color.WHITE);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            }
            else {
                set1.setFillColor(Color.WHITE);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            // create a data object with the datasets
            LineData data = new LineData(dataSets);
            // set data
            mChart.setData(data);
        }
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
        // write the document content
        String targetPdf = "/sdcard/NeuroEye/"+patientname+"_"+ts+".pdf";
        File filePath = new File(targetPdf);
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

    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(LineChartActivity1.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(LineChartActivity1.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale(LineChartActivity1.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(LineChartActivity1.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        } else {
            boolean_permission = true;
        }
    }

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

        targetPdf = "/sdcard/NeuroEye1/"+local_loggedin+"_"+ts+".pdf";
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
