package com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class ModuleItem {

    String moduleName;
    int id;

    public ModuleItem(String moduleName, int id){
        this.moduleName = moduleName;
        this.id = id;
    }


    public String getModuleName(){
        return moduleName;
    }

    public int getModuleId() {
        return id;
    }

}
