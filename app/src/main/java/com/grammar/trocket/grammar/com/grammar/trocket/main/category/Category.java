package com.grammar.trocket.grammar.com.grammar.trocket.main.category;

import android.util.Log;

/**
 * Created by jamiemoreland on 15/02/16.
 * Category class to hold category information
 */
public class Category implements Comparable<Category> {
    String name;
    String desc;
    int icon;
    boolean hasDialect;
    boolean isResource;
    int id;
    private int order;


    /**
     * @param icon Icon for category
     * @param desc Description for Category
     * @param name Name of Category
     * @param  hasDialect Dialect  of category
     **/
    public Category(String name, String desc, int icon, boolean hasDialect, int id, int order) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.hasDialect = hasDialect;
        this.isResource = false;
        this.id = id;
        this.order = order;
    }

    /**
     * @param icon Icon for category
     * @param desc Description for Category
     * @param name Name of Category
     * @param  hasDialect Dialect  of category
     * @param isResource Set true if this is a boolean, this will turn buttons on the card invisible
     **/
    public Category(String name, String desc, int icon, boolean hasDialect, boolean isResource, int id, int order) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.hasDialect = hasDialect;
        this.isResource = isResource;
        this.id = id;
        this.order = order;
    }

    public int getOrder(){
        return order;
    }

    public int compareTo(Category compare) {

        int compareOrder = ((Category) compare).getOrder();

        //ascending order
        Log.w("Ordering..", this.name + "compared" + compare);
        return this.order - compareOrder;
    }

}
