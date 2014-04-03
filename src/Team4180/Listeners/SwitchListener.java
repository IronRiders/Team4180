/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180.Listeners;

/**
 *
 * @author ros_haremash
 */
public interface SwitchListener {
       
    /**
     * This gets called repeatedly with the Limit switch state
     * @param port 
     */
    public void switchState(int port, boolean state);
    /**
     * This gets called when the Limit switch turns on
     * @param port 
     */
    public void switchOn(int port);
    /**
     * This gets called when the Limit switch turns off
     * @param port 
     */
    public void switchOff(int port);
}
