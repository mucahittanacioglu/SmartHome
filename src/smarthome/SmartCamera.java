/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartHome;

import static SmartHome.SmartLight.getTimeProper;
import java.util.GregorianCalendar;

/**
 *
 * @author Co
 */
public class SmartCamera extends SmartObject implements MotionControl,Comparable<SmartCamera> {
    
    private boolean status;
    private int batteryLife;
    private boolean nightVision;
    
    public SmartCamera(String alias,String macId,boolean nightVision,int batteryLife){
        setAlias(alias);
        setMaacId(macId);
        this.nightVision=nightVision;
        this.batteryLife=batteryLife;
    }
    
    public void recordOn(boolean isDay){
        
        if(isConnectionStatus())
            if(isDay){
                if(!status){
                    System.out.println("Smart Camera - "+getAlias()+ " is turned on now.");
                    status = true;
                }else
                    System.out.println("Smart Camera - "+getAlias()+" has been already turned on.");
            }else{
                if(!nightVision)
                    System.out.println("Sorry! Smart Camera - "+getAlias()+ " does not have night vision feature.");
            }
                    
    }
    
    public void recordOff(){
        if(isConnectionStatus()){
            if(status){
                System.out.println("Smart Camera - "+getAlias()+" is turned off now..");
                status =false;
            }else
                System.out.println("Smart Camera - "+getAlias()+" has been already turned off.");
        }
            
    }

    @Override
    public boolean testObject() {
        if(isConnectionStatus()){
            SmartObjectToString();
            System.out.println("Test is starting for SmartCamera day time");
            recordOn(true);
            recordOff(); 
            recordOn(false);
            recordOff();
            System.out.println("Test completed for "+getAlias());
            return true;
        }else
            return false;
    }

    @Override
    public boolean shutDownObject() {
        if(isConnectionStatus()){
            SmartObjectToString();
            if(status)
                setStatus(false);
            else
                System.out.println(getAlias()+" is not turned on yet.");
            return true;
        }else
            return false;
    }

    @Override
    public boolean controlMotion(boolean hasMotion, boolean isDay) {
        if(!hasMotion){
            System.out.println("Motion not detected");
        }else{
            System.out.println("Motion detected.");
        }
        if((!isDay)&&(!isNightVision())){
            System.out.println("Sorry! Smart Camera - "+getAlias()+ " does not have night vision feature.");
            return false;
        }else{
            recordOn(isDay);
            return true;
        }
            
        
            
    }

    @Override
    public int compareTo(SmartCamera o) {
        if(this.getBatteryLife() == o.getBatteryLife())
            return 0;
        else if(this.getBatteryLife() < o.getBatteryLife())
            return -1;
        else
            return 1;
    }

    @Override
    public String toString() {
        return "SmartCamera -> " + getAlias()+ " s battery life is "+getBatteryLife()+" status is "+isStatus();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public boolean isNightVision() {
        return nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }
    
    
    
    
    
}
