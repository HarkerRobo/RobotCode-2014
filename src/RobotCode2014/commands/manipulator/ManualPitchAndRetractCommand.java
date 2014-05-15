package RobotCode2014.commands.manipulator;

import RobotCode2014.OI;
import RobotCode2014.commands.CommandBase;
import RobotCode2014.wrappers.GamePadWrapper;

/**
 *
 * @author Brian Chan
 */
public class ManualPitchAndRetractCommand extends CommandBase {

    public ManualPitchAndRetractCommand() {
	requires(manipPitch);
        requires(manipPiston);
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
	manipPitch.setTargetPitchSpeed(OI.gamepad2.getRightY());
	manipPiston.retractPiston(OI.gamepad2.getLeftY());
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
	oi.manualPitchRestore.start();
    }
}
