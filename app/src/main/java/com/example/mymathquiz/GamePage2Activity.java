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

public class GamePage2Activity extends AppCompatActivity {
    Button btn_2start, btn_2answer0, btn_2answer1, btn_2answer2, btn_2answer3;
    TextView tv_2timer, tv_2score, tv_2questions, tv_2bottommsg,tv_timeleft, tv_correctansewrs, tv_totalscore;;
    ProgressBar prog_2bar;
    ImageView btn_2exit;

    Game2 g = new Game2();

    int secondsRemaining = 30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            tv_2timer.setText(Integer.toString(secondsRemaining) + getString(R.string.sec));
            prog_2bar.setProgress(30 - secondsRemaining);

        }

        @Override
        public void onFinish() {
            btn_2answer0.setEnabled(false);
            btn_2answer1.setEnabled(false);
            btn_2answer2.setEnabled(false);
            btn_2answer3.setEnabled(false);
            tv_2bottommsg.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));
            tv_2questions.setText(getString(R.string.time_up) );
            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_2start.setVisibility(View.VISIBLE);
                }
            }, 2000);

        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page2);

        btn_2start = findViewById(R.id.btn_3start);
        btn_2answer0 = findViewById(R.id.btn_2answer0);
        btn_2answer1 = findViewById(R.id.btn_3answer1);
        btn_2answer2 = findViewById(R.id.btn_3answer2);
        btn_2answer3 = findViewById(R.id.btn_3answer3);


        tv_correctansewrs = findViewById(R.id.tv_correct_total2);
        tv_timeleft = findViewById(R.id.tv_timeleft2);
        tv_totalscore = findViewById(R.id.tv_scoreinfo2);

        tv_2score = findViewById(R.id.tv_3score);
        tv_2bottommsg = findViewById(R.id.tv_3bottommsg);
        tv_2questions = findViewById(R.id.tv_3questions);
        tv_2timer = findViewById(R.id.tv_3timer);
        prog_2bar = findViewById(R.id.prog_3bar);

        btn_2exit = findViewById(R.id.btn_3exit3);

        tv_2timer.setText(getString(R.string.no_sec));
        tv_2questions.setText("");
        tv_2bottommsg.setText("");
        tv_2score.setText(getString(R.string.no_pts));
        prog_2bar.setProgress(0);

        /* exit button click listener, to exit from existing game back to home page using arrow icon */
        View.OnClickListener exitButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamePage2Activity.this, ChooseLevelActivity.class);
                startActivity(intent);
            }
        } ;

        btn_2exit.setOnClickListener(exitButtonClickListener);

        View.OnClickListener startButtonClickListener = (v) -> {
            Button start_button = (Button) v;

            start_button.setVisibility(View.INVISIBLE);
            secondsRemaining = 30;
            g = new Game2();
            nextTurn();
            timer.start();

        };

        btn_2start.setOnClickListener(startButtonClickListener);

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                //               Toast.makeText(GamePageActivity.this, "AnswerSelected=" + answerSelected, Toast.LENGTH_SHORT).show();
                g.checkAnswer(answerSelected);
                tv_2score.setText(Integer.toString(g.getScore()));
                nextTurn();

            }
        };

        btn_2answer0.setOnClickListener(answerButtonClickListener);
        btn_2answer1.setOnClickListener(answerButtonClickListener);
        btn_2answer2.setOnClickListener(answerButtonClickListener);
        btn_2answer3.setOnClickListener(answerButtonClickListener);
    }

    private void nextTurn(){
        //create a new question
        // set text on answer buttons
        //enable answer button
        //start the times
        g.makeNewQuestion();
        int [] answer = g.getCurrentQuestion().getAnswerArray();

        btn_2answer0.setText(Integer.toString(answer[0]));
        btn_2answer1.setText(Integer.toString(answer[1]));
        btn_2answer2.setText(Integer.toString(answer[2]));
        btn_2answer3.setText(Integer.toString(answer[3]));

        btn_2answer0.setEnabled(true);
        btn_2answer1.setEnabled(true);
        btn_2answer2.setEnabled(true);
        btn_2answer3.setEnabled(true);

        tv_2questions.setText(g.getCurrentQuestion().getQuestionPhrase());
        tv_2bottommsg.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));

    }}