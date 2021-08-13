package com.example.mymathquiz;

import java.util.ArrayList;
import java.util.List;

// Third level: SUB of tow numbers

public class Game3 {

    private List <Question3> questions;
    private int numberCorrect;
    private int numberIncorrect;
    private int totalQuestions;
    private int score;

    private Question3 currentQuestion;

    public Game3(){
        numberCorrect=0;
        numberIncorrect=0;
        totalQuestions=0;
        score=0;
        currentQuestion = new Question3(10);
        questions = new ArrayList<Question3>();
    }

    public void makeNewQuestion(){
        currentQuestion = new Question3(totalQuestions * 2 + 5 );
        totalQuestions++;
        questions.add(currentQuestion);

    }

    public boolean checkAnswer(int submittedAnswer){
        boolean isCorrect;
        if (currentQuestion.getAnswer() == submittedAnswer){
            numberCorrect++;
            isCorrect = true;
        }
        else {
            numberIncorrect++;
            isCorrect = false;
        }
        score = numberCorrect * 10 + (numberIncorrect * (-30));
        return isCorrect;
    }



    public List<Question3> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question3> questions) {
        this.questions = questions;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question3 getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question3 currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

}
