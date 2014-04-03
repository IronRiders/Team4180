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
public class ClawSwitchListener implements SwitchListener {

    private int closedLimitSwitchPort;
    private int openLimitSwitchPort;
    private Claw claw;

    public ClawSwitchListener(Claw claw, int closePort, int openPort) {
        this.claw = claw;
        this.closedLimitSwitchPort = closePort;
        this.openLimitSwitchPort = openPort;
    }

    public void switchOn(int port) {
    }

    public void switchOff(int port) {
    }

    public void switchState(int port, boolean state) {
        if(port == openLimitSwitchPort && state && claw.getClawState().equals(Claw.State.OPENING))
        {
            claw.setClawState(Claw.State.OPEN);
            claw.stopClaw();
            System.out.println("The claw has opened" + state);
        }else if(port == closedLimitSwitchPort && state && claw.getClawState().equals(Claw.State.CLOSING))
        {
            claw.setClawState(Claw.State.CLOSED);
            claw.stopClaw();
            System.out.println("The claw has closed" + state);
            System.out.println();
        }
    }

}
