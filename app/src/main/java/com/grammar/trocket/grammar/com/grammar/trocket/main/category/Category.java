package com.grammar.trocket.grammar.com.grammar.trocket.main.category;

/**
 * Created by jamiemoreland on 15/02/16.
 * Category class to hold category information
 */
public class Category {
    String name;
    String desc;
    int icon;
    int hasDialect;
    boolean isResource;
    String id;


    /**
     * @param icon Icon for category
     * @param desc Description for Category
     * @param name Name of Category
     * @param  hasDialect Dialect  of category
     **/
    public Category(String name, String desc, int icon, int hasDialect, String id) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.hasDialect = hasDialect;
        this.isResource = false;
        this.id = id;
    }

    /**
     * @param icon Icon for category
     * @param desc Description for Category
     * @param name Name of Category
     * @param  hasDialect Dialect  of category
     * @param isResource Set true if this is a boolean, this will turn buttons on the card invisible
     **/
    public Category(String name, String desc, int icon, int hasDialect, String id, boolean isResource) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.hasDialect = hasDialect;
        this.isResource = isResource;
        this.id = id;
    }

}
