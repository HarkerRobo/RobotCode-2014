/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotCode2014.commands.manipulator;

import RobotCode2014.commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Alex Lyon
 */
public class PitchToNetworkingCommand extends CommandBase {
    private PitchToAngleCommand moveCommand;

    public PitchToNetworkingCommand() {
        double dist = networking.getGoalDistance();
        if(dist >= 0) {
            moveCommand = new PitchToAngleCommand(manipPitch.calculatePitch(dist));
        } else {
            CommandGroup group = getGroup();
            if(group != null) {
                group.cancel();
            }
            moveCommand = null;
        }
    }

    protected void initialize() { if(moveCommand != null) moveCommand.initialize(); }

    protected void execute() { if(moveCommand != null) moveCommand.execute(); }

    protected boolean isFinished() {
        if(moveCommand != null) return moveCommand.isFinished();
        return true;
    }

    protected void end() { if(moveCommand != null) moveCommand.end(); }

    protected void interrupted() { if(moveCommand != null) moveCommand.interrupted(); }
}
