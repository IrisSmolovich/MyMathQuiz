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

public class GamePageActivity extends AppCompatActivity {
    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    TextView tv_timer, tv_score, tv_questions, tv_bottommsg, tv_timeleft, tv_correctansewrs, tv_totalscore;
    ProgressBar prog_bar;
    ImageView btn_exit;

    Game1 g = new Game1();

    int secondsRemaining = 30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining) + getString(R.string.sec));
            prog_bar.setProgress(30 - secondsRemaining);

        }

        @Override
        public void onFinish() {
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            tv_bottommsg.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));
            tv_questions.setText(getString(R.string.time_up) );

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_start.setVisibility(View.VISIBLE);
                    }
            }, 1000);

        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        btn_start = findViewById(R.id.btn_3start);
        btn_answer0 = findViewById(R.id.btn_3answer0);
        btn_answer1 = findViewById(R.id.btn_3answer1);
        btn_answer2 = findViewById(R.id.btn_3answer2);
        btn_answer3 = findViewById(R.id.btn_3answer3);

        tv_score = findViewById(R.id.tv_3score);
        tv_bottommsg = findViewById(R.id.tv_3bottommsg);
        tv_questions = findViewById(R.id.tv_3questions);
        tv_timer = findViewById(R.id.tv_3timer);
        prog_bar = findViewById(R.id.prog_3bar);

        tv_correctansewrs = findViewById(R.id.tv_correct_total);
        tv_timeleft = findViewById(R.id.tv_timeleft);
        tv_totalscore = findViewById(R.id.tv_scoreinfo);

        btn_exit = findViewById(R.id.btn_3exit3);

        tv_timer.setText(getString(R.string.no_sec));
        tv_questions.setText("");
        tv_bottommsg.setText("");
        tv_score.setText(getString(R.string.no_pts));
        prog_bar.setProgress(0);

        /* exit button click listener, to exit from existing game back to home page using arrow icon */
        View.OnClickListener exitButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamePageActivity.this, ChooseLevelActivity.class);
                startActivity(intent);
            }
        };

        btn_exit.setOnClickListener(exitButtonClickListener);

        View.OnClickListener startButtonClickListener = (v) -> {
            Button start_button = (Button) v;

            start_button.setVisibility(View.INVISIBLE);
            secondsRemaining = 30;
            g = new Game1();
            nextTurn();
            timer.start();

        };

        btn_start.setOnClickListener(startButtonClickListener);

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                //               Toast.makeText(GamePageActivity.this, "AnswerSelected=" + answerSelected, Toast.LENGTH_SHORT).show();
                g.checkAnswer(answerSelected);
                tv_score.setText(Integer.toString(g.getScore()));
                nextTurn();

            }
        };

        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);
    }

    private void nextTurn() {
        //create a new question
        // set text on answer buttons
        //enable answer button
        //start the times
        g.makeNewQuestion();
        int[] answer = g.getCurrentQuestion().getAnswerArray();

        btn_answer0.setText(Integer.toString(answer[0]));
        btn_answer1.setText(Integer.toString(answer[1]));
        btn_answer2.setText(Integer.toString(answer[2]));
        btn_answer3.setText(Integer.toString(answer[3]));

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);

        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());
        tv_bottommsg.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));

    }

}