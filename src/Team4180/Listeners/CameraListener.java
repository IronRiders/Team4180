/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Team4180.Listeners;

import ImageRecognition.ImageRecognition.TargetInfo;
import Team4180.Input.Attack3Joystick;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 * @author ros_mhreid
 */
public class CameraListener implements ButtonListener{
	private Servo hServo,vServo;
	private static final double SPEED = 0.1;
	
	public CameraListener(int[] ports){
		hServo = new Servo(ports[0]);
		vServo = new Servo(ports[1]);
	}
	
	public void buttonUp(Attack3Joystick.Button button) {
	}

	public void buttonDown(Attack3Joystick.Button button) {
		if(button == Attack3Joystick.Button.BUTTON_3){
			vServo.set(vServo.get()+SPEED);
		}
		else if(button == Attack3Joystick.Button.BUTTON_2){
			vServo.set(vServo.get()-SPEED);
		}
		else if(button == Attack3Joystick.Button.BUTTON_4){
			hServo.set(hServo.get()-SPEED);
		}
		else if(button == Attack3Joystick.Button.BUTTON_5){
			hServo.set(hServo.get()+SPEED);
		}
	}
	public void track (TargetInfo info, TurretListener turret){
		double x = info.distanceX;
		double y = info.distanceY;
		double dist = info.distanceFromTarget;
		
		double gravityOffset = 0;
		
		if (x > hServo.get()){
			hServo.set(hServo.get()-0.1);
		} else if (x < hServo.get()){
			hServo.set(hServo.get()+0.1);
		}
		if (y > vServo.get()+gravityOffset){
			vServo.set(vServo.get()-0.1);
		} else if (y < vServo.get()+gravityOffset){
			vServo.set(vServo.get()+0.1);
		}
		/*
		if (hServo.get() < 0){
			turret.joystickRight(1);
		} else if (hServo.get() > 0){
			turret.joystickLeft(1);
		}
		if (vServo.get() < 0 + gravityOffset){
			turret.joystickForward(1);
		} else if (vServo.get() > 0 + gravityOffset){
			turret.joystickBackwards(1);
		}*/
	}
}
