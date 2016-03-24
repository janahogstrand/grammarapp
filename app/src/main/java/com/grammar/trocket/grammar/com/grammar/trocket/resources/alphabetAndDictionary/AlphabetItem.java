package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary;

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

    public AlphabetItem(String letter, boolean isDictionary){
        this.letter = letter;
        this.isDictionary = isDictionary;
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

    public String toString(){
        return letter;
    }

}
