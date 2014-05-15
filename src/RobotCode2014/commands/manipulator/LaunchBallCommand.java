/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.commands.manipulator;

import RobotCode2014.Constants;
import RobotCode2014.commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Manan Shah
 * @author Ben H. Fires the ball (assumes that the winch is already wound up and
 * clamp is open) and retracts the piston again.
 */
public class LaunchBallCommand extends CommandGroup {

    public LaunchBallCommand() {
        addSequential(new OpenClampCommand());
        addSequential(new WaitCommand(Constants.ManipulatorConst.launchAfterOpenClampDelay));
        addSequential(new UnlockPistonCommand());
        addSequential(new WaitCommand(Constants.ManipulatorConst.retractAfterLaunchDelay));

        addParallel(new CloseClampCommand());
        addSequential(new LockAndRetractPistonCommand());
    }

}
