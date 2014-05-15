
package RobotCode2014.wrappers;

import edu.wpi.first.wpilibj.Talon;

/**
 * A wrapper for Talons that adds the ability to flip the direction of the motor
 * @author farzin
 */
public class TalonWrapper extends Talon {

    private boolean reverse = false;
    
    public TalonWrapper(int channel) {
	super(channel);
    }
    
    public TalonWrapper(int channel, boolean reverse) {
	super(channel);
	this.reverse = reverse;
    }

    public TalonWrapper(int slot, int channel) {
	super(slot, channel);
    }
    
    public TalonWrapper(int slot, int channel, boolean reverse) {
	super(slot, channel);
	this.reverse = reverse;
    }
    
    public void setReverse(boolean reverse) {
	this.reverse = reverse;
    }
    
    public boolean getReverse() {
	return reverse;
    }
    
    public void set(double speed) {
	if(reverse)
	    super.set(-speed);
	else super.set(speed);
    }
    
    public double get() {
	if(reverse)
	    return -super.get();
	else return super.get();
    }
}
