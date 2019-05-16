/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartHome;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.Boolean;
import java.util.Random;

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
        Method m;
        for(int i = 0; i < smartObjectList.size() ; i++){
            if(smartObjectList.get(i) instanceof LocationControl){
                temp = smartObjectList.get(i).getClass().cast(smartObjectList.get(i));
       
                    
                    m = smartObjectList.get(i).getClass().getMethod(oncome ? "onCome":"onLeave");
                    m.invoke(temp);
            }        
        }
    }
    
    public void controlMotion( boolean hasMotion,boolean isDay)throws Exception{
         Object temp = null;
         Method m;
         Class[] parameters = new Class[2];
         parameters[0]=boolean.class;
         parameters[1]=boolean.class;
        for(int i = 0; i < smartObjectList.size() ; i++){
            if(smartObjectList.get(i) instanceof MotionControl){
                temp = smartObjectList.get(i).getClass().cast(smartObjectList.get(i));
       
                   
                    m = smartObjectList.get(i).getClass().getMethod("controlMotion",parameters);
                    m.invoke(temp,hasMotion,isDay);
            }        
        }
    }
    
    public void controlProgrammable()throws Exception{
        Method m;
        Object temp = null; 
        for(int i = 0; i < smartObjectList.size() ; i++){
            if(smartObjectList.get(i) instanceof Programmable){
                temp = smartObjectList.get(i).getClass().cast(smartObjectList.get(i));
       
                   
                    m = smartObjectList.get(i).getClass().getMethod("runProgram");
                    m.invoke(temp);
            }        
        }
    }
    
    public void controlTimer(int seconds)throws Exception{
        Method m; 
        Object temp = null; 
        Class[] parameter = {int.class};
        
        for(int i = 0; i < smartObjectList.size() ; i++){
            if(smartObjectList.get(i) instanceof Programmable){
                temp = smartObjectList.get(i).getClass().cast(smartObjectList.get(i));
       
                    
                    if(seconds==0){
                        m = smartObjectList.get(i).getClass().getMethod("cancelTimer");
                        m.invoke(temp);
                    }else{
                        m = smartObjectList.get(i).getClass().getMethod("setTimer",parameter);
                        m.invoke(temp,seconds);
                    }
            }        
        }
    }
    
    
    public void controlTimerRandomly()throws Exception{
        Method m;
        int[] rndVal= {0,10,5};
        int second ;
        Object temp = null; 
        Class[] parameter = {int.class};
        for(int i = 0; i < smartObjectList.size() ; i++){
            if(smartObjectList.get(i) instanceof Programmable){
                temp = smartObjectList.get(i).getClass().cast(smartObjectList.get(i));
                    second = rndVal[new Random().nextInt(3)];
                    
                    if(second==0){
                        m = smartObjectList.get(i).getClass().getMethod("cancelTimer");
                        m.invoke(temp);
                    }else{
                    m = smartObjectList.get(i).getClass().getMethod("setTimer",parameter);
                    m.invoke(temp,second);
                    }
            }        
        }
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
