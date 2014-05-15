/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.commands.drivetrain;

import RobotCode2014.Constants;
import RobotCode2014.OI;
import RobotCode2014.util.IOUtil;
import RobotCode2014.commands.CommandBase;
import RobotCode2014.wrappers.GamePadWrapper;

/**
 *
 * @author neymikajain
 */
public class ManualDriveCommand extends CommandBase {

    public ManualDriveCommand() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        GamePadWrapper gamepad = RobotCode2014.OI.gamepad1;
        if (gamepad.getRawButton(7)) {
            drivetrain.drive(gamepad.getLeftY() * Constants.DrivetrainConst.lowSpeedScaling,
                    gamepad.getRightY() * Constants.DrivetrainConst.lowSpeedScaling);
        } else {
            drivetrain.drive(gamepad.getLeftY() * Constants.DrivetrainConst.highSpeedScaling,
                    gamepad.getRightY() * Constants.DrivetrainConst.highSpeedScaling);
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drivetrain.isBusy();
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.setBusy(true);
        //starts the manualDriveRestore method - continually checks for joystick updates
        OI.manualDriveRestore.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
