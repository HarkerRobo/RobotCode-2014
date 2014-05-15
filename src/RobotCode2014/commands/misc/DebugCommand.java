
package RobotCode2014.commands.misc;

import RobotCode2014.commands.CommandBase;
import RobotCode2014.wrappers.EncoderWrapper;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that constantly runs, writing debug information to SmartDashboard
 * @author Manan
 */
public class DebugCommand extends CommandBase {
    
    Encoder leftEnc, rightEnc;
    EncoderWrapper pitchEnc;
    public DebugCommand() {
        setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        leftEnc = drivetrain.getLeftEnc();
        rightEnc = drivetrain.getRightEnc();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("Left rate: ", leftEnc.getRate());
        SmartDashboard.putNumber("Right rate: ", rightEnc.getRate());
        SmartDashboard.putNumber("Left raw: ", leftEnc.getRaw());
        SmartDashboard.putNumber("Right raw: ", rightEnc.getRaw());
        SmartDashboard.putNumber("Left ticks: ", leftEnc.get());
        SmartDashboard.putNumber("Right ticks: ", rightEnc.get());
        SmartDashboard.putNumber("Left dist: ", leftEnc.getDistance());
        SmartDashboard.putNumber("Right dist: ", rightEnc.getDistance());
	
	SmartDashboard.putBoolean("piston ls ", manipPiston.isPistonRetracted());
	SmartDashboard.putBoolean("pitch l", manipPitch.isMinPitch());
	SmartDashboard.putBoolean("pressure ", pneumatics.getPressureSwitch());
	
	SmartDashboard.putNumber("pitch ", manipPitch.getPitchRaw());	
	SmartDashboard.putNumber("pitch ", manipPitch.getPitchRads());	
	
	SmartDashboard.putBoolean("mag1 ", manipPitch.source1());
	SmartDashboard.putBoolean("mag2 ", manipPitch.source2());
        
        SmartDashboard.putBoolean("goal hot", networking.isGoalHot());
        SmartDashboard.putNumber("goal angle", networking.getGoalAngle());
        SmartDashboard.putNumber("goal dist", networking.getGoalDistance());
	
        
        //SmartDashboard.putBoolean("pid enabled",manipPitch.getPIDController().isEnable());
        //SmartDashboard.putNumber("pid enabled",manipulator.getPIDController().);
	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}