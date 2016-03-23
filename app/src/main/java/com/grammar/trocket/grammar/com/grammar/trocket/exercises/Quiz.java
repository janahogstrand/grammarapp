package com.grammar.trocket.grammar.com.grammar.trocket.exercises;

/**
 * Created by jamiemoreland on 09/03/16.
 */
public class Quiz {
    private String name;
    private String quizType;

    public Quiz(String name, String quizType){
        this.name = name;
        this.quizType = quizType;
    }

    public String getName(){
        return name;
    }

    public String getQuizType(){
        return quizType;
    }


}