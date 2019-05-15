/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartHome;

import java.util.ArrayList;

/**
 *
 * @author Co
 */
public class SmartHome {
    private ArrayList<SmartObject> smartObjectList;

    public SmartHome() {
    }
    
    public boolean addSmartObject(SmartObject smartObject){
       return smartObjectList.add(smartObject);
    }
    
    public boolean removeSmartObject(SmartObject smartObject){
        return smartObjectList.remove(smartObject);
    }
    
    public void controlLocation(boolean oncome){
        
    }
    
    public void controlMotion( boolean hasMotion,boolean isDay){
        
    }
    
    public void controlProgrammable(){
        
    }
    
    public void controlTimer(int seconds){
        
    }
    
    public void controlTimerRandomly(){
        
    }
    
    public void sortCameras(){
        
    }

    public ArrayList<SmartObject> getSmartObjectList() {
        return smartObjectList;
    }

    public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
        this.smartObjectList = smartObjectList;
    }
    
    
}
