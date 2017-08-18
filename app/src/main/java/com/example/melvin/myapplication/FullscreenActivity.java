package com.example.melvin.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    TextView a,b,c,e,d,f;ImageButton r;
    ImageView b1,b2,bullets,sh;ScrollView s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }

        setContentView(R.layout.activity_fullscreen);






        d=(TextView) findViewById(R.id.bg) ;
        a=(TextView)findViewById(R.id.help);
        e=(TextView)findViewById(R.id.play);
        f=(TextView) findViewById(R.id.editText4);
        r=(ImageButton)findViewById(R.id.ret);
        b=(TextView)findViewById(R.id.settings);
        c=(TextView)findViewById(R.id.credits);
        b1=(ImageView) findViewById(R.id.bird1);
        b2=(ImageView) findViewById(R.id.bird2);
        bullets=(ImageView) findViewById(R.id.bullet);
        sh=(ImageView) findViewById(R.id.shoot);
        s=(ScrollView) findViewById(R.id.scrollView4);


        r.setVisibility(View.INVISIBLE);
        f.setVisibility(View.INVISIBLE);


        a.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                r.setVisibility(View.VISIBLE);
                f.setVisibility(View.VISIBLE);
                f.setAlpha(1);
                f.setText("-FLY-\n\nHold UP arrow to fly.\nKeep an eye on your jetpack.\nIf it's completely empty, it will take few seconds to regenerate.\n\n-SHOOT-\n\nTap shoot button to shoot.");

                a.setAlpha(1);
                b.setAlpha((float) 0.2);
                c.setAlpha((float) 0.2);
                b1.setAlpha((float) 0.2);
                b2.setAlpha((float) 0.2);
                bullets.setAlpha((float) 0.2);
                sh.setAlpha((float) 0.2);
                d.setAlpha((float) 0.2);
                e.setAlpha((float) 0.2);
                s.setAlpha(1);
                return false;
            }
        });
        b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                r.setVisibility(View.VISIBLE);
                f.setVisibility(View.VISIBLE);

                a.setAlpha((float) 0.2);
                b.setAlpha((float) 1);
                c.setAlpha((float) 0.2);
                b1.setAlpha((float) 0.2);
                b2.setAlpha((float) 0.2);
                bullets.setAlpha((float) 0.2);
                sh.setAlpha((float) 0.2);
                d.setAlpha((float) 0.2);
                e.setAlpha((float) 0.2);
                s.setAlpha(1);
                return false;
            }
        });
        c.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                r.setVisibility(View.VISIBLE);
                f.setVisibility(View.VISIBLE);
                f.setText("-CREDITS-\n\nCoding,Graphics,Idea by Melvin Mathew.\n\nSpecial Thanks:\n\n Livin Mathew\nAll of my family who helped play test");

                a.setAlpha((float) 0.2);
                b.setAlpha((float) 0.2);
                c.setAlpha((float) 1);
                b1.setAlpha((float) 0.2);
                b2.setAlpha((float) 0.2);
                bullets.setAlpha((float) 0.2);
                sh.setAlpha((float) 0.2);
                d.setAlpha((float) 0.2);
                e.setAlpha((float) 0.2);
                s.setAlpha(1);
                return false;
            }
        });
        r.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                r.setVisibility(View.INVISIBLE);
                f.setVisibility(View.INVISIBLE);

                a.setAlpha(1);
                b.setAlpha((float) 1);
                c.setAlpha((float) 1);
                b1.setAlpha((float) 1);
                b2.setAlpha((float) 1);
                bullets.setAlpha((float) 1);
                sh.setAlpha((float) 1);
                d.setAlpha((float) 1);
                e.setAlpha((float) 1);
                s.setAlpha(1);
                return false;
            }
        });

e.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        startActivity(new Intent(FullscreenActivity.this,Game2.class));
        return false;
    }
});

    }

}