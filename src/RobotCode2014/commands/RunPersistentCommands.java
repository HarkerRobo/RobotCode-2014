package RobotCode2014.commands;

import RobotCode2014.commands.drivetrain.DrivetrainAccelerationCommand;
import RobotCode2014.commands.manipulator.PitchAccelerationCommand;
import RobotCode2014.commands.misc.UpdateEncodersCommand;
import RobotCode2014.commands.misc.DebugCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A command group that starts all commands that should run continuously on the robot.
 * (Exists mainly just to save space in the scheduler display on the SmartDashboard.)
 * @author Brian Chan
 */
public class RunPersistentCommands extends CommandGroup {
    
    public RunPersistentCommands() {
        addParallel(new DebugCommand());
	addParallel(new UpdateEncodersCommand());
	addParallel(new DrivetrainAccelerationCommand());
        addParallel(new PitchAccelerationCommand());
    }
    
}