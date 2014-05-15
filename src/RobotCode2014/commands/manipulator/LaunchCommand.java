/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.commands.manipulator;

import RobotCode2014.commands.drivetrain.TurnToNetworkingAngleCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

/**
 *
 * @author Manan Shah :D
 * @author Ben H. 
 */
public class LaunchCommand extends CommandGroup {
    
    public LaunchCommand() {
        addParallel(new TurnToNetworkingAngleCommand());
        addSequential(new PitchToNetworkingCommand());
        addSequential(new WaitForChildren());
        addSequential(new LaunchBallCommand());
	addSequential(new PitchToAngleCommand(1/2 * Math.PI)); //90 degrees
    }
}
