package ragul.srushty.com.neuroeye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;


public class MainActivityL extends AppCompatActivity  {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainl);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        deleteFilesPrintPdf("/sdcard/NeuroEye1/"); //Delete Printed Pdf Files


        Button close_app=(Button) findViewById(R.id.close_app);
        close_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
        Button login=(Button) findViewById(R.id.startbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityL.this,PatientSearch.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });
        Button signup=(Button) findViewById(R.id.startbutton2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityL.this,Mainactivity3.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);finish();
                finish();
            }
        });
    }

    public void deleteFilesPrintPdf(String path) {

        File dir = new File(path);
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            try {
                for (int i = 0; i < children.length; i++)
                {
                    new File(dir, children[i]).delete();
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

}
