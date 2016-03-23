package com.grammar.trocket.grammar.com.grammar.trocket.main.category;

import android.util.Log;

/**
 * Created by jamiemoreland on 23/03/16.
 */
public class CardButton implements Comparable<CardButton> {
    private String name;
    private int order;

    public CardButton(String name, int order){
        this.name = name;
        this.order = order;
    }

    public int getOrder(){
        return order;
    }

    public String getName(){
        return name;
    }

    @Override
    public int compareTo(CardButton another) {
        int compareOrder = ((CardButton) another).getOrder();

        //ascending order
        Log.w("Ordering.. ", this.name + "compared to " + another.name);
        return this.order - compareOrder;
    }
}
