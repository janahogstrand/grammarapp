package com.grammar.trocket.trocket.main.module_selection;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class ModuleItem {
    String moduleName;
    String language;
    int id;
    //ArrayList<DialectItem> dialects;

//    public ModuleItem(String moduleName, String language, int id, ArrayList<DialectItem> dialects){
//        this.moduleName = moduleName;
//        this.language = language;
//        this.id = id;
//        this.dialects = dialects;
//    }

    public ModuleItem(String moduleName, String language, int id){
        this.moduleName = moduleName;
        this.language = language;
        this.id = id;
        //dialects = null;
    }

    public String getModuleName(){
        return moduleName;
    }
    public int getModuleId(){
        return id;
    }

//    public ArrayList<DialectItem> getDialects(){
//        return dialects;
//    }
//
//    public void addDialect(ArrayList<DialectItem> dialects){
//        this.dialects = dialects;
//    }

}
