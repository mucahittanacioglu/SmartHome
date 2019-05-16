/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SmartHome;

/**
 *
 * @author Co
 */
public interface Programmable {
    
    public void setTimer(int seconds);
    public void cancelTimer();
    public void runProgram();
}
