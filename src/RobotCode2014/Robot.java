/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package RobotCode2014;


import RobotCode2014.commands.AutonomousBasic;
import RobotCode2014.commands.AutonomousCommand;
import RobotCode2014.commands.AutonomousLowGoalCommand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import RobotCode2014.commands.CommandBase;
import RobotCode2014.util.IOUtil;
import RobotCode2014.commands.RunPersistentCommands;
import edu.wpi.first.wpilibj.Watchdog;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomous;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	Watchdog.getInstance().setEnabled(false);
	
	//update constants
        Constants.updateConstants();
	
	//initialize subsystems
        CommandBase.init();
	
        //initialize commands
        //right, left, 
	System.out.println("Initializing autonomous");
        //autonomous = new AutonomousCommand(.5, .5, 3, 0); //todo: get auto shooting angle?
        autonomous = new AutonomousBasic(.5, .5, 3, -30.*Math.PI/180);
        //autonomous = new AutonomousLowGoalCommand(0.5, 0.5, 3, -105.0*Math.PI/180);
	System.out.println("Finished initialization");
	new RunPersistentCommands().start();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
	System.out.println("Starting autonomous");
        autonomous.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	System.out.println("Teleop initiated");
	autonomous.cancel(); //make sure that autonomous stops running
	System.out.println("Autonomous cancelled");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
//	System.out.println(
//	OI.b1.get() + " " +
//	OI.b2.get() + " " +
//	OI.b3.get() + " " +
//	OI.b4.get() + " " +
//	OI.b5.get() + " " +
//	OI.b6.get() + " " +
//	OI.b7.get() + " " +
//	OI.b8.get()
//	);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void disabledInit() {
	IOUtil.println("Robot is disabled.");
    }
    
    public void disabledPeriodic() { }
    
}
