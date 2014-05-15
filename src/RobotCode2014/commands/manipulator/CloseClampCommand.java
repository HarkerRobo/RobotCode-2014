package RobotCode2014.commands.manipulator;

import RobotCode2014.commands.CommandBase;

// NOTE: this isn't used for anything right now.

/**
 *
 * @author Brian Chan
 */
public class CloseClampCommand extends CommandBase {
    public CloseClampCommand() {
	requires(manipClamp);
    }
    
    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    }
    
    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
	manipClamp.closeClamp();
    }
    
    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return true;
    }
    
    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }
    
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}