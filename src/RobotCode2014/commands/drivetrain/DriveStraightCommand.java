package RobotCode2014.commands.drivetrain;

import RobotCode2014.subsystems.Drivetrain;
import RobotCode2014.Constants;
import RobotCode2014.wrappers.EncoderWrapper;

/**
 *
 * @author Manan
 * @author Arcterus
 */
public class DriveStraightCommand extends AutomaticDriveCommand {
	private EncoderWrapper leftEnc, rightEnc;
	private double speed;

	private double leftStart, rightStart;

	private double left, right;

	public DriveStraightCommand() {
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	 protected void initialize() {
		leftEnc = drivetrain.getLeftEnc();
		rightEnc = drivetrain.getRightEnc();
	}

	public void start() {
		super.start();
		speed = .5;
		left = speed;
		right = speed;

		leftStart = leftEnc.getDistance();
		rightStart = rightEnc.getDistance();

		drivetrain.driveRaw(speed, speed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double ratio = leftEnc.getRate() / rightEnc.getRate();

		if(Double.isNaN(ratio)) ratio = 1.0;
		// 	drivetrain.driveRaw(speed / ratio, speed * ratio);

		//double difference = leftEnc.getDistance()-leftStart - rightEnc.getDistance()+rightStart; // d = l-r
		// double difference = (leftEnc.getDistance()-leftStart) - (.5*rightEnc.getDistance()-rightStart); // d = l-r

		right *= ratio;
		//left -= difference * Constants.DrivetrainConst.driveStraightScaling;
		//right += difference * Constants.DrivetrainConst.driveStraightScaling;

		// SmartDashboard.putNumber("diff",difference);
		// SmartDashboard.putNumber("scaled diff",difference*Constants.DrivetrainConst.driveStraightScaling);
		//
		// SmartDashboard.putNumber("Left Rate", leftEnc.getRate());
		// SmartDashboard.putNumber("Right Rate", rightEnc.getRate());
		drivetrain.driveRaw(left, right);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
}
