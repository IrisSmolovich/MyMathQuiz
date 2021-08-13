package com.example.mymathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ModifySoundActivity extends AppCompatActivity {
    Button btn_soundOn, btn_soundOff;
    ImageView  btn_exitsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_sound);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btn_soundOff = findViewById(R.id.btn_soundoff);
        btn_soundOn = findViewById(R.id.btn_soundon);
        btn_exitsound = findViewById(R.id.btn_exitsound);



        btn_soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ModifySoundActivity.this, BackgroundMusic.class);
                startService(intent);
            }
        });

        btn_soundOff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ModifySoundActivity.this, BackgroundMusic.class);
                stopService(intent);
            }
        });

        btn_exitsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifySoundActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}