package com.example.teerachel.imagequizactivity;

/**
 * Created by teerachel on 07/03/2016.
 */
public class Image_Quiz_Ans {

    Image_Quiz_Ques quesClass = new Image_Quiz_Ques();
    String[] quesList = quesClass.createArray();

    /**
     * The getCorrectAnswer method returns the current question's correct answer
     * by comparing the current question with the qusList array strings, if the current question
     * matches a question in the qusList array the matched question's answer is returned as string
     */
    public  String getCorrectAnswer(String Question) {
        String correctAnswer = "";
        if (Question.equals(quesList[0])) {
            correctAnswer = "Tren" ;
        }
        if (Question.equals(quesList[1])) {
            correctAnswer = "Avion";
        }
        if (Question.equals(quesList[2])) {
            correctAnswer = "Coche";
        }
        if (Question.equals(quesList[3])) {
            correctAnswer = "Autobus";
        }
        if (Question.equals(quesList[4])) {
            correctAnswer = "Barco";
        }
        if (Question.equals(quesList[5])) {
            correctAnswer = "Bicicleta";
        }
        return correctAnswer;
    }

    /**
     * The getOptions method returns the current question's options
     * by comparing the current question with the qusList array strings, if the current question
     * matches a question in the qusList array the matched question's option is returned as an array
     */
    public String[] getAnswerOptions(String Question) {
        String ans1 = "";
        String ans2 = "";
        String ans3 = "";
        String ans4 = "";
        String ans5 = "";
        String ans6 = "";

        String[] options = new String[6];
        if (Question.equals(quesList[0])) {
            ans1 = "Tren";
            ans2 = "Avion";
            ans3 = "Coche";
            ans4 = "Autobus";
            ans5 = "Barco";
            ans6 = "Bicicleta";
        }
        if (Question.equals(quesList[1])) {
            ans1 = "Tren";
            ans2 = "Avion";
            ans3 = "Coche";
            ans4 = "Autobus";
            ans5 = "Barco";
            ans6 = "Bicicleta";
        }
        if (Question.equals(quesList[2])) {
            ans1 = "Tren";
            ans2 = "Avion";
            ans3 = "Coche";
            ans4 = "Autobus";
            ans5 = "Barco";
            ans6 = "Bicicleta";
        }
        if (Question.equals(quesList[3])) {
            ans1 = "Tren";
            ans2 = "Avion";
            ans3 = "Coche";
            ans4 = "Autobus";
            ans5 = "Barco";
            ans6 = "Bicicleta";
        }
        if (Question.equals(quesList[4])) {
            ans1 = "Tren";
            ans2 = "Avion";
            ans3 = "Coche";
            ans4 = "Autobus";
            ans5 = "Barco";
            ans6 = "Bicicleta";
        }
        if (Question.equals(quesList[5])) {
            ans1 = "Tren";
            ans2 = "Avion";
            ans3 = "Coche";
            ans4 = "Autobus";
            ans5 = "Barco";
            ans6 = "Bicicleta";
        }


        options[0] = ans1;
        options[1] = ans2;
        options[2] = ans3;
        options[3] = ans4;
        options[4] = ans5;
        options[5] = ans6;

        return options;
    }
}