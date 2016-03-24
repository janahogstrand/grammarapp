package com.grammar.trocket.grammar.com.grammar.trocket.exercises;

/**
 * Created by jamiemoreland on 09/03/16.
 */
public class Quiz {
    private String name;
    private String quizType;
    private int id;

    public Quiz(String name, String quizType, int id){
        this.name = name;
        this.quizType = quizType;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getQuizType(){
        return quizType;
    }


}
