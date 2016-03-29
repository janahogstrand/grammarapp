package com.grammar.trocket.grammingo.main.category;

import android.util.Log;

/**
 * Created by jamiemoreland on 23/03/16.
 */
public class CardButton implements Comparable<CardButton> {
    private String name;
    private int order;
    private int id;
    private int contentId;

    public CardButton(String name, int order, int id, int contentId){
        this.name = name;
        this.order = order;
        this.id = id;
        this.contentId = contentId;
    }

    public int getOrder(){
        return order;
    }

    public String getName(){
        return name;
    }

    public int getId() {
        return id;
    }

    public int getContentId() {
        return contentId;
    }

    @Override
    public int compareTo(CardButton another) {
        int compareOrder = ((CardButton) another).getOrder();

        //ascending order
        Log.w("Ordering.. ", this.name + "compared to " + another.name);
        return this.order - compareOrder;
    }
}
