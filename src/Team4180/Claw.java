/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Team4180;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author ros_aljacobson
 */
public class Claw {

    private Talon openClose;
    private Talon raiseLower;
    //private Talon pullBackPiston;
    private Talon drumMotor;
    private Servo ratchet;
    private Servo clutch;
    private double yValueNow;
    private double zValueNow = 1;
    private String clawState;

    //These are which ports each motor is on the robot
    public static final int TEMP_PORT = 10; //if hardware has not given us a port yet then TEMP_PORT is used
    public static final int CLAW_OPEN_CLOSE_PORT = 6;
    public static final int CLAW_RAISE_LOWER_PORT = 5;
    //public static final int PULL_BACK_PISTON_PORT = 4;
    public static final int RATCHET_PORT = 9;
    public static final int DRUM_PORT = 4;
    public static final int CLUTCH_SERVO_PORT = 10;//not temp

    public static final double DISENGAGE_CLUTCH_ANGLE = -20;
    public static final double ENGAGE_CLUTCH_ANGLE = -100; 
    public static final double DISENGAGE_RATCHET_ANGLE = 100; //HARDWARE unknown
    public static final double ENGAGE_RATCHET_ANGLE = 0; //HARDWARE unknown
    
    //controls the speed of the talons for the claw and drum
    public double CLOSE_CLAW_VALUE = -zValueNow;//needs to be tested
    public double OPEN_CLAW_VALUE = zValueNow;//needs to be tested
    public double DRUM_FORWARD_SPEED = zValueNow;//needs to be tested
    public double DRUM_BACKWARDS_SPEED = -zValueNow;//needs to be tested

    public final class State {

        public static final String OPEN = "open";
        public static final String OPENING = "opening";
        public static final String CLOSED = "closed";
        public static final String CLOSING = "closing";
    }

    public Claw() {
        //This assigns the ports to each of the seperate motors (talons and servos.
        //each motor will get input from the robot through its assigned port.
        openClose = new Talon(CLAW_OPEN_CLOSE_PORT);
        raiseLower = new Talon(CLAW_RAISE_LOWER_PORT);
        //pullBackPiston = new Talon(PULL_BACK_PISTON_PORT);
        drumMotor = new Talon(DRUM_PORT);
        ratchet = new Servo(RATCHET_PORT);
        
        clutch = new Servo(CLUTCH_SERVO_PORT);
        yValueNow = 0;
        clawState = State.CLOSED;
    }

    //This method opens the claw. It takes in a double from a button listener(ClawOpenListener).
    public void openClaw() {
        
        if (!clawState.equals(State.OPEN)) {
            openClose.set(OPEN_CLAW_VALUE);
            setClawState(State.OPENING);
            System.out.println("opening claw"/* + " The port is " + openClose.getChannel() + "  " + openClose.getSpeed()*/);
        //    System.out.println(openClose.get() + ", " + openClose.getChannel() + ", " + openClose.getExpiration() + ", " + openClose.getModuleNumber() + ", " + openClose.getPosition() + ", " + openClose.getRaw() + ", " + openClose.getSpeed());
          //  System.out.println(openClose.isAlive() +", "+openClose.isSafetyEnabled()+", "+openClose.getDescription()+", "+openClose.getSmartDashboardType());
        }
    }
    //This method closes the claw. It takes a negative double from the ClawCloseListener.
    public void closeClaw() {
        if (!clawState.equals(State.CLOSED)) {
            openClose.set(CLOSE_CLAW_VALUE);
            setClawState(State.CLOSING);
            System.out.println("closing claw" + " The port is " + openClose.getChannel() + "  " + openClose.getSpeed());
        }
    }

    //takes in a string...
    //used to set state of claw
    public void setClawState(String state) {
        clawState = state;
        System.out.println("Claw state is: " + state);
    }

    public String getClawState() {
        return clawState;
    }

    //This method stops movement of openeing and closing the claw by setting
    //the openClose value back to zero.
    public void stopClaw() {
        openClose.set(0);
    }

    public void updateY(double y) {
        yValueNow = y;
    }

    public void updateZ(double z) {
        zValueNow = z;
        CLOSE_CLAW_VALUE = -zValueNow;//needs to be tested
        OPEN_CLAW_VALUE = zValueNow;//needs to be tested
        DRUM_FORWARD_SPEED = zValueNow;//needs to be tested
        DRUM_BACKWARDS_SPEED = -zValueNow;//needs to be tested
    }

    //This method controls the pitch(up and down) of the claw. Takes the y value from the 2nd joystick
    //The pitch is controlled by the actual y value of the joystick (not a button).
    public void clawElevation() {
        double newY = ((double) (int) (yValueNow * 100)) / 100;
        raiseLower.set(-newY);
    }

    public void stopElevation() {
        raiseLower.set(0);
    }

    //This method releases the ratchet and releases the spring system to let the robot shoot the ball.
    //When the cocking servo reaches the angle (100 degrees) that is equal to the disengae clutch angle
    //then the disengage ratchet method is called.
    public void fire() {
        //100 --> class constant
        disengageRatchet();
        //if (clutch.get() == DISENGAGE_CLUTCH_ANGLE) {
          //  disengageRatchet();
        //}
        Timer.delay(1);
        reload();
    }

    //Automatically reloads the shooting mechanism
    public void reload() {
        engageRatchet();
        engageClutch();
        drumBackwards();
    }

    public void stopReload() {
        drumStop();
        disengageClutch();
    }

    public void engageClutch() {
        clutch.setAngle(ENGAGE_CLUTCH_ANGLE);
    }

    public void disengageClutch() {
        clutch.setAngle(DISENGAGE_CLUTCH_ANGLE);
    }

    public void drumForwards() {
        //is there a drum forwards, should there be. Is it just disengage.
        //needs to be tested
        drumMotor.set(DRUM_FORWARD_SPEED);
    }

    public void drumBackwards() {
        //needs to be tested
        drumMotor.set(DRUM_BACKWARDS_SPEED);
    }

    public void drumStop() {
        drumMotor.stopMotor();
    }

    //This method disengages the ratchet to allow fireing. 
    //This method is called upon in the method fire.
    public void disengageRatchet() {
        //delay amount needs to be tested 
        ratchet.set(DISENGAGE_RATCHET_ANGLE);
        //Timer.delay(.025);
        //Why is this here? No delay needed in disengaging the rachet
        //ratchet.set(0);
    }

    //This method engages the ratchet to hold the system in place after the reload process.
    //The method is used when called upon by the auto reload.
    public void engageRatchet() {
        //delay amount needs to be tested
        ratchet.set(ENGAGE_RATCHET_ANGLE);
        //Timer.delay(.025);
        //ratchet.set(0);
    }
    public void openClawA(double speed)
    {
        openClose.set(speed);
        setClawState(State.OPENING);
        System.out.println("opening claw");
    }
    public void closeClawA(double speed)
    {
        openClose.set(-speed);
        setClawState(State.CLOSING);
        System.out.println("closing claw");
    }
}
