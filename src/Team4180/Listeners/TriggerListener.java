/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180.Listeners;

import Team4180.Driving;
import Team4180.Input.Attack3Joystick;
import edu.wpi.first.wpilibj.Servo;
/**
 *
 * @author ros_diwang
 */
public class TriggerListener implements ButtonListener{
    private Driving driving;
    
    //Runs driving class 
    //Takes in driving
    public TriggerListener(Driving driving){
        this.driving = driving;
    }
    //This starts the forward movement of the robot if the trigger is released
    //Takes a button, number 1 (trigger)
    public void buttonUp(Attack3Joystick.Button button){
        driving.driveStraight(false);
    }
    //This stops the forward movement of the robot if the trigger is released
    //Takes a button, number 1 (trigger)
    public void buttonDown(Attack3Joystick.Button button){
        driving.driveStraight(true);   
    }    
    
   

}

