package RobotCode2014.commands;

import RobotCode2014.commands.drivetrain.DriveForTimeCommand;
import RobotCode2014.commands.manipulator.LaunchBallCommand;
import RobotCode2014.commands.manipulator.PitchToAngleCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

/**
 *
 * @author Brian Chan
 */
public class AutonomousCommandNotHot extends CommandGroup {
    
    public AutonomousCommandNotHot(double angle, double time, double left, double right) {
        //sets the angle to 60 degrees
	    System.out.println("Started commmand chain.");
            addParallel(new PitchToAngleCommand(angle));
	    System.out.println("Finished MoveArmToPitch");
            addSequential(new DriveForTimeCommand(time, left, right));
	    System.out.println("Finished DriveForTime");
	    addSequential(new WaitForChildren());
	    System.out.println("Waited for Chillun");
            //addSequential(new LaunchBallCommand());
	    System.out.println("Launched ball, yay!");
    }
}