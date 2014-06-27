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
 * @author me
 */
public class PitchCommand extends CommandBase {

    double speed;

    public PitchCommand(double speed) {
	requires(manipPitch);
	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(OI.gamepad1.getRawButton(5))
            manipPitch.setTargetPitchSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        manipPitch.setTargetPitchSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
