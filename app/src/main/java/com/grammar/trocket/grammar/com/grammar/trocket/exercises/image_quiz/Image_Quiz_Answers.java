package com.grammar.trocket.grammar.com.grammar.trocket.exercises.image_quiz;

/**
 * Created by firasAltayeb on 15/02/2016.
 */
public class Image_Quiz_Answers {
    Image_Quiz_Question qusListClass = new Image_Quiz_Question();
    String[] qusList = qusListClass.createArray();

    /**
     The getCorrectAnswer method returns the current question's correct answer
     by comparing the current question with the qusList array strings, if the current question
     matches a question in the qusList array the matched question's answer is returned as string */
    public String getCorrectAnswer(String Question){
        String correctAnswer = "";
        if (Question.equals(qusList[0])){
            correctAnswer = "aeroplane";
        }
        if (Question.equals(qusList[1])){
            correctAnswer = "airportseat";
        }
        if (Question.equals(qusList[2])){
            correctAnswer = "bankcard";
        }
        if (Question.equals(qusList[3])){
            correctAnswer = "bed";
        }
        if (Question.equals(qusList[4])){
            correctAnswer = "car";
        }
        if (Question.equals(qusList[5])){
            correctAnswer = "train";
        }
        return correctAnswer;
    }
}
