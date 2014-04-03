/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team4180.Input;

import Team4180.Listeners.SwitchListener;
import Team4180.Robot;
import edu.wpi.first.wpilibj.DigitalInput;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author ros_haremash
 */
public class DigitalInputHandler {
    
    private final Hashtable listenersTable;
    private final Boolean[] stateTable;
    
    private final int[] ports;
    private final DigitalInput[] digitalInputs;
    
    //This method assigns the given ports to vaules in a DigitalInput array
    //Accepts int array or ports in which limit switches will be used
    public DigitalInputHandler(int[] digitalInputPorts){
        ports = digitalInputPorts;
        
        listenersTable = new Hashtable();
        stateTable = new Boolean[digitalInputPorts.length];
       
        digitalInputs = new DigitalInput[ports.length];
        for(int i = 0; i < ports.length; i++){
            digitalInputs[i] = new DigitalInput(ports[i]); 
            //System.out.println(digitalInputs[i].getChannel() + ", " + digitalInputs[i].getAnalogTriggerForRouting() + ", " + digitalInputs[i].getChannelForRouting() + ", " + digitalInputs[i].getModuleForRouting());
        }
    }
    
    //This method recieves vaules from the robot's DigitalInput/limit switches
    //Gets vaules from digital input ports
    public void listen(){
        //the handler is lstening
        for(int i = 0; i < ports.length; i++){
            final boolean isSwitched = !digitalInputs[i].get();
            final boolean wasSwitched;
            
            // Was the button pressed last time we checked?
            if((Boolean)stateTable[i] == null){
                wasSwitched = !isSwitched;
            }
            else{
                wasSwitched = ((Boolean)stateTable[i]).booleanValue();
            }
            stateTable[i] = Boolean.valueOf(isSwitched);
            //notifiying the switch listeners
            notifySwitchListeners(ports[i], isSwitched, wasSwitched);
        }
    }
    
    //This method assigns vaules to the Hashtable listnersTable, a number port
    //is assigned as the key of the Vector listeners
    //Accepts the int portnumber that the switch listener is assigned to and the
    //SwitchListner listener
    public void addSwitchListener(int portNumber, SwitchListener listener){
        Integer numberPort = Integer.valueOf(portNumber);
        Vector listeners = (Vector) listenersTable.get(numberPort);
        if(listeners == null) {
            listeners = new Vector();
            listenersTable.put(numberPort, listeners);
        }
        listeners.addElement(listener);
        System.out.println("Attached Switch Listener to port " + numberPort);
    }
    
    public void addSwitchListener(int[] portNumbers, SwitchListener listener){
        for(int i = 0; i < portNumbers.length; i++){
            addSwitchListener(portNumbers[i], listener);
        }
    }
    
    private void notifySwitchListeners(int portNumber, boolean isSwitched, boolean wasSwitched) {
        if (Robot.DEBUG_SWITCH_LISTENER) { System.err.println("Notifying listeners on port " + portNumber); }
        Vector listeners = ((Vector)listenersTable.get(Integer.valueOf(portNumber)));
        if (Robot.DEBUG_SWITCH_LISTENER) { System.err.println("Port " + portNumber + " has " + (listeners == null ? 0 : listeners.size()) + " listeners"); }
        
        if(listeners == null)
            return;

        if (Robot.DEBUG_SWITCH_LISTENER) {
            System.out.println("isSwitched: " + isSwitched);
            System.out.println("wasSwitched: " + wasSwitched);
        }
        
        for (int i = 0; i < listeners.size(); i++) {
            SwitchListener sl = (SwitchListener)listeners.elementAt(i);
            
            if (isSwitched != wasSwitched) {
                if (Robot.DEBUG_SWITCH_LISTENER) { 
                   System.out.println("Calling listener " + i + " for port " + portNumber);
                }
                sl.switchState(portNumber, isSwitched);
            }
        }

//        if (isSwitched && !wasSwitched) {
//            System.out.println("Port "+portNumber+" now "+isSwitched);
//            for (int i = 0; i < listeners.size(); i++){
//                SwitchListener sl = (SwitchListener)listeners.elementAt(i);
//                sl.switchOn(portNumber);
//                //the switch is on
//            }
//        }
//        else if (!isSwitched && wasSwitched) {
//            System.out.println("Port "+portNumber+" now "+isSwitched);
//            for (int i = 0; i < listeners.size(); i++){
//                SwitchListener sl = (SwitchListener)listeners.elementAt(i);
//                sl.switchOff(portNumber);
//                //the switch is off
//            }
//        }
    }
}