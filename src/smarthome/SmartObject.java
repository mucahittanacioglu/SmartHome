/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartHome;

/**
 Mucahit Tanacıoglu
 * 150115006
 */
public abstract class SmartObject {
    private String alias;
    private String maacId;
    private String IP;
    private boolean connectionStatus;
    
    public SmartObject(){
        
    }
    public boolean connect(String IP){
        this.IP=IP;
        System.out.print(alias+" connection established.");
        return this.connectionStatus=true;
    }
    
    public boolean disconnect(){
        this.IP = "";
        return !(this.connectionStatus=false);
    }
    public void SmartObjectToString(){
        System.out.println("This is "+this+" device "+this.alias);
        System.out.println("MacId : "+this.maacId+"/nIP : "+this.IP);
        
    }
    public boolean controlConnection(){
        if(this.connectionStatus)
            return this.connectionStatus;
        else{
            System.out.println("This device is not connected. "+this+" -> "+this.alias);
            return this.connectionStatus;
                    
        }
    }
    
    public abstract boolean testObject();
    
    public abstract boolean shutDownObject();

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMaacId() {
        return maacId;
    }

    public void setMaacId(String maacId) {
        this.maacId = maacId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }
    
    
    
}
