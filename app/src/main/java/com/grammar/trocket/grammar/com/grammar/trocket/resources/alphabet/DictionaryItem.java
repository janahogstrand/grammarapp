package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabet;

/**
 * Created by jamiemoreland on 19/03/16.
 * A word
 * Holds the foreign and english version of the word
 * to be used by a card view
 */
public class DictionaryItem {
    private String foreignWord;
    private String englishWord;
    public DictionaryItem(String foreignWord, String englishWord){
        this.foreignWord = foreignWord;
        this.englishWord = englishWord;
    }

    /*
    * Gets foreign word
    * **/
    public String getForeignWord(){
        return foreignWord;
    }

    /*
    * Gets english word
    * **/
    public String getEnglishWord(){
        return englishWord;
    }
}
