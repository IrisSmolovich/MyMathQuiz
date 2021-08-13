package com.example.mymathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLevelActivity extends AppCompatActivity {
    Button btn_level1, btn_level2, btn_level3, btn_dual;
    ImageView iv_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        btn_level1 = findViewById(R.id.btn_level1);
        btn_level2 = findViewById(R.id.btn_level2);
        btn_level3 = findViewById(R.id.btn_level3);
        iv_exit = findViewById(R.id.iv_exiticon);
        btn_dual = findViewById(R.id.btn_levelDual);

        iv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ChooseLevelActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

        //move to level 1: sum
        btn_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevelActivity.this, GamePageActivity.class);
                startActivity(intent);
            }
        });

        //move to level 2: sub
        btn_level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevelActivity.this, GamePage3Activity.class);
                startActivity(intent);
            }
        });

        //move to level3: combination of both previous levels
        btn_level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevelActivity.this, GamePage2Activity.class);
                startActivity(intent);
            }
        });

        //move to dual level
        btn_dual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevelActivity.this, DualGamePageActivity.class);
                startActivity(intent);
            }
        });


    }
}