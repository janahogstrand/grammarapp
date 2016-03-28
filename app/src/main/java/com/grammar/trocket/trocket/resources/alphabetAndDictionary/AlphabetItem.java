package com.grammar.trocket.trocket.resources.alphabetAndDictionary;

import android.util.Log;

/**
 * Created by jamiemoreland on 18/03/16.
 * This class is for an Alphabet Item
 * This should contain a letter and whether
 * its a dictionary item, if so this will change on click
 * functionality
 */
public class AlphabetItem{
    private String letter;
    private boolean isDictionary;
    private int id;

    public AlphabetItem(String letter, boolean isDictionary, int id){
        this.letter = letter;
        this.isDictionary = isDictionary;
        this.id = id;
    }

    /**
     * Returns letter
     * */
    public String getLetter(){
        return letter;
    }


    /**
     * Returns true is it is a dictionary
     * */
    public boolean getIsDictionary(){
        return isDictionary;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return letter;
    }

}
