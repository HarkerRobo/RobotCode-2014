package RobotCode2014.commands.drivetrain;

import RobotCode2014.commands.CommandBase;


/**
 *
 * @author Brian Chan
 */
public class DrivetrainAccelerationCommand extends CommandBase {

    public DrivetrainAccelerationCommand() {
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
	drivetrain.updateDrive();
    }
    
    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
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