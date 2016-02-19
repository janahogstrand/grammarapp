package com.grammar.trocket.grammar;

/**
 * Created by jamiemoreland on 15/02/16.
 * Category class to hold category information
 */
public class Category {
    String name;
    String desc;
    int icon;

    /**
     * @param icon Icon for category
     * @param desc Description for Category
     * @param name Name of Category
     *
     * **/
    Category(String name, String desc, int icon){
        this.name = name;
        this.desc = desc;
        this.icon = icon;
    }
}
