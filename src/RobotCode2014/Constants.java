package RobotCode2014;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 * Constants for things on the robot
 *
 * @author Brian Chan
 * @version
 */
public class Constants {
    
    private static Preferences prefs;
    
    public static double encoderRateScaling;

    private static void initializePreferences() {
	prefs = Preferences.getInstance();
    }

    public static void updateConstants() {
	if(prefs == null) initializePreferences();

	encoderRateScaling = prefs.getDouble("EncoderRateScaling", 5);
	
	DrivetrainConst.updateConstants();
	ManipulatorConst.updateConstants();
    }
    
    /*
     * Table framework for the debugging values 
     * @author Manan
     */
    
    static NetworkTable ntable = NetworkTable.getTable("pitch table");
    public static Hashtable table = new Hashtable();

    /**
     * Populates the hashmap with the values from the NetworkTable. Only call this
     * once when initially setting up the table
     * @param maxDist the maximum distance needed 
     */
    public void populateTable(double maxDist) {
        for(double i=0; i<maxDist; i+=0.01) {
            if(ntable.containsKey(Double.toString(i)))
                table.put(Double.valueOf(i), Double.valueOf(ntable.getNumber(String.valueOf(i))));
        }
    }
    
    /**
     * Updates the hashmap with the values in the NetworkTable. Call this after the 
     * initial population of the table
     * @param maxDist the maximum distance needed
     */
    public void updateTable(double maxDist) 
    {
        for(double i=0; i<maxDist; i+=0.01) {
            if(ntable.containsKey(Double.toString(i)) && !table.containsKey(Double.toString(i)))
                table.put(Double.valueOf(i), Double.valueOf(ntable.getNumber(String.valueOf(i))));
        }
        for(double i=0; i<maxDist; i+=0.01) {
            if(!ntable.containsKey(Double.toString(i)) && table.containsKey(Double.toString(i)))
                table.remove(Double.valueOf(i));
        }
    }
    
    /*
     * Other methods for the hashtable that might be needed
     */
    
    public Object getValue(String key){ return table.get(key); }
    //warning: does NOT remove values from the network table. 
    public Object remove(String key){ return table.remove(key); }
    //warning: does NOT update the network table. 
    public void rawAdd(String key, double value) { table.put(key, Double.valueOf(value)); }
    public Enumeration getKeys() { return table.keys(); }
    public Enumeration getValues() { return table.elements(); }
    
    public static class DrivetrainConst {

	public static double leftEncDistPerPulse, rightEncDistPerPulse;
	public static boolean leftFrontFlip, leftBackFlip, rightFrontFlip, rightBackFlip;

	public static double driveToPointScaling;
        
        public static double joystickScaleFactor;
	public static double driveStraightScaling;
	public static double turnToAngleError;
	public static double accelerationScaling;
	public static double accelerationThreshold;
	public static double manualOverrideThreshold, manualOverrideResetThreshold;
        
        //the factor of scaling  
        public static double lowSpeedScaling, highSpeedScaling;

	private static void updateConstants() {
	   
	    // -----------------------------
	    //	Encoder distance per pulse
	    //	    experimental values: ticks per cycle * distance moved (manually) / encoder pulses
	    //       note: next encoders are 250
	    // -----------------------------
	    leftEncDistPerPulse	    = prefs.getDouble("DT_leftEncScaling", 4*5.0/22383);
	    rightEncDistPerPulse    = prefs.getDouble("DT_rightEncScaling", 4*5.0/21685);
	    
	    
	    // -----------------------------
	    //	Motor controller direction
	    //	    Whether or not each drive train motor is flipped
	    // -----------------------------
	    
	    leftFrontFlip   = prefs.getBoolean("DT_LFFlip", true);
	    leftBackFlip    = prefs.getBoolean("DT_LBFlip", true);
	    rightFrontFlip  = prefs.getBoolean("DT_RFFlip", false);
	    rightBackFlip   = prefs.getBoolean("DT_RBFlip", false);
	    
	    
	    // -----------------------------
	    //	Command-specific constants
	    // -----------------------------
	    
	    //acceleration code
	    accelerationScaling = prefs.getDouble("DT_AccelScale", .7);
	    accelerationThreshold = prefs.getDouble("DT_AccelThreshold", .3);
	    
	    //driveStriaght - amount by which to scale
	    driveStraightScaling = prefs.getDouble("DT_DriveStraightScaling", .3);
            
            //scales the joystick values by a specified factor for the JoystickScaleDriveCommand
	    lowSpeedScaling = prefs.getDouble("DT_LowSpeedScaling", .7);
            highSpeedScaling = prefs.getDouble("DT_HighSpeedScaling", .9);
            
	    //turnToAngle - acceptable angular distance from destination for command to be considered complete
	    turnToAngleError = prefs.getDouble("DT_TurnToAngleError", .2);
	    
	    //drive to point
	    driveToPointScaling = prefs.getDouble("DT_DriveToPointScaling", .3); //TODO: needs more testing

	    //analogue stick threshold before interrupting command and giving control back to player
	    manualOverrideThreshold = prefs.getDouble("DT_ManualOverrideThreshold", .5);
            manualOverrideResetThreshold = prefs.getDouble("DT_ManualOverrideResetThreshold", .3);
            joystickScaleFactor = prefs.getDouble("DT_JoystickScaleFactor", .4);

	}
    }
    
    public static class ManipulatorConst {
        
        
        public static double p, i, d; //currently being used for movearmtopitch, not PID (I and D not used at all
        public static double PIDTolerance; //currently being used for movearmtopitch tolerance, not PID
        
        public static double pitchEncLowest; //the lowest allowed angle for the manipulator 
        public static double pitchEncHighest;//the highest allowed angle for the manipulator
        
        public static double pitchEncRadPerPulse;
                
        public static double pitchAccelerationScaling;
        
        public static double pitchAccelerationThreshold;
        
        public static double retractAfterLaunchDelay;
        public static double launchAfterOpenClampDelay;
		
	public static double retractionSpeedScaling;
        
        private static void updateConstants() {
            p = prefs.getDouble("MP_P", 1.0); //TODO: get pid values
            i = prefs.getDouble("MP_I", 0.0);
            d = prefs.getDouble("MP_D", 0.0);
            PIDTolerance = prefs.getDouble("MP_Tolerance", 0.07);
            
            pitchEncRadPerPulse = prefs.getDouble("MP_PitchEncRadPerPulse", 105*Math.PI/180 / 883); //theoretical value: 2*Math.PI/4096
            
            pitchAccelerationScaling = prefs.getDouble("MP_PitchTalonScaling", .5);
            
            pitchAccelerationThreshold = prefs.getDouble("MP_PitchAccelerationThreshold", .3);
            
            pitchEncLowest  = prefs.getDouble("pitch_Enc_Lowest", -34.0*Math.PI/180);
            pitchEncHighest = prefs.getDouble("pitch_Enc_Lowest", 0);
            
            // -----------------------------
	    //	Command-specific constants
	    // -----------------------------
            retractAfterLaunchDelay = prefs.getDouble("MP_RetractAfterLaunchDelay", 1);
            launchAfterOpenClampDelay = prefs.getDouble("MP_LaunchAfterOpenClampDelay", .5);
	    	
	    retractionSpeedScaling = prefs.getDouble("MP_RetractionSpeedScaling", .2);
        }
    }
}
