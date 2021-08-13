
package com.example.mymathquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DualGamePageActivity extends AppCompatActivity {

    TextView tv_question1, tv_question2, tv_timer, tv_timer2, tv_score1, tv_score2, tv_msg1, tv_msg2;
    Button button1, button2, button3, button4, button5, button6, button7, button8, btn_start;
    ProgressBar progbar;
    ImageView btn_exit;

    Game1 g = new Game1();
    Game1 g2 = new Game1();

    int secondsRemaining = 30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining) + getString(R.string.sec));
            tv_timer2.setText(Integer.toString(secondsRemaining) + getString(R.string.sec));
            progbar.setProgress(30 - secondsRemaining);

        }

        @Override
        public void onFinish() {
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);

            button5.setEnabled(false);
            button6.setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);

            tv_msg1.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));
            tv_question1.setText(getString(R.string.time_up));
            tv_msg2.setText(g2.getNumberCorrect() + "/" + (g2.getTotalQuestions() - 1));
            tv_question2.setText(getString(R.string.time_up));

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                    if(g.getScore()>g2.getScore()){
                        tv_msg1.setText(getString(R.string.you_won) + g.getScore());
                        tv_msg2.setText(getString(R.string.your_score) + g2.getScore());    }
                    else if(g.getScore()<g2.getScore()) {
                        tv_msg1.setText(getString(R.string.your_score) + g.getScore());
                        tv_msg2.setText(getString(R.string.you_won) + g2.getScore());    }
                }
            }, 1000);

        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_game_page);

// DEFINITION FOR PLAYER 1
        btn_start = findViewById(R.id.btn_dualstart);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        tv_score1 = findViewById(R.id.tv_dualscore);
        tv_msg1 = findViewById(R.id.tv_dualmsg1);
        tv_question1 = findViewById(R.id.tv_dualQuestion1);
        tv_timer = findViewById(R.id.tv_dualtimer);
        progbar = findViewById(R.id.progressBar2);


        btn_exit = findViewById(R.id.btn_exitdual);

        tv_timer.setText(getString(R.string.no_sec));
        tv_question1.setText("");
        tv_msg1.setText("");
        tv_score1.setText(getString(R.string.no_pts));
        progbar.setProgress(0);

        View.OnClickListener startButtonClickListener = (v) -> {
            Button start_button = (Button) v;

            start_button.setVisibility(View.INVISIBLE);
            secondsRemaining = 30;
            g = new Game1();
            g2 = new Game1();
            nextTurn1();
            nextTurn2();
            timer.start();

        };

        btn_start.setOnClickListener(startButtonClickListener);

// DEFINITION FOR PLAYER 2
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);

        tv_score2 = findViewById(R.id.tv_dualscore2);
        tv_msg2 = findViewById(R.id.tv_dualmsg2);
        tv_question2 = findViewById(R.id.tv_dualQustion2);
        tv_timer2 = findViewById(R.id.tv_dualtimer2);
        progbar = findViewById(R.id.progressBar2);

        tv_timer2.setText(getString(R.string.no_sec));
        tv_question2.setText("");
        tv_msg2.setText("");
        tv_score2.setText(getString(R.string.no_pts));


        View.OnClickListener answer1ButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                //               Toast.makeText(GamePageActivity.this, "AnswerSelected=" + answerSelected, Toast.LENGTH_SHORT).show();
                g.checkAnswer(answerSelected);
                tv_score1.setText(Integer.toString(g.getScore()));
                nextTurn1();

            }
        };

        View.OnClickListener answer2ButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                //               Toast.makeText(GamePageActivity.this, "AnswerSelected=" + answerSelected, Toast.LENGTH_SHORT).show();
                g2.checkAnswer(answerSelected);
                tv_score2.setText(Integer.toString(g2.getScore()));
                nextTurn2();

            }
        };


        /* exit button click listener, to exit from existing game back to home page using arrow icon */
        View.OnClickListener exitButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DualGamePageActivity.this, ChooseLevelActivity.class);
                startActivity(intent);
            }
        };

        btn_exit.setOnClickListener(exitButtonClickListener);


        button1.setOnClickListener(answer1ButtonClickListener);
        button2.setOnClickListener(answer1ButtonClickListener);
        button3.setOnClickListener(answer1ButtonClickListener);
        button4.setOnClickListener(answer1ButtonClickListener);
        button5.setOnClickListener(answer2ButtonClickListener);
        button6.setOnClickListener(answer2ButtonClickListener);
        button7.setOnClickListener(answer2ButtonClickListener);
        button8.setOnClickListener(answer2ButtonClickListener);
    }


    private void nextTurn1() {
        //create a new question
        // set text on answer buttons
        //enable answer button
        //start the times
        g.makeNewQuestion();
        int[] answer = g.getCurrentQuestion().getAnswerArray();

        button1.setText(Integer.toString(answer[0]));
        button2.setText(Integer.toString(answer[1]));
        button3.setText(Integer.toString(answer[2]));
        button4.setText(Integer.toString(answer[3]));

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        tv_question1.setText(g.getCurrentQuestion().getQuestionPhrase());
        tv_msg1.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));
    }

    private void nextTurn2() {
        //create a new question
        // set text on answer buttons
        //enable answer button
        //start the times
        g2.makeNewQuestion();
        int[] answer = g2.getCurrentQuestion().getAnswerArray();

        button5.setText(Integer.toString(answer[0]));
        button6.setText(Integer.toString(answer[1]));
        button7.setText(Integer.toString(answer[2]));
        button8.setText(Integer.toString(answer[3]));

        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);

        tv_question2.setText(g2.getCurrentQuestion().getQuestionPhrase());
        tv_msg2.setText(g2.getNumberCorrect() + "/" + (g2.getTotalQuestions() - 1));
    }


}