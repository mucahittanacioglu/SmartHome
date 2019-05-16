/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartHome;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Co
 */
public class SmartLight extends SmartObject implements LocationControl,Programmable{
    private boolean hasLightTurned;
    private java.util.Calendar programTime;
    private boolean programAction;
    
    public SmartLight(String alias,String macId){
        setAlias(alias);
        setMaacId(macId);
    }
    
    public void turnOnLight(){
        if(isConnectionStatus()){
            if(!hasLightTurned){
            programTime = new GregorianCalendar();
            setHasLightTurned(true);
            setProgramAction(true);
            setProgramTime(new GregorianCalendar());
            System.out.println("Smart Light - "+getAlias() +" is turned on now (Current time: "+getTimeProper(new GregorianCalendar()) +")");
            }else
                System.out.println("Smart Light - "+getAlias() +" has been already turned on.");
        }else
            System.out.println(getAlias()+"is not connected yet.");
    }
    
    public void turnOffLight(){
        if(isConnectionStatus()){
            if(hasLightTurned){
                setProgramTime(new GregorianCalendar());
                setHasLightTurned(false);
                programAction=true;
                setProgramTime(new GregorianCalendar());
                System.out.println("Smart Light - "+getAlias() +" is turned off now (Current time: "+getTimeProper(new GregorianCalendar()) +")");
            }else
                System.out.println("Smart Light - "+getAlias() +" has been already turned off.");
        }else
            System.out.println(getAlias()+" not connected yet.");
    }
    
     @Override
    public boolean testObject() {
        if(isConnectionStatus()){
            System.out.println("Test is starting for SmartLight");
            System.out.println("This is SmartLight device "+this.getAlias());
            SmartObjectToString();
            turnOnLight();
            turnOffLight();
            System.out.println("Test completed for "+getAlias());
            return true;
        }else
            return false;
    }

    @Override
    public boolean shutDownObject() {
        if(isConnectionStatus()){
            SmartObjectToString();
            if(isHasLightTurned())
                setHasLightTurned(false);
            else
                System.out.println(getAlias()+" is not turned on yet.");
            return true;
        }else
            return false;
    }

    @Override
    public void onLeave() {
         if(isConnectionStatus()){
             turnOffLight();
             
             System.out.println("Smart Light - "+getAlias() +" is turned off now.(Current time: "+getTimeProper(new GregorianCalendar()) +")");
         }else
             System.out.println(getAlias()+" not connected yet.");
    
    }

    @Override
    public void onCome() {
        if(isConnectionStatus()){
             turnOnLight();
             System.out.println("Smart Light - "+getAlias() +" is turned on now.(Current time: "+getTimeProper(new GregorianCalendar()) +")");
             
        }else
             System.out.println(getAlias()+" is not connected yet.");    
    }

    @Override
    public void setTimer(int seconds) {
        if(isConnectionStatus()){
           
            programTime.add(Calendar.SECOND, seconds);
            
            if(hasLightTurned)
                System.out.println("Smart Light - "+getAlias() +" will be turned off "+seconds+" seconds later.(Current time: "+getTimeProper(new GregorianCalendar()) +")");
            else
                System.out.println("Smart Light - "+getAlias() +" will be turned on "+seconds+" seconds later.(Current time: "+getTimeProper(new GregorianCalendar()) +")");
                
        }else
             System.out.println(getAlias()+" is not connected yet.");
    
    }

    @Override
    public void cancelTimer() {
        if(isConnectionStatus()){
            programTime = null;
            programAction=false;
        }else
             System.out.println(getAlias()+" is not connected yet.");
    
    }

    @Override
    public void runProgram() {
        
        if(isConnectionStatus()){
            if(programAction){
               if(isHasLightTurned()){
                   if(getTimeProper(programTime).equalsIgnoreCase(getTimeProper(new GregorianCalendar()))){
                        //System.out.println("Smart Plug - "+getAlias() +" is turned off now.(Current time: "+getTimeProper(programTime) +")");
                        turnOffLight();
                        programTime=null;
                    }
               }else{
                    if(getTimeProper(programTime).equalsIgnoreCase(getTimeProper(new GregorianCalendar()))){
                        //System.out.println("Smart Light - "+getAlias() +" is turned on now.(Current time: "+getTimeProper(programTime) +")");
                        turnOnLight();
                        programTime=null;
                    }
               }    
            }
        }else
             System.out.println(getAlias()+" is not connected yet.");
    
    }
    
    public boolean isHasLightTurned() {
        return hasLightTurned;
    }

    public void setHasLightTurned(boolean hasLightTurned) {
        this.hasLightTurned = hasLightTurned;
    }

    public Calendar getProgramTime() {
        return programTime;
    }

    public void setProgramTime(Calendar programTime) {
        this.programTime = programTime;
    }

    public boolean isProgramAction() {
        return programAction;
    }

    public void setProgramAction(boolean programAction) {
        this.programAction = programAction;
    }
    
   
    
    public static String getTimeProper(Calendar cal){
      return cal.getTime().getHours()+":"+cal.getTime().getMinutes()+":"+cal.getTime().getSeconds();
        
    }
}
