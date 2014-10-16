/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180.Listeners;
import Team4180.Driving;

/**
 *
 * @author ros_kecooper
 */
public class MovementListener implements JoystickListener {
    private Driving driving;
    
    //Runs driving class 
    //Takes in driving
    public MovementListener (Driving driving){
        this.driving = driving;
    }
    //Determines if the joystick has moved and updates    
    //Takes in the coordinates of joystick position
    public void joystickMoved(double x, double y, double z){
        z = 1;
        driving.updateX(x);
        driving.updateY(y);
        driving.updateZ(z);
        driving.reCalcVelocity();
    }
}
