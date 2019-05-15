/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartHome;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.Boolean;

/**
 *
 * @author Co
 */
public class SmartHome {
    private ArrayList<SmartObject> smartObjectList;

    public SmartHome() {
        smartObjectList = new ArrayList();
    }
    
    public boolean addSmartObject(SmartObject smartObject){
       smartObject.connect("10.0.0."+smartObjectList.size()+100);
        return smartObjectList.add(smartObject);
    }
    
    public boolean removeSmartObject(SmartObject smartObject){
        
        return smartObjectList.remove(smartObject);
    }
    
    public void controlLocation(boolean oncome) throws Exception{
        Object temp = null;
        for(int i = 0; i < smartObjectList.size() ; i++){
            if(smartObjectList.get(i) instanceof LocationControl){
                temp = smartObjectList.get(i).getClass().cast(smartObjectList.get(i));
       
                    Method m;
                    m = smartObjectList.get(i).getClass().getMethod(oncome ? "onCome":"onLeave");
                    m.invoke(temp);
            }        
        }
    }
    
    public void controlMotion( boolean hasMotion,boolean isDay)throws Exception{
         Object temp = null;
         Class[] parameters = new Class[2];
         parameters[0]=boolean.class;
         parameters[1]=boolean.class;
        for(int i = 0; i < smartObjectList.size() ; i++){
            if(smartObjectList.get(i) instanceof MotionControl){
                temp = smartObjectList.get(i).getClass().cast(smartObjectList.get(i));
       
                    Method m;
                    m = smartObjectList.get(i).getClass().getMethod("controlMotion",parameters);
                    m.invoke(temp,hasMotion,isDay);
            }        
        }
    }
    
    public void controlProgrammable()throws Exception{
        Object temp = null; 
        for(int i = 0; i < smartObjectList.size() ; i++){
            if(smartObjectList.get(i) instanceof Programmable){
                temp = smartObjectList.get(i).getClass().cast(smartObjectList.get(i));
       
                    Method m;
                    m = smartObjectList.get(i).getClass().getMethod("runProgram");
                    m.invoke(temp);
            }        
        }
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
