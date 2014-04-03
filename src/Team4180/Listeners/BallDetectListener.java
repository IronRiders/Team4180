/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180.Listeners;

import Team4180.Claw;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author ros_haremash
 */
public class BallDetectListener implements SwitchListener {
    private Claw claw;
    private int ballDetectPort;
    private boolean isLoaded = false;
    
    public BallDetectListener(Claw claw, int ballDetectPort) {
        this.claw = claw;
        this.ballDetectPort = ballDetectPort;
    }
    
    public void switchOn (int port) {
        System.out.println("The Switch is on.");
    }
    
    public void switchOff (int port) {
    }

    public void switchState(int port, boolean state) {
        if(port == ballDetectPort && state && !(claw.getClawState().equals(Claw.State.CLOSED))){
            System.out.println("The ball has been detected. And the state is: " + state);
            claw.closeClaw();
        }
    }
}