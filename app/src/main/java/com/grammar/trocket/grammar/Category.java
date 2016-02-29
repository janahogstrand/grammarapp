package com.grammar.trocket.grammar;

/**
 * Created by jamiemoreland on 15/02/16.
 * Category class to hold category information
 */
public class Category {
    String name;
    String desc;
    int icon;
    boolean hasDialect;
    boolean isResource;


    /**
     * @param icon Icon for category
     * @param desc Description for Category
     * @param name Name of Category
     * @param  hasDialect Dialect  of category
     **/
    Category(String name, String desc, int icon, boolean hasDialect) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.hasDialect = hasDialect;
        this.isResource = false;
    }

    /**
     * @param icon Icon for category
     * @param desc Description for Category
     * @param name Name of Category
     * @param  hasDialect Dialect  of category
     * @param isResource Set true if this is a boolean, this will turn buttons on the card invisible
     **/
    Category(String name, String desc, int icon, boolean hasDialect, boolean isResource) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.hasDialect = hasDialect;
        this.isResource = isResource;
    }

}
