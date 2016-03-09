package com.grammar.trocket.grammar.com.grammar.trocket.exercises_quiz;

/**
 * Created by jamiemoreland on 09/03/16.
 */
public class Quiz {
    private String name;
    private QuizType quizType;

    public Quiz(String name, QuizType quizType){
        this.name = name;
        this.quizType = quizType;
    }

    public String getName(){
        return name;
    }

    public QuizType getQuizType(){
        return quizType;
    }


}
