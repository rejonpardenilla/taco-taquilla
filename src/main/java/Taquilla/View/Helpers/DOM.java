package Taquilla.View.Helpers;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DOM extends HashMap<String, Component> {

    public void add(String id, Component component){
        this.put(id, component);
    }

    public Component findById(String id){
        return this.get(id);
    }

    public ArrayList<String> getAllIds(){
        return new ArrayList<>(this.keySet());
    }
}
