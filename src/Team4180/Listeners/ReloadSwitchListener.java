/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180.Listeners;

import Team4180.Claw;

/**
 *
 * @author ros_haremash
 */
public class ReloadSwitchListener implements SwitchListener {
    
    private int reloadPort;
    private Claw claw;
    
    public ReloadSwitchListener(Claw claw, int reloadPort) {
        this.claw = claw;
        this.reloadPort = reloadPort;
    }
    
    public void switchOn (int port) {
        if(port == reloadPort){
            claw.stopReload();
        }
    }
    
    public void switchOff (int port) {
    }
    
    public void switchState(int port, boolean state) {
    }
}
