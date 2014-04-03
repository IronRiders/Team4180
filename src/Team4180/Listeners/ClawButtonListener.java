/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180.Listeners;

import Team4180.Claw;
import Team4180.Input.Attack3Joystick;
import Team4180.Listeners.ClawButtonListener;
/**
 *
 * @author ros_maremash
 */
//this class will select the correct Listener method depending on the button it recives as a perameter.
public class ClawButtonListener implements ButtonListener{
    private Claw claw;
    
    public ClawButtonListener(Claw claw){
        this.claw = claw;
    }
    
    public void buttonUp(Attack3Joystick.Button button) {
        if(button == Attack3Joystick.Button.TRIGGER){
            claw.drumStop();
        }else if(button == Attack3Joystick.Button.BUTTON_6){
            claw.drumStop();
        }else if(button == Attack3Joystick.Button.BUTTON_7){
            claw.drumStop();
        }else if(button == Attack3Joystick.Button.BUTTON_2){
            claw.stopClaw();
        }else if(button == Attack3Joystick.Button.BUTTON_3){
            claw.stopClaw();
        }
    }

    public void buttonDown(Attack3Joystick.Button button) {
        if(button == Attack3Joystick.Button.TRIGGER){
            claw.fire(); 
        } else if(button == Attack3Joystick.Button.BUTTON_2){
            claw.openClaw();                 
        } else if(button == Attack3Joystick.Button.BUTTON_3){
            claw.closeClaw();
        } else if(button == Attack3Joystick.Button.BUTTON_4){
            claw.stopReload();
        } else if(button == Attack3Joystick.Button.BUTTON_5){
            claw.reload();
        } else if(button == Attack3Joystick.Button.BUTTON_6){
            claw.drumForwards();            
        } else if(button == Attack3Joystick.Button.BUTTON_7){
            claw.drumBackwards();
        } else if(button == Attack3Joystick.Button.BUTTON_8){
            claw.disengageRatchet();
        } else if(button == Attack3Joystick.Button.BUTTON_9){
            claw.engageRatchet();
        } else if(button == Attack3Joystick.Button.BUTTON_10){
            claw.disengageClutch();
        } else if(button == Attack3Joystick.Button.BUTTON_11){
            claw.engageClutch();
        }
    }
}
