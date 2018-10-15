package ragul.srushty.com.neuroeye;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SelectingModeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Context context;
    DBHelperPatientLastTest mypatientvital;
    DBHelperDifferentiation mydbdif;
    DBHelperAntiscades mydbanti;

    DrawerLayout drawer_layout;
    RelativeLayout touch;


//    Button export_diff;
//    Button export_anti;
//    Button export_test;

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)

    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectingmode);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.closeDrawer(Gravity.START, true);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        if (shouldAskPermissions()) {
            askPermissions();
        }

        mypatientvital = new DBHelperPatientLastTest(this);
        mydbdif = new DBHelperDifferentiation(this);
        mydbanti = new DBHelperAntiscades(this);

//        export_diff = (Button) findViewById(R.id.export_diff);
//        export_anti = (Button) findViewById(R.id.export_anti);
//        export_test = (Button) findViewById(R.id.export_test);


        final String loggedIn = "logged_in";
        final String patientId = "logged_in_patientId";
        final String patientage = "logged_in_patientage";
        final String patienttestdate = "logged_in_patienttestdate";
        final String patientgender = "logged_in_patientgender";
        final String loggedInTestId = "logged_in_test_id";
        final String patientemail = "logged_in_patientemail";
        final String patientphonenumber = "logged_in_patientphonenumber";

        String local_loggedin;
        String local_patientid;
        String local_patientage;
        String local_patienttestdate;
        String local_patientgender;
        String local_patientemail;
        String local_patientphonenumber;
        String local_patient_history_test_value="000";
        int local_loggedInTestId;

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SelectingModeActivity.this);
        local_loggedin= prefs.getString(loggedIn,"");
        local_patientid=prefs.getString(patientId,"");
        local_patientage=prefs.getString(patientage, "");
        local_patienttestdate=prefs.getString(patienttestdate, "");
        local_patientgender=prefs.getString(patientgender, "");
        local_patientemail=prefs.getString(patientemail,"");
        local_patientphonenumber=prefs.getString(patientphonenumber, "");
        local_loggedInTestId=prefs.getInt(loggedInTestId, 0);

        Log.e("User Details",local_loggedin+" , "+local_patientid+" , "+local_patientage+" , "+local_patienttestdate+" , "+local_patientgender+" , "+local_patientemail+" , "+local_patientphonenumber+" , "+local_loggedInTestId);

        TextView id=(TextView) findViewById(R.id.idval);
        TextView gender=(TextView) findViewById(R.id.genderval);
        TextView name=(TextView) findViewById(R.id.nameval);
        TextView date=(TextView) findViewById(R.id.dateval);
        TextView age=(TextView) findViewById(R.id.ageval);
        TextView phonenumber=(TextView) findViewById(R.id.phoneval);

        id.setText(local_patientid+"");
        gender.setText(local_patientgender+"");
        name.setText(local_loggedin+"");
        date.setText(local_patienttestdate+"");
        age.setText(local_patientage+"");
        phonenumber.setText(local_patientphonenumber+"");

        String local_patient_history_test_value2 = mypatientvital.getLatestPatientTest(local_patientid);
        String last_test_Total_time_taken = mypatientvital.getPatientTotalTime(local_patientid);
        String last_test_Dot_Size = mypatientvital.getPatientDotSize(local_patientid);
        String last_test_accuracy = mypatientvital.getPatientAccuracy(local_patientid);
        String last_test_q1 = mypatientvital.getPatientQuadrantOne(local_patientid);
        String last_test_q2 = mypatientvital.getPatientQuadrantTwo(local_patientid);
        String last_test_q3 = mypatientvital.getPatientQuadrantThree(local_patientid);
        String last_test_q4 = mypatientvital.getPatientQuadrantFour(local_patientid);

        Log.e("Average Time",""+local_patient_history_test_value2);
        Log.e("Total Time",""+last_test_Total_time_taken);
        Log.e("Dot Size",""+last_test_Dot_Size);
        Log.e("Accuracy",""+last_test_accuracy);
        Log.e("Quardent 1",""+last_test_q1);
        Log.e("Quardent 2",""+last_test_q2);
        Log.e("Quardent 3",""+last_test_q3);
        Log.e("Quardent 4",""+last_test_q4);

        String total_time = mydbdif.totaltime(local_patientid);
        String avg_time = mydbdif.avgtime(local_patientid);
        String wrong_object = mydbdif.wrongobject(local_patientid);

        Log.e("totaltime", "" + total_time);
        Log.e("avgtime", "" + avg_time);
        Log.e("wrongobject", "" + wrong_object);


        String anti_total_time = mydbanti.gettotaltime(local_patientid);
        String anti_avg_time_stage1 = mydbanti.getavgtime_stage1(local_patientid);
        String anti_avg_time_stage2 = mydbanti.getavgtime_stage2(local_patientid);
        String anti_avg_time_stage3 = mydbanti.getavgtime_stage3(local_patientid);

        String anti_correct_stage1 = mydbanti.getcorrect_stage1(local_patientid);
        String anti_wrong_stage1 = mydbanti.getwrong_stage1(local_patientid);
        String anti_correct_stage2 = mydbanti.getcorrect_stage2(local_patientid);
        String anti_wrong_stage2 = mydbanti.getwrong_stage2(local_patientid);
        String anti_correct_stage3 = mydbanti.getcorrect_stage3(local_patientid);
        String anti_wrong_stage3 = mydbanti.getwrong_stage3(local_patientid);

        Log.e("anti_total_time", "" + anti_total_time);
        Log.e("anti_avg_time_stage1", "" + anti_avg_time_stage1);
        Log.e("anti_avg_time_stage2", "" + anti_avg_time_stage2);
        Log.e("anti_avg_time_stage3", "" + anti_avg_time_stage3);

        Log.e("anti_correct_stage1", "" + anti_correct_stage1);
        Log.e("anti_wrong_stage1", "" + anti_wrong_stage1);
        Log.e("anti_correct_stage2", "" + anti_correct_stage2);
        Log.e("anti_wrong_stage2", "" + anti_wrong_stage2);
        Log.e("anti_correct_stage3", "" + anti_correct_stage3);
        Log.e("anti_wrong_stage3", "" + anti_wrong_stage3);

//        Button logout=(Button) findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(SelectingModeActivity.this,MainActivityL.class);
//                startActivity(i);
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
//                finish();
//            }
//        });

        Button training=(Button) findViewById(R.id.training);
        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectingModeActivity.this,MainActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });

        Button testing=(Button) findViewById(R.id.testing);
        testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectingModeActivity.this,Testing.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });

        Button task=(Button) findViewById(R.id.task);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectingModeActivity.this,DifferentiationTaskActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });

        Button antisaccades=(Button) findViewById(R.id.antisaccades);
        antisaccades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectingModeActivity.this,Antisaccades.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });

//        export_test = (Button) findViewById(R.id.export_test);
//
//        export_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    new ExportDatabaseTestCSV().execute("");
//                } catch (Exception ex) {
//                    Log.e("Error in MainActivity", ex.toString());
//                }
//            }
//        });
//
//        export_diff = (Button) findViewById(R.id.export_diff);
//
//        export_diff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    new ExportDatabaseDiffCSV().execute("");
//                } catch (Exception ex) {
//                    Log.e("Error in MainActivity", ex.toString());
//                }
//            }
//        });
//
//        export_anti = (Button) findViewById(R.id.export_anti);
//
//        export_anti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    new ExportDatabaseAntiCSV().execute("");
//                } catch (Exception ex) {
//                    Log.e("Error in MainActivity", ex.toString());
//                }
//            }
//        });

    }

    class ExportDatabaseTestCSV extends AsyncTask<String, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(SelectingModeActivity.this);
        SQLiteDatabase sqldb = mypatientvital.getReadableDatabase();

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args) {
            File dbFile = getDatabasePath("MyDBNameNeuroTest.db");
            System.out.println(dbFile);  // displays the data base path in your logcat
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");

            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }

            File file = new File(exportDir, "NeuroEye/TestingBackupCSV.csv");
            try {
                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                Cursor curCSV = sqldb.rawQuery("select * from neurolasttest", null);
                csvWrite.writeNext(curCSV.getColumnNames());
                while (curCSV.moveToNext()) {
                    String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2),curCSV.getString(3),curCSV.getString(4),curCSV.getString(5),curCSV.getString(6),curCSV.getString(7),curCSV.getString(8),curCSV.getString(9),curCSV.getString(10),curCSV.getString(11),curCSV.getString(12),curCSV.getString(13),curCSV.getString(14),curCSV.getString(15),curCSV.getString(16),curCSV.getString(17),curCSV.getString(18),curCSV.getString(19)};
//                    curCSV.getString(3),curCSV.getString(4)};
                    csvWrite.writeNext(arrStr);
                }
                csvWrite.close();
                curCSV.close();
                return true;
            } catch (SQLException sqlEx) {
                Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
                return false;
            } catch (IOException e) {
                Log.e("MainActivity", e.getMessage(), e);
                return false;
            }
        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success) {
                Toast.makeText(SelectingModeActivity.this, "Export successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SelectingModeActivity.this, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }

    }


    class ExportDatabaseDiffCSV extends AsyncTask<String, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(SelectingModeActivity.this);
        SQLiteDatabase sqldb = mydbdif.getReadableDatabase();

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args) {
            File dbFile = getDatabasePath("MyDbDifferentiation.db");
            System.out.println(dbFile);  // displays the data base path in your logcat
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");

            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }

            File file = new File(exportDir, "NeuroEye/DifferentiationBackupCSV.csv");
            try {
                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                Cursor curCSV = sqldb.rawQuery("select * from patient_differentiation", null);
                csvWrite.writeNext(curCSV.getColumnNames());
                while (curCSV.moveToNext()) {
                    String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2),curCSV.getString(3),curCSV.getString(4),curCSV.getString(5),curCSV.getString(6)};
//                    curCSV.getString(3),curCSV.getString(4)};
                    csvWrite.writeNext(arrStr);
                }
                csvWrite.close();
                curCSV.close();
                return true;
            } catch (SQLException sqlEx) {
                Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
                return false;
            } catch (IOException e) {
                Log.e("MainActivity", e.getMessage(), e);
                return false;
            }
        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success) {
                Toast.makeText(SelectingModeActivity.this, "Export successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SelectingModeActivity.this, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }

    }


    class ExportDatabaseAntiCSV extends AsyncTask<String, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(SelectingModeActivity.this);
        SQLiteDatabase sqldb = mydbanti.getReadableDatabase();

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args) {
            File dbFile = getDatabasePath("MyDbAntiscades.db");
            System.out.println(dbFile);  // displays the data base path in your logcat
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");

            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }

            File file = new File(exportDir, "NeuroEye/AntiscadesBackupCSV.csv");
            try {
                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                Cursor curCSV = sqldb.rawQuery("select * from patient_antiscades", null);
                csvWrite.writeNext(curCSV.getColumnNames());
                while (curCSV.moveToNext()) {
                    String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2),curCSV.getString(3),curCSV.getString(4),curCSV.getString(5),curCSV.getString(6),curCSV.getString(7),curCSV.getString(8),curCSV.getString(9),curCSV.getString(10),curCSV.getString(11),curCSV.getString(12),curCSV.getString(13)};
//                    curCSV.getString(3),curCSV.getString(4)};
                    csvWrite.writeNext(arrStr);
                }
                csvWrite.close();
                curCSV.close();
                return true;
            } catch (SQLException sqlEx) {
                Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
                return false;
            } catch (IOException e) {
                Log.e("MainActivity", e.getMessage(), e);
                return false;
            }
        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success) {
                Toast.makeText(SelectingModeActivity.this, "Export successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SelectingModeActivity.this, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Toast.makeText(getApplicationContext(),"Home", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(SelectingModeActivity.this,MainActivityL.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
            finish();

        }

        else if (id == R.id.back) {
            backclick();
            return true;
        }

        else if (id == R.id.cache) {

            deleteFiles("/sdcard/NeuroEye/");
        }

        else if (id == R.id.export_diff) {

            try {
                new ExportDatabaseDiffCSV().execute("");
            } catch (Exception ex) {
                Log.e("Error in MainActivity", ex.toString());
            }
        }


        else if (id == R.id.export_test) {

            try {
                new ExportDatabaseTestCSV().execute("");
            } catch (Exception ex) {
                Log.e("Error in MainActivity", ex.toString());
            }
        }

        else if (id == R.id.export_anti) {

            try {
                new ExportDatabaseAntiCSV().execute("");
            } catch (Exception ex) {
                Log.e("Error in MainActivity", ex.toString());
            }
        }

        else if (id == R.id.logout) {

            Intent i = new Intent(SelectingModeActivity.this,MainActivityL.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
            finish();
        }

        else if (id == R.id.exit) {
            Toast.makeText(getApplicationContext(),"Exit", Toast.LENGTH_SHORT).show();
            finishAffinity();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void backclick() {
        Intent i1 = new Intent(this, MainActivityL.class);
        startActivity(i1);
        finish();
    }

    public void deleteFiles(String path) {

        File dir = new File(path);
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
            Toast.makeText(getApplicationContext(),"Deleted Sucessfully", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




}
