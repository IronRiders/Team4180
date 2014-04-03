package Team4180.Listeners;

/**
 * Interface describing actions that a JoystickListener must react to.
 */
public interface JoystickListener {
    
    /**
     * This method will be called whenever the joystick is moved
     * 
     * @param x the X (+up / -down) position of the joystick
     * @param y the Y (+left / -right) position of the joystick
     * @param z the Z position of the joystick
     */
    public void joystickMoved(double x, double y, double z);
}
