package com.grammar.trocket.grammar.com.grammar.trocket.exercises;

/**
 * Created by jamiemoreland on 23/03/16.
 */
public class VideoItem {

    private String name;
    private String url;
    private String subUrl;
    private int id;

    public VideoItem(String name, String url, String subUrl, int id){
        this.name = name;
        this.url = url;
        this.subUrl = subUrl;
        this.id = id;

    }

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

    public String getSubUrl(){
        return subUrl;
    }

    public int getId(){
        return id;
    }

}
