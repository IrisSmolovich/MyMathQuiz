package com.example.mymathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class SettingsActivity extends AppCompatActivity {
    Button btn_about_us, btn_sound,  btn_rate_us;
    Dialog dialog, dialogMusic;
    ImageView btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_settings);

        btn_about_us = findViewById(R.id.btn_about);
        btn_sound = findViewById(R.id.btn_sound);
        btn_rate_us = findViewById(R.id.btn_rate);
        btn_exit = findViewById(R.id.btn_3exit3);
        dialog = new Dialog(this);
        dialogMusic = new Dialog(this);

        btn_rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtn_rate_us();
            }
        });

        /* opens a pop up window with general info about the app */
        btn_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessage();

            }
        });

        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ModifySoundActivity.class);
                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

    }

    /* a popup window (dialog) with rating bar option to rate the app*/
    public void setBtn_rate_us() {
        Button btn_submit;
        dialog.setContentView(R.layout.popup_rate);

        btn_submit = dialog.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void openMessage(){
        AboutUsMsg aboutUsMsg = new AboutUsMsg();
        aboutUsMsg.show(getSupportFragmentManager(), "example message");

    }




}