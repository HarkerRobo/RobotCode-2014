/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotCode2014.commands.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Alex Lyon
 */
public class TurnToNetworkingAngleCommand extends AutomaticDriveCommand {
    private final TurnToAngleCommand turnCommand;
    
    public TurnToNetworkingAngleCommand() {
        double angle = networking.getGoalAngle();
        if(angle >= 0) {
            turnCommand = new TurnToAngleCommand(angle);
        } else {
            CommandGroup group = getGroup();
            if(group != null) {
                group.cancel();
            }
            turnCommand = null;
        }
    }
    
    public void start() { if(turnCommand != null) turnCommand.start(); }
    
    protected void initialize() { }
    
    protected void execute() { if(turnCommand != null) turnCommand.execute(); }
    
    protected boolean isFinished() {
        if(turnCommand != null) return turnCommand.isFinished();
        return true;
    }
}
