package com.grammar.trocket.grammar.com.grammar.trocket.resources.seasons;

/**
 * Created by ran on 24/03/16.
 */
public class Cluster {

    String id;
    String dialectId;
    String categoryId;
    String title;
    String instruction;
    String help;

    public Cluster(String id, String dialectId, String categoryId, String title, String instruction, String help) {
        this.id = id;
        this.dialectId = dialectId;
        this.categoryId = categoryId;
        this.title = title;
        this.instruction = instruction;
        this.help = help;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getDialectId() {
        return dialectId;
    }

    public String getTitle() {
        return title;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getHelp() {
        return help;
    }


}
