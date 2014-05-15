/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.commands;

import RobotCode2014.commands.drivetrain.DriveForTimeCommand;
import RobotCode2014.commands.manipulator.LaunchBallCommand;
import RobotCode2014.commands.manipulator.PitchToAngleCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author me
 */
public class AutonomousBasic extends CommandGroup {
    
    public AutonomousBasic(double left, double right, double time, double pitch) {
//        addParallel(new MoveArmToPitchCommand(pitch));
        addSequential(new DriveForTimeCommand(left, right, time));
//        addSequential(new WaitForChildren());
//        addSequential(new LaunchBallCommand());
    }
}
