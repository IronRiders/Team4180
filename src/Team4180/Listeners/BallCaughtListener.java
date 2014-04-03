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
public class BallCaughtListener implements SwitchListener {

    private int ballCaughtLimitSwitchPort;
    private Claw claw;

    public BallCaughtListener(Claw claw, int ballCaughtPort) {
        this.claw = claw;
        this.ballCaughtLimitSwitchPort = ballCaughtPort;
    }

    public void switchOn(int port) {
        System.out.println("The switch is pressed");
    }

    public void switchOff(int port) {
        System.out.println("The switch is off");
    }

    public void switchState(int port, boolean state) { 
        if(port == ballCaughtLimitSwitchPort && state && claw.getClawState().equals(Claw.State.CLOSING))
        {
            claw.setClawState(Claw.State.CLOSED);
            claw.stopClaw();
            System.out.println("The ball has been caught");
        }
    }

}
