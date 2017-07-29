package com.example.melvin.myapplication;

import android.app.Activity;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Game2 extends AppCompatActivity {
    ImageView b1, b2,b3, bullet_shots;
    boolean down_click;
    TextView gamer,sc;
    int score=1;
    java.util.Timer t4, birdTimer, t8, t9,t10;
    float bulletX;
    RelativeLayout main;
    ImageButton up;float bullet_original_Y;
    ImageButton bomber;
    ImageView jet;
    ProgressBar progressBar,progressBar1;int progress=100,progress1=100;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game2);
        ActionBar actionBar= getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        sc=(TextView)findViewById(R.id.score);
        gamer = (TextView) findViewById(R.id.player);
        up = (ImageButton) findViewById(R.id.up);
        b1 = (ImageView) findViewById(R.id.bird1);
        b2 = (ImageView) findViewById(R.id.bird2);
        b3 = (ImageView) findViewById(R.id.bird3);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar3);
        bullet_shots = (ImageView) findViewById(R.id.bullet);
        bullet_shots.setVisibility(View.INVISIBLE);
        bulletX = bullet_shots.getX();bullet_original_Y = bullet_shots.getY();
        bomber = (ImageButton) findViewById(R.id.bomb);
        main = (RelativeLayout) findViewById(R.id.mainlayout);
        birdTimer = new java.util.Timer();
        jet=(ImageView)findViewById(R.id.jetpack);
        jet.setY(gamer.getY()+gamer.getHeight()*2);
        jet.setVisibility(View.INVISIBLE);
        progressBar.setProgress(progress);
        progressBar1.setProgress(progress1);
       up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    jet.setVisibility(View.VISIBLE);
                    if (down_click)
                        t8.cancel();
                    t9 = new java.util.Timer();

                    t9.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            sendBroadcast(new Intent("UP"));

                        }

                    }, 30, 30);

                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    jet.setVisibility(View.INVISIBLE);
                    t9.cancel();

                    t8 = new java.util.Timer();
                    t8.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            sendBroadcast(new Intent("DOWN"));

                        }
                    }, 30, 30);
                }
                return false;
            }
        });
    }

    public void bomb(View view) {
        if (main.getHeight() <= gamer.getY()+gamer.getHeight()) {
            bullet_shots.setY(gamer.getY() + 20);
        }
        if (gamer.getY()<0) {
            bullet_shots.setY(gamer.getY()+20);
        }
        bomber.setEnabled(false);
        bullet_shots.setVisibility(View.VISIBLE);
        t4 = new java.util.Timer();
        t4.schedule(new TimerTask() {
            @Override
            public void run() {
                sendBroadcast(new Intent("BOMB"));

            }
        }, 20, 20);

    }
    BroadcastReceiver receiver5 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            gamer.setY(gamer.getY() - 10);
            jet.setY(jet.getY()- 10);
            if (progressBar1.getProgress()<=0){
                progressBar1.setProgress(0);
            }
            else{
                progressBar1.setProgress(progressBar1.getProgress()-2);
            }
            if (bullet_shots.getVisibility() == View.INVISIBLE)
                bullet_shots.setY(gamer.getY()+5);
            if (gamer.getY()<0){
                t9.cancel();
            }
        }

    };
    BroadcastReceiver receiver4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            down_click = true;
            gamer.setY(gamer.getY() + 10);
            jet.setY(jet.getY()+10);

            if (progressBar1.getProgress()>=100){
                progressBar1.setProgress(100);
            }
            else{
                progressBar1.setProgress(progressBar1.getProgress()+3);
            }
            if (bullet_shots.getVisibility() == View.INVISIBLE)
                bullet_shots.setY(gamer.getY() + 20);
            if (main.getHeight() <= gamer.getY()+gamer.getHeight()){
                if (bullet_shots.getVisibility() == View.INVISIBLE)
                bullet_shots.setY(gamer.getY()+20);
                t8.cancel();
            }
        }
    };
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            bullet_shots.setX(bullet_shots.getX() + 40);
            if (bullet_shots.getX() > main.getWidth()) {
                bomber.setEnabled(true);
                bullet_shots.setVisibility(View.INVISIBLE);
                bullet_shots.setX(gamer.getWidth() + gamer.getX());
                t4.cancel();
            }
            onhit();
        }

    };
    BroadcastReceiver receiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            b1.setX(b1.getX() - 10);
            if (b1.getX() < 0) {
                progress-=10;
                progressBar.setProgress(progress);
                b1.setX(main.getWidth());
            }
        }

    };
    BroadcastReceiver receiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            b2.setX(b2.getX() - 10);
            if (b2.getX() < 0) {
                progress-=10;
                progressBar.setProgress(progress);
                b2.setX(main.getWidth());
            }
        }

    };
    BroadcastReceiver receiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            b3.setX(b3.getX() - 10);
            if (b3.getX() < 0) {
                progress-=10;
                progressBar.setProgress(progress);
                b3.setX(main.getWidth());
            }
        }

    };

    @Override
    protected void onResume() {
        super.onResume();

        birdTimer.schedule(new TimerTask() {
                               int i = 0;
                               @Override
                               public void run() {
                                   sendBroadcast(new Intent("BIRD2"));
                                   if (++i%2 == 0)
                                       sendBroadcast(new Intent("BIRD1"));

                                   sendBroadcast(new Intent("BIRD3"));
                               }
                           },40
                ,40);



        registerReceiver(receiver5, new IntentFilter("UP"));
        registerReceiver(receiver4, new IntentFilter("DOWN"));
        registerReceiver(receiver3, new IntentFilter("BIRD3"));
        registerReceiver(receiver2, new IntentFilter("BIRD2"));
        registerReceiver(receiver1, new IntentFilter("BIRD1"));
        registerReceiver(receiver, new IntentFilter("BOMB"));
    }


    @Override
    protected void onPause() {

        if (birdTimer!=null)
        birdTimer.cancel();
        if (t8!=null)
        t8.cancel();
        super.onPause();
        birdTimer.cancel();
        unregisterReceiver(receiver);
        unregisterReceiver(receiver4);
        unregisterReceiver(receiver5);
        unregisterReceiver(receiver1);
        unregisterReceiver(receiver3);
        unregisterReceiver(receiver2);
    }



    public void onhit() {
        if (bullet_shots.getY() <= b1.getY() + b1.getHeight() && bullet_shots.getY() >= b1.getY() && bullet_shots.getX() > b1.getX()) {
                change();
                int r = new Random().nextInt(main.getHeight() - 400);
                b1.setX(main.getWidth());
                b1.setY(r);
                sc.setText(score++ +"");

        }


        if (bullet_shots.getY() <= b2.getY() + b2.getHeight() && bullet_shots.getY() >= b2.getY() && bullet_shots.getX() > b2.getX()) {
                change();
                int r = new Random().nextInt(main.getHeight() - 400);
                b2.setX(main.getWidth());
                b2.setY(r);
                sc.setText(score++ +"");


        }
        if (bullet_shots.getY() <= b3.getY() + b3.getHeight() && bullet_shots.getY() >= b3.getY() && bullet_shots.getX() > b3.getX()) {
                change();
                int r = new Random().nextInt(main.getHeight() - 400);
                b3.setX(main.getWidth());
                b3.setY(r);
                sc.setText(score++ +"");
            }

        }
    public void change() {
        bomber.setEnabled(true);
        bullet_shots.setVisibility(View.INVISIBLE);
        bullet_shots.setX(gamer.getWidth() + gamer.getX());
        t4.cancel();
    }

}