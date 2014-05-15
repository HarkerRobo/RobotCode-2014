package RobotCode2014.commands;

import RobotCode2014.commands.drivetrain.*;
import RobotCode2014.commands.manipulator.LaunchBallCommand;
import RobotCode2014.commands.manipulator.PitchToAngleCommand;
import RobotCode2014.commands.manipulator.LaunchCommand;
import RobotCode2014.commands.misc.WaitIfNotHot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

/**
 *
 * @author Manan Shah
 */
public class AutonomousCommand extends CommandGroup {
    
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
    public AutonomousCommand(double left, double right, double time, double angle) {
        this.right = right;
        this.left = left;
        this.angle = angle;
	this.time = time;
        
        addParallel(new PitchToAngleCommand(angle));
        addSequential(new DriveForTimeCommand(time, left, right));
        addSequential(new WaitForChildren()); //ball must be raised before image processing works properly
        addSequential(new WaitIfNotHot(5)); //wait until goal is hot or 5s have passed, whichever coems first
        addSequential(new LaunchBallCommand());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("AutonomousCommand interrupted?");
    }
}
