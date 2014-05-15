/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.commands.manipulator;

import RobotCode2014.Constants;
import RobotCode2014.commands.CommandBase;

/**
 *
 * @author Benjamin
 */
public class PitchToAngleCommand extends CommandBase {
    private final double target;
    
    /**
     * 
     * @param targetAngle target angle in radians
     */
    public PitchToAngleCommand(double targetAngle) {
	requires(manipPitch);
	double targ = manipPitch.getPitchRads() + targetAngle;
        if(Constants.ManipulatorConst.pitchEncLowest > targ)
            target = Constants.ManipulatorConst.pitchEncLowest;
        else if(targ > Constants.ManipulatorConst.pitchEncHighest)
            target = Constants.ManipulatorConst.pitchEncHighest;
        else 
            target = targ;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double speedIdeal = (manipPitch.getPitchRads() - target) * Constants.ManipulatorConst.p;
        double realSpeed = Math.min(speedIdeal, 1);
	manipPitch.setTargetPitchSpeed(realSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return Math.abs(target - manipPitch.getPitchRads()) < Constants.ManipulatorConst.PIDTolerance;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
    
}
