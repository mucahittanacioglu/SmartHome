/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartHome;

import static SmartHome.SmartLight.getTimeProper;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Co
 */
public class SmartPlug extends SmartObject implements Programmable{
    
    private boolean status;
    private java.util.Calendar programTime;
    private boolean programAction;
    
    public SmartPlug(String alias,String macId){
        setAlias(alias);
        setMaacId(macId);
    }
    
    public void turnOn(){
        if(isConnectionStatus()){
            if(!isStatus()){
            System.out.println("Smart Plug - "+getAlias() +" is turned on now.(Current time: "+getTimeProper(programTime) +")");
                setStatus(true);
                setProgramAction(true);
                setProgramTime(new GregorianCalendar());
            }else
                System.out.println("Smart Plug -"+getAlias()+ " has been already turned on.");
        }else{
            System.out.println("Smart Plug - "+getAlias() +" is not connected yet.");
        }    
            
    }
    
    public void turnOff(){
        if(isConnectionStatus()){
            if(isStatus()){
                System.out.println("Smart Plug - "+getAlias() +" is turned off now.(Current time: "+getTimeProper(programTime) +")");
                setStatus(false);
                setProgramAction(true);
                setProgramTime(new GregorianCalendar());
            }else
                System.out.println("Smart Plug -"+getAlias()+ " has been already turned off.");
        }else{
            System.out.println("Smart Plug - "+getAlias() +" is not connected yet.");
        } 
    }
    
      @Override
    public boolean testObject() {
        if(isConnectionStatus()){
            SmartObjectToString();
            turnOn();
            turnOff();
            System.out.println("Test completed for "+getAlias());
            return true;
        }else
            return false;
    }

    @Override
    public boolean shutDownObject() {
         if(isConnectionStatus()){
            SmartObjectToString();
            if(isStatus())
                setStatus(false);
            return true;
        }else
            return false;
    }
    
    @Override
    public void setTimer(int seconds) {
        if(isConnectionStatus()){
            programTime = new GregorianCalendar();
            programTime.add(Calendar.SECOND, seconds);
            programAction=true;
            if(isStatus())
                System.out.println("Smart Plug - "+getAlias() +" will be turned off "+seconds+" seconds later.(Current time: "+getTimeProper(new GregorianCalendar()) +")");
            else
                System.out.println("Smart Plug - "+getAlias() +" will be turned on "+seconds+" seconds later.(Current time: "+getTimeProper(new GregorianCalendar()) +")");
                
        }else
             System.out.println(getAlias()+" is not connected yet.");
    }

    @Override
    public void cancelTimer() {
        if(isStatus()){
            programTime = null;
            programAction = false;
        }else
             System.out.println(getAlias()+" is not connected yet.");
    }

    @Override
    public void runProgram() {
       if(isConnectionStatus()){
            if(programAction){
                 if(isStatus()){
                   if(getTimeProper(programTime).equalsIgnoreCase(getTimeProper(new GregorianCalendar()))){
                        //System.out.println("Smart Plug - "+getAlias() +" is turned off now.(Current time: "+getTimeProper(programTime) +")");
                        turnOff();
                        cancelTimer();
                    }
               }else{
                    if(getTimeProper(programTime).equalsIgnoreCase(getTimeProper(new GregorianCalendar()))){
                        //System.out.println("Smart Light - "+getAlias() +" is turned on now.(Current time: "+getTimeProper(programTime) +")");
                        turnOn();
                        cancelTimer();
                    }
               }    
            }
        }else
             System.out.println(getAlias()+" is not connected yet.");
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Calendar getProgramTime() {
        return programTime;
    }

    public void setProgramTime(Calendar Calendar) {
        this.programTime = Calendar;
    }

    public boolean isProgramAction() {
        return programAction;
    }

    public void setProgramAction(boolean programAction) {
        this.programAction = programAction;
    }

  
    
    
    
    
    
    
}
