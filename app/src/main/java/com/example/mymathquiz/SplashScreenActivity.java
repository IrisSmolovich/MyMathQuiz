package com.example.mymathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {
    private static int Splash_timeout = 2000;
//   MediaPlayer mysong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent svc=new Intent(this, BackgroundMusic.class);
        startService(svc);
        setContentView(R.layout.activity_splash_screen);

        /* initialize a background song as soon as the app launches*/
//        mysong = MediaPlayer.create(SplashScreenActivity.this, R.raw.buckaroo_music);
//        mysong.start();

        /* define settings of splash screen- a pop up screen at a beginning of the app*/
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
            startActivity(intent);
            finish();
        }, Splash_timeout);


    }

}