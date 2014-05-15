package RobotCode2014.commands.manipulator;

import RobotCode2014.commands.CommandBase;


/**
 *
 * @author Brian Chan
 */
public class UnlockPistonCommand extends CommandBase {

    public UnlockPistonCommand() {
        requires(manipPiston);
        setTimerLength(.3);
    }
    
    public void start() {
        super.start();
        manipPiston.unlockPiston();
        manipPiston.retractPiston(1); //send short retract signal to allow piston to unlock properly
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
        return isTimerFinished();
    }
    
    /**
     * Called once after isFinished returns true
     */
    protected void end() {
        manipPiston.retractPiston(0);
    }
    
    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
        end();
    }
}