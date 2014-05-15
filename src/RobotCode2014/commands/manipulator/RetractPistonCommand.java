/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.commands.manipulator;

import RobotCode2014.commands.CommandBase;

/**
 *
 * @author me
 */
public class RetractPistonCommand extends CommandBase {
    
    boolean hasSwitched;
    boolean isStopped;
    
    public RetractPistonCommand() {
        requires(manipPiston);
        setTimerLength(1.5);
        isStopped = false;
        hasSwitched = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(isStopped) {
            isStopped = true;
            hasSwitched = false;
            manipPiston.retractPiston(.1);
            System.out.println("retracting piston");
        }
        
        if(isTimerFinished() && !hasSwitched) {
            hasSwitched = true;
            manipPiston.retractPiston(1);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return manipPiston.isPistonRetracted();
    }

    // Called once after isFinished returns true
    protected void end() {
        manipPiston.retractPiston(0);
        isStopped = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	System.out.println("interrupted");
        end();
    }
}
