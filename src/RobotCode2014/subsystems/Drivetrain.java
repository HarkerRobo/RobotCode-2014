package RobotCode2014.subsystems;

import RobotCode2014.Constants;
import RobotCode2014.OI;
import RobotCode2014.util.Vector3D;
import RobotCode2014.wrappers.EncoderWrapper;
import RobotCode2014.wrappers.TalonWrapper;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Brian Chan
 */
public class Drivetrain extends Subsystem {

    // The error away from the starting direction that is acceptable
    // If set too high stuff will go off target; if set too low it will not go
    // very fast because it will correct its own corrections
    // Error is in degrees, same as gyro measurements
    private final TalonWrapper leftFront, leftBack, rightFront, rightBack;
    private final EncoderWrapper leftEnc, rightEnc;
//    private Encoder encoder1, encoder2;
    private final Gyro gyro;

    private double targetLeft, targetRight;
    private double currLeft, currRight;

    private boolean busy;

    public Drivetrain() {
	//jags are named by position of the motors they control on the robot
	leftFront = new TalonWrapper(1);
	leftBack = new TalonWrapper(2);
	rightFront = new TalonWrapper(3);
	rightBack = new TalonWrapper(4);

	leftEnc = new EncoderWrapper(1, 2);
	rightEnc = new EncoderWrapper(3, 4);
        
        gyro = new Gyro(1);
	
	LiveWindow.addActuator("Drivetrain", "talon1", leftFront);
	LiveWindow.addActuator("Drivetrain", "talon2", leftBack);
	LiveWindow.addActuator("Drivetrain", "talon3", rightFront);
	LiveWindow.addActuator("Drivetrain", "talon4", rightBack);
	
	LiveWindow.addSensor("Drivetrain", "leftEnc", leftEnc);
	LiveWindow.addSensor("Drivetrain", "rightEnc", rightEnc);

	leftEnc.start();
	rightEnc.start();

	//encoder accelerationScaling
	leftEnc.setDistancePerPulse(Constants.DrivetrainConst.leftEncDistPerPulse);
	rightEnc.setDistancePerPulse(Constants.DrivetrainConst.rightEncDistPerPulse);

	targetLeft = targetRight = 0;
        currLeft = currRight = 0;

	busy = false;
    }

    public void drive(double left, double right) {
	//square inputs, keeping sign
	left *= (left > 0) ? left : -left;
	right *= (right > 0) ? right : -right;

	//save target values, which will be used in updateDrive
	targetLeft = left;
	targetRight = right;
    }

    /**
     * Moves the current speed of the drive train closer to (or to) the target value.
     * Scaling is done with the intention of accelerating smoothly to keep things from breaking.
     */
    public void updateDrive() {
	currLeft = scale(targetLeft, currLeft, Constants.DrivetrainConst.accelerationThreshold, Constants.DrivetrainConst.accelerationScaling);
	currRight = scale(targetRight, currRight, Constants.DrivetrainConst.accelerationThreshold, Constants.DrivetrainConst.accelerationScaling);

	driveRaw(currLeft, currRight);
    }
    
    /**
     * Moves the current speed of the drive train closer to (or to) the target value.
     * Scaling is done with the intention of accelerating smoothly to keep things from breaking.
     * @param left
     * @param right
     */
    public void updateDrive(double left, double right) {
	currLeft = scale(left, currLeft, Constants.DrivetrainConst.accelerationThreshold, Constants.DrivetrainConst.accelerationScaling);
	currRight = scale(right, currRight, Constants.DrivetrainConst.accelerationThreshold, Constants.DrivetrainConst.accelerationScaling);

	driveRaw(currLeft, currRight);
    }
    
    /**
     * Scales val by scaling if val > threshold.
     * If val < threshold, returns val.
     * If val > threshold, returns weighted average of threshold and target values.
     * @param val the value to scale
     * @param threshold the max value of val before scaling
     * @param scaling the factor to scale val by
     * @return the scaled value of val if val was scaled; val if val was not scaled
     */
    private double scale(double val, double curr, double threshold, double scaling) {
	return (Math.abs(val-curr) < threshold)
		? val
                : (val*scaling + curr*(1-scaling)); 	//weighted average of previous and intended values
    }

    public void driveRaw(double left, double right) {
	leftFront.set(Constants.DrivetrainConst.leftFrontFlip   ? left  : -left);
	leftBack.set(Constants.DrivetrainConst.leftBackFlip	? left  : -left);
	rightFront.set(Constants.DrivetrainConst.rightFrontFlip ? right : -right);
	rightBack.set(Constants.DrivetrainConst.rightBackFlip   ? right : -right);
	
	SmartDashboard.putNumber("accel left",left);
	SmartDashboard.putNumber("accel right",right);
    }

    /**
     * Drives the robot to a set point using vector arithmetic
     * @param x     Final x-position
     * @param y     Final y-position
     * @param speed the speed for the robot to move at
     * @param timeStep
     * @return true if success
     */
    public boolean driveToPoint(double x, double y, double speed, double timeStep) {
	//current position as a vector
	Vector3D currentPosition = new Vector3D(getAverageEncoderRate() * timeStep, getYawAngle());
	//where we want to go
	Vector3D desiredPosition = new Vector3D(x, y, 0);

	//how far the desired position is form the current position
	Vector3D offsetPosition = desiredPosition.subtract(currentPosition);

	//If the robot is close enough end command, break and return true
	if(offsetPosition.magnitude() < .1) return true;

	//get our current velocity and calculate theta
	Vector3D currentVelocity = new Vector3D(
		getAverageEncoderRate() * Math.cos(getYawAngle()),
		getAverageEncoderRate() * Math.sin(getYawAngle()),
		0);
	double theta = offsetPosition.normalize().cross(currentVelocity.normalize()).direction();

	//drive based on theta and speed
	drive(speed + theta * Constants.DrivetrainConst.driveToPointScaling, speed - theta * Constants.DrivetrainConst.driveToPointScaling); //TODO: does this break with acceleration accelerationScaling
	return false;
    }

    public EncoderWrapper getLeftEnc() {
	return leftEnc;
    }

    public EncoderWrapper getRightEnc() {
	return rightEnc;
    }

    /**
     * @return Horizontal direction in radians
     */
    public double getYawAngle() {
	return gyro.getAngle() * Math.PI/180;
    }

    public String encoderString() {
	return "Raw Value " + leftEnc.getRaw() + " Value " + leftEnc.get() + " Distance " + leftEnc.getDistance() + " Rate " + leftEnc.getRate();
    }

    public double getAverageEncoderDistance() {
	return (rightEnc.getDistance() + leftEnc.getDistance()) / 2;
    }

    public double getAverageEncoderRate() {
	return (rightEnc.getRate() + leftEnc.getRate()) / 2;
    }

    public void updateEncoders() {
	leftEnc.updateRate();
	rightEnc.updateRate();
    }

    /**
     * Stops drive train movement and gives control back to user
     * (Lets DriveWithJoysticksCommand override any other drivetrain commands)
     */
    public void stop() {
	driveRaw(0, 0);
	busy = false;
    }

    public boolean isBusy() {
	return busy;
    }

    public void setBusy(boolean bool) {
	busy = bool;
    }

    public void initDefaultCommand() {
	setDefaultCommand(OI.manualDrive);
    }

    public void resetGyro() {
        gyro.reset();
    }

}
