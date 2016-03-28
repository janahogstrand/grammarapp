package com.grammar.trocket.trocket.resources.seasons;

/**
 * Created by ran on 24/03/16.
 */
public class ClusterItem {

    String id;
    String clusterId;
    String name;
    String hierarchy;

    public ClusterItem(String id, String clusterId, String name, String hierarchy) {
        this.id = id;
        this.clusterId = clusterId;
        this.name = name;
        this.hierarchy = hierarchy;
    }

    public String getId() {
        return id;
    }

    public String getClusterId() {
        return clusterId;
    }

    public String getName() {
        return name;
    }

    public String getHierarchy() {
        return hierarchy;
    }


}
