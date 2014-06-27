package RobotCode2014;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import RobotCode2014.commands.CommandBase;
import RobotCode2014.commands.drivetrain.*;
import RobotCode2014.commands.manipulator.*;
import RobotCode2014.commands.misc.*;
import RobotCode2014.wrappers.GamePadWrapper;
import RobotCode2014.wrappers.JoystickButtonWrapper;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public static Command manualDriveRestore = new RestoreManualDriveCommand();
    public static Command manualDrive = new ManualDriveCommand();
    
    public static Command manualPitch = new ManualPitchAndRetractCommand();
    public static Command manualPitchRestore = new RestoreManualPitchCommand();
    
    public static GamePadWrapper gamepad1 = new GamePadWrapper(1);
    public static GamePadWrapper gamepad2 = new GamePadWrapper(2);
    
    public OI() {
        SmartDashboard.putData(Scheduler.getInstance());
	SmartDashboard.putData(CommandBase.drivetrain);
	SmartDashboard.putData(CommandBase.manipPitch);
        

	SmartDashboard.putData(new UpdateConstantsCommand());
        
        /**************Button mapping*******************
         * The below commands map buttons to the commands that are supposed to 
         *  operate when the buttons are pressed.
         *  
         * @author Manan
         * @author Andrew
         * @author Vedaad
         ***********************************************/
        JoystickButtonWrapper g1b1 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad1, 1);
        JoystickButtonWrapper g1b2 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad1, 2);
        JoystickButtonWrapper g1b3 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad1, 3);
        JoystickButtonWrapper g1b4 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad1, 4);
        JoystickButtonWrapper g1b5 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad1, 5);
        JoystickButtonWrapper g1b6 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad1, 6);
        JoystickButtonWrapper g1b7 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad1, 7);
        JoystickButtonWrapper g1b8 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad1, 8);
        
        JoystickButtonWrapper g2b1 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad2, 1);
        JoystickButtonWrapper g2b2 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad2, 2);
        JoystickButtonWrapper g2b3 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad2, 3);
        JoystickButtonWrapper g2b4 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad2, 4);
        JoystickButtonWrapper g2b5 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad2, 5);
        JoystickButtonWrapper g2b6 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad2, 6);
        JoystickButtonWrapper g2b7 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad2, 7);
        JoystickButtonWrapper g2b8 = new RobotCode2014.wrappers.JoystickButtonWrapper(gamepad2, 8);
        
        //Button    8           Collect balls
        //Button    6           Reject balls
        g1b6.whilePressed(new CollectCommand());
        g1b8.whilePressed(new RejectCommand());
        
        g1b6.whilePressed(new PitchCommand(-.4));
        g1b8.whilePressed(new PitchCommand(.4));
	
	g1b4.whenPressed(new ToggleClampCommand());
        
        //Button    3           Launch at specified networking angle
        //since both require manipulator, only one gets called 
        g1b3.whenPressed(new LaunchCommand());
        
        g1b1.whenPressed(new UnlockPistonCommand());
        g1b1.whenReleased(new LockAndRetractPistonCommand());
        
        g1b2.toggleWhenPressed(new RetractPistonCommand());

        //b3.whenPressed(new RunWithModifier(b5, new RobotCode2014.commands.manipulator.LaunchBallCommand()));
        
        //Button    1 gamepad2  Launch the ball
        //LaunchBallCommand is medium level code while LaunchCommand is high level code
        //b9 does the same thing as b12
        //b9.whenPressed(new LaunchBallCommand());
        
        //Button    8 gamepad2  collect ball
        g2b8.whilePressed(new CollectCommand());
        
        //Button    6 gamepad2  Reject ball
        g2b6.whilePressed(new RejectCommand());
        
        //Button    3 gamepad2  Toggle clamp
        g2b3.whenPressed(new ToggleClampCommand());
        
        //BUtton    4 gamepad2  Toggles the piston
        g2b4.whenPressed(new UnlockPistonCommand());
        g2b4.whenReleased(new LockPistonCommand());

        SmartDashboard.putData(new LaunchBallCommand());
	SmartDashboard.putData(new LaunchCommand());
	
	SmartDashboard.putData(new TurnToAngleCommand(Math.PI)); //180 degrees
        SmartDashboard.putData(new PitchToAngleCommand(0));
	
	SmartDashboard.putData(new RetractPistonCommand());
	SmartDashboard.putData(new LockAndRetractPistonCommand());
	
	SmartDashboard.putData(new LockPistonCommand());
	SmartDashboard.putData(new UnlockPistonCommand());
        
        SmartDashboard.putData("stop compressor", new SetCompressorState(0));
        SmartDashboard.putData("start compressor", new SetCompressorState(1));
	
        
    }
}
