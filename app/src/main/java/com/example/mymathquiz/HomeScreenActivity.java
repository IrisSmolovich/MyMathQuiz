package com.example.mymathquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class HomeScreenActivity extends AppCompatActivity {
    Button btn_play, btn_instructions, btn_settings, btn_exitapp;
    ImageView btn_changelang;
    TextView tv_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadLocale();
        setContentView(R.layout.activity_home_screen);

        btn_play = findViewById(R.id.play_btn);
 //       btn_score = findViewById(R.id.score_btn);
        btn_settings = findViewById(R.id.set_btn);
        btn_exitapp = findViewById(R.id.btn_exitapp);
        btn_changelang = findViewById(R.id.btn_changelang);
        tv_hello = findViewById(R.id.tv_hello);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        tv_hello.startAnimation(animation);

            View.OnClickListener SettingClickListener = v -> {
                Intent intent = new Intent(HomeScreenActivity.this, SettingsActivity.class);
                startActivity(intent);
            };

            btn_settings.setOnClickListener(SettingClickListener);


            btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreenActivity.this, ChooseLevelActivity.class);
                    startActivity(intent);
                }
            });

 /*           btn_score.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreenActivity.this, HighScoreActivity.class);
                    startActivity(intent);
                }
            }); */

            btn_exitapp.setOnClickListener(v -> {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            });

            btn_changelang.setOnClickListener(v -> {
                /* show popup window (dialog) to display list of languages, the user will pick its preferable one */
                showChangeLangDialog();
            });
        }

        /* display list of all possible languages */
        public void showChangeLangDialog () {
            final String[] listItems = {"English", "עברית"};
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreenActivity.this);
            builder.setTitle(getString(R.string.choose_lang));
            builder.setSingleChoiceItems(listItems, -1, (dialog, i) -> {
                if (i == 0) {
                    setLocale("en");
                    recreate();
                } else if (i == 1) {
                    setLocale("iw");
                    recreate();
                }
                dialog.dismiss();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        private void setLocale (String lang){
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

            /* safe data to shared preferences so in next use the chosen language will automatically by applied */
            SharedPreferences.Editor editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
            editor.putString("My_Language", lang);
            editor.apply();
        }

        /* load language saved in Shared Preferences */
        public void loadLocale () {
            SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
            String language = prefs.getString("My_Language", "");
            setLocale(language);
        }


}