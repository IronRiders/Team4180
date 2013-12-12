/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Team4180.Listeners;

/**
 *
 * @author Alex
 */
public interface JoystickListener {
    public void joystickForward(double speed);
    public void joystickBackwards(double speed);
    public void joystickRight(double speed);
    public void joystickLeft(double speed);
	public void joystickCentered();
    public void joystickZ(double z);
    public double[] getXYZ();
}
