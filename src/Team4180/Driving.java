/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author ros_kecooper
 * modified by Aaron Jacobson
 * modified on: 1/30/14
 */
public class Driving {
    private Jaguar jagleft;
    private Jaguar jagright;
    private double CurrentXValue; 
    private double CurrentYValue;
    private double CurrentZValue;
    private boolean triggered;
    public static final int LEFT_MOTOR_PORT = 1;
    public static final int RIGHT_MOTOR_PORT = 2;


    public Driving(){
        jagleft = new Jaguar(LEFT_MOTOR_PORT);//the port that the left Jaguar motor is plugged into(value = 1);
        jagright = new Jaguar(RIGHT_MOTOR_PORT);//the port that the right Jaguar motor is plugged into(value = 2);

        CurrentXValue = 0;
        CurrentYValue = 0;
        CurrentZValue = .5;
        triggered = false;
    }
    
    private void leftJaguars(double speed){
        //the negative is because of how the motors are set up
        jagleft.set(-speed);
    }
    private void rightJaguars(double speed) {
        jagright.set(speed);
    }
    public void updateX(double x){
        CurrentXValue = x;
    }
    public void updateY(double y){
        CurrentYValue = y;
    }
    public void updateZ(double z){
        CurrentZValue = z;
    }
    public void reCalcVelocity(){
      double newY = ((int)(CurrentYValue*(100*CurrentZValue)))/(CurrentZValue*100.0);
      double newX;
      //This will stop all forward movement when the trigger is pulled
      if(!triggered){
          newX = ((int)(CurrentXValue*(100*CurrentZValue)))/(CurrentZValue*100.0);
      }else{
          newX = 0;
      }
      //this is where we tell the motors how fast to go and in what direction
      rightJaguars(Math.max(Math.min(newY+newX, 1),-1));
      leftJaguars(Math.max(Math.min(newY-newX, 1),-1));
      
    }
    public void reCalcVelocityA(double speed)
    {
        leftJaguars(speed);
        rightJaguars(speed);
    }
    public void stop(){
        leftJaguars(0);
        rightJaguars(0);
    }
   // Q.Q
    public void driveStraight(boolean triggerPressed){ //triggerpressed comes from TriggerListener.
      triggered = triggerPressed;
      reCalcVelocity();
    }
}

