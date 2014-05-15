package RobotCode2014.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import RobotCode2014.OI;
import RobotCode2014.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.CANJagDrivetrain
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    
    public static Drivetrain drivetrain = new Drivetrain();
    public static ManipulatorPitch manipPitch = new ManipulatorPitch();
    public static ManipulatorClamp manipClamp = new ManipulatorClamp();
    public static ManipulatorCollector manipCollector = new ManipulatorCollector();
    public static ManipulatorPiston manipPiston = new ManipulatorPiston();
    
    public static Networking networking = new Networking();
    public static Pneumatics pneumatics = new Pneumatics();
    
    private double timerLength;
    
    protected double startTime;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
	// will), constructing it during the construction of CommandBase (from
	// which commands extend), subsystems are not guaranteed to exist,
	// thus their requires() statements may grab null pointers.
	oi = new OI();
    }

    public CommandBase(String name) {
	super(name);
    }

    public CommandBase() {
	super();
	timerLength = -1;
    }
    
    public void start() {
	super.start();
	startTime = Timer.getFPGATimestamp();
    }
    
    protected void setTimerLength(double seconds) {
	timerLength = seconds;
    }
    
    protected boolean isTimerFinished() {
//	System.out.println((timerLength >= 0 && Timer.getFPGATimestamp() >= startTime + timerLength) + " " + 
//					    (Timer.getFPGATimestamp() - (startTime + timerLength)));
	return timerLength >= 0 && Timer.getFPGATimestamp() >= startTime + timerLength;
    }
    
}
