package Team4180.Listeners;

import Team4180.Input.Attack3Joystick.Button;

/**
 * Interface describing actions that a ButtonListener must react to.
 */
public interface ButtonListener {
	
	/**
	 * This method will be called when the button is released (up).
	 */
	
	public void buttonUp(Button button);
	/**
	 * This method will be called when the button is pressed (down).
	 */
	
	public void buttonDown(Button button);

}
