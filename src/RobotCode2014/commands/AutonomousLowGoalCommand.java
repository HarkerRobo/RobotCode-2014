package RobotCode2014.commands;

import RobotCode2014.commands.drivetrain.*;
import RobotCode2014.commands.manipulator.PitchToAngleCommand;
import RobotCode2014.commands.manipulator.RejectCommand;
import RobotCode2014.commands.misc.CancelCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Manan Shah
 */
public class AutonomousLowGoalCommand extends CommandGroup {
    
    private final double right, left;
    private final double angle;
    private final double time;
    
    /**
     * Autonomous command constructor
     * @param right the right motor speed
     * @param left the left motor speed
     * @param time the time to drive
     * @param angle the angle at which to move. 
     */
    public AutonomousLowGoalCommand(double left, double right, double time, double angle) {
        this.right = right;
        this.left = left;
        this.angle = angle;
	this.time = time;
        
        addParallel(new PitchToAngleCommand(angle));
        addSequential(new DriveForTimeCommand(time, right, left));
        RejectCommand r = new RejectCommand();
        addParallel(r);
        addSequential(new WaitCommand(2.0));
        addSequential(new CancelCommand(r));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("AutonomousLowGoal interrupted?");
    }
}
