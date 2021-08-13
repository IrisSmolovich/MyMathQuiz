package com.example.mymathquiz;

import java.util.Random;

// Third level: multi-use of SUN and SUB in one exercise

public class Question2 {
    private int firstNumber;
    private int secondNumber;
    private int thirdNumber;
    //answer for first + second - third
    private int answer;
    private int [] answerArray;

    //which one of the buttons has the correct answer
    private int answerPosition;
    //max value for first and second numbers
    private int upperLimit;

    //String output like "3 + 5 - 1 = "
    private String questionPhrase;

    //generate new random number
    public Question2(int upperLimit){

        this.upperLimit = upperLimit;
        Random randomNumberMaker = new Random();

        this.firstNumber = randomNumberMaker.nextInt(upperLimit);
        this.secondNumber = randomNumberMaker.nextInt(upperLimit);
        this.thirdNumber = randomNumberMaker.nextInt(upperLimit);
        this.answer = this.firstNumber + this.secondNumber - this.thirdNumber;
        this.questionPhrase = firstNumber + " + " + secondNumber + " - " + thirdNumber + " = ";

        this.answerPosition = randomNumberMaker.nextInt(4);
        this.answerArray = new int [] {0,1,2,3};

        this.answerArray[0] = answer+10;
        this.answerArray[1] = answer-1;
        this.answerArray[2] = answer+3;
        this.answerArray[3] = answer-5;

        this.answerArray = shuffleArray(this.answerArray);
        answerArray[answerPosition] = answer;

    }

    private int [] shuffleArray(int[] array){
        int i, index, temp;
        Random randomNumberGenerator = new Random();

        for(i = array.length-1; i > 0; i--)
        {
            index = randomNumberGenerator.nextInt(i+1);
            temp = array[index];
            array[index] = array[i];
            array[i]=temp;
        }
        return array;

    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(int thirdNumber) { this.thirdNumber = thirdNumber; }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
}
