package Taquilla.View.Helpers;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class DOM extends HashMap<String, Component> {

    public void add(String id, Component component){
        this.put(id, component);
    }

    public Component findById(String id){
        return this.get(id);
    }

    public Object invokeMethodOn(String id, String methodName){
        java.lang.reflect.Method method;
        try{
            method = this.get(id).getClass().getMethod(methodName);
            return method.invoke(this.get(id));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object invokeMethodOn(String id, String methodName, Object... args){
        java.lang.reflect.Method method;
        try{
            Class<?>[] parameterTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++){
                parameterTypes[i] = args[i].getClass();
            }
            method = this.get(id).getClass().getMethod(methodName, parameterTypes);
            return method.invoke(this.get(id), args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getAllIds(){
        return new ArrayList<>(this.keySet());
    }

}
