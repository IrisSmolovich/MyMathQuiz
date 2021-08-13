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

public class GamePage3Activity extends AppCompatActivity {
    Button btn_3start, btn_3answer0, btn_3answer1, btn_3answer2, btn_3answer3;
    TextView tv_3timer, tv_3score, tv_3questions, tv_3bottommsg, tv_timeleft, tv_correctansewrs, tv_totalscore;;
    ProgressBar prog_3bar;
    ImageView btn_3exit;

    Game3 g = new Game3();

    int secondsRemaining = 30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            tv_3timer.setText(Integer.toString(secondsRemaining) + getString(R.string.sec));
            prog_3bar.setProgress(30 - secondsRemaining);

        }

        @Override
        public void onFinish() {
            btn_3answer0.setEnabled(false);
            btn_3answer1.setEnabled(false);
            btn_3answer2.setEnabled(false);
            btn_3answer3.setEnabled(false);
            tv_3bottommsg.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));
            tv_3questions.setText(getString(R.string.time_up) );

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_3start.setVisibility(View.VISIBLE);
                }
            }, 2000);

        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page3);

        btn_3start = findViewById(R.id.btn_3start);
        btn_3answer0 = findViewById(R.id.btn_3answer0);
        btn_3answer1 = findViewById(R.id.btn_3answer1);
        btn_3answer2 = findViewById(R.id.btn_3answer2);
        btn_3answer3 = findViewById(R.id.btn_3answer3);

        tv_correctansewrs = findViewById(R.id.tv_correct_total3);
        tv_timeleft = findViewById(R.id.tv_timeleft3);
        tv_totalscore = findViewById(R.id.tv_scoreinfo3);

        tv_3score = findViewById(R.id.tv_3score);
        tv_3bottommsg = findViewById(R.id.tv_3bottommsg);
        tv_3questions = findViewById(R.id.tv_3questions);
        tv_3timer = findViewById(R.id.tv_3timer);
        prog_3bar = findViewById(R.id.prog_3bar);

        btn_3exit = findViewById(R.id.btn_3exit3);

        tv_3timer.setText(getString(R.string.no_sec));
        tv_3questions.setText("");
        tv_3bottommsg.setText("");
        tv_3score.setText(getString(R.string.no_pts));
        prog_3bar.setProgress(0);

        /* exit button click listener, to exit from existing game back to home page using arrow icon */
        View.OnClickListener exitButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamePage3Activity.this, ChooseLevelActivity.class);
                startActivity(intent);
            }
        } ;

        btn_3exit.setOnClickListener(exitButtonClickListener);

        View.OnClickListener startButtonClickListener = (v) -> {
            Button start_button = (Button) v;

            start_button.setVisibility(View.INVISIBLE);
            secondsRemaining = 30;
            g = new Game3();
            nextTurn();
            timer.start();

        };

        btn_3start.setOnClickListener(startButtonClickListener);

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                //               Toast.makeText(GamePageActivity.this, "AnswerSelected=" + answerSelected, Toast.LENGTH_SHORT).show();
                g.checkAnswer(answerSelected);
                tv_3score.setText(Integer.toString(g.getScore()));
                nextTurn();

            }
        };

        btn_3answer0.setOnClickListener(answerButtonClickListener);
        btn_3answer1.setOnClickListener(answerButtonClickListener);
        btn_3answer2.setOnClickListener(answerButtonClickListener);
        btn_3answer3.setOnClickListener(answerButtonClickListener);
    }

    private void nextTurn(){
        //create a new question
        // set text on answer buttons
        //enable answer button
        //start the times
        g.makeNewQuestion();
        int [] answer = g.getCurrentQuestion().getAnswerArray();

        btn_3answer0.setText(Integer.toString(answer[0]));
        btn_3answer1.setText(Integer.toString(answer[1]));
        btn_3answer2.setText(Integer.toString(answer[2]));
        btn_3answer3.setText(Integer.toString(answer[3]));

        btn_3answer0.setEnabled(true);
        btn_3answer1.setEnabled(true);
        btn_3answer2.setEnabled(true);
        btn_3answer3.setEnabled(true);

        tv_3questions.setText(g.getCurrentQuestion().getQuestionPhrase());
        tv_3bottommsg.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));

    }
}