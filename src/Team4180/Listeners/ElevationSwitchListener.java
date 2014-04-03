/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180.Listeners;

import Team4180.Claw;

/**
 *
 * @author ros_aljacobson
 */
public class ElevationSwitchListener implements SwitchListener {
    
    private int elevationSwitchPort;
    private Claw claw;
    
    public ElevationSwitchListener(Claw claw, int elevationPort) {
        this.claw = claw;
        this.elevationSwitchPort = elevationPort;
    }
    
    public void switchOn (int port) {
    }
    
    public void switchOff (int port) {
    }
    
    public void switchState(int port, boolean state) {
       if(port == elevationSwitchPort && state)
        {
            claw.stopElevation();
        }
    }
}
