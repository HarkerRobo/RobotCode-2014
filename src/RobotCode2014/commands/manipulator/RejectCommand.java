/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.commands.manipulator;

import RobotCode2014.OI;
import RobotCode2014.commands.CommandBase;

/**
 *
 * @author nikita
 */
public class RejectCommand extends CommandBase {

    public RejectCommand() {
        System.out.println("Reject Command Created");        
	requires(manipCollector);
    }

    public void start() {
	super.start();
        System.out.println("Reject Command Started");
	//if(!OI.gamepad1.getRawButton(5))
        manipCollector.ejectFromClamp();
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("Reject Command Ended");
	manipCollector.stopCollector();
	//Move arm up or something afterwards so camera can see?
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("Reject Command interrupted by " + manipCollector.getCurrentCommand().getName());
	end();
    }
}
