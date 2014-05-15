package RobotCode2014.commands.manipulator;

import RobotCode2014.commands.CommandBase;


/**
 *
 * @author Brian Chan
 */
public class RestoreManualPitchCommand extends CommandBase {

    public RestoreManualPitchCommand() {
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
    }
    
    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
	if(manipPitch.getCurrentCommand()==null && manipPiston.getCurrentCommand()==null)
	    System.out.println("restoring manual command");
        return manipPitch.getCurrentCommand()==null && manipPiston.getCurrentCommand()==null;
    }
    
    /**
     * Called once after isFinished returns true
     */
    protected void end() {
	oi.manualPitch.start();
    }
    
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}