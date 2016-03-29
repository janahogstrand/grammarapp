package com.grammar.trocket.grammingo.resources.seasons;

/**
 * Created by ran on 24/03/16.
 */
public class ClusterSubItem {

    String id;
    String clusterItemId;
    String name;
    String description;
    String clickable;
    String audioUrl;
    String hierarchy;

    public ClusterSubItem(String id, String clusterItemId, String name, String description, String clickable, String audioUrl, String hierarchy) {
        this.id = id;
        this.clusterItemId = clusterItemId;
        this.name = name;
        this.description = description;
        this.clickable = clickable;
        this.audioUrl = audioUrl;
        this.hierarchy = hierarchy;
    }

    public String getId() {
        return id;
    }

    public String getClusterItemId() {
        return clusterItemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getClickable() {
        return clickable;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getHierarchy() {
        return hierarchy;
    }



}
