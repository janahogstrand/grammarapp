package com.grammar.trocket.trocket.exercises;

/**
 * Created by firasAltayeb on 24/03/2016.
 */
public class Question {
    private String name;
    private int id;

    public Question(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

}
