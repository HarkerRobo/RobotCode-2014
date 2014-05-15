package RobotCode2014.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 * @author Brian Chan
 */
public class Networking extends Subsystem {
    
    NetworkTable imageProcessing;
    
    public Networking() {
	imageProcessing = NetworkTable.getTable("imageProcessing");
    }
    
    public double getGoalDistance() {
	return imageProcessing.getNumber("distance", -1);
    }
    
    public double getGoalAngle() {
	return imageProcessing.getNumber("angle", -1);
    }
    
    public boolean isGoalHot() {
	return imageProcessing.getBoolean("hotness", false);
    }
    
    public void initDefaultCommand() {
    }
}