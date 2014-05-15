package RobotCode2014.commands;

import RobotCode2014.commands.drivetrain.DriveForTimeCommand;
import RobotCode2014.commands.manipulator.LaunchCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Brian Chan
 */
public class AutonomousCommandHot extends CommandGroup {
    
    public AutonomousCommandHot(double left, double right, double time) {
        //assumes shootCommand works as intended
            addSequential(new LaunchCommand());
            addSequential(new DriveForTimeCommand(time, left, right));
    }
}