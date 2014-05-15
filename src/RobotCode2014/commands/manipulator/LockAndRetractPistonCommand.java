package RobotCode2014.commands.manipulator;

import RobotCode2014.commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 * @author Brian Chan
 */
public class LockAndRetractPistonCommand extends CommandGroup {

    public LockAndRetractPistonCommand() {
        addSequential(new LockPistonCommand());
        addSequential(new RetractPistonCommand());
    }

}