package RobotCode2014.subsystems;

import RobotCode2014.Constants;
import RobotCode2014.OI;
import RobotCode2014.commands.manipulator.PitchAccelerationCommand;
import RobotCode2014.wrappers.EncoderWrapper;
import RobotCode2014.wrappers.PneumaticsWrapper;
import RobotCode2014.wrappers.TalonWrapper;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * All angles are in radians, make sure that nowhere uses degrees!
 *
 * @author Brian Chan
 * @author Manan Shah
 * @author Ben Huchley
 */
public class ManipulatorPitch extends Subsystem {

    private final TalonWrapper pitchTalon;		//controls arm pitch
    private final DigitalInput minPitchLS;	//determines whether arm is at limits of movement

    private final Encoder pitchEnc;	//encoder for arm pitch

    private final DigitalInput source1, source2;
    private double targetSpeed;
    private double currSpeed;

    public ManipulatorPitch() {

        pitchTalon = new TalonWrapper(7);
        minPitchLS = new DigitalInput(6);

//        pitchEnc = new EncoderWrapper(10, 12);
        source1 = new DigitalInput(12);
        source2 = new DigitalInput(13);
        pitchEnc = new Encoder(source1, source2);
        pitchEnc.setDistancePerPulse(Constants.ManipulatorConst.pitchEncRadPerPulse);
        pitchEnc.start(); //assume robot will start with max pitch (arm all the way up)
        pitchEnc.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);

        targetSpeed = currSpeed = 0;
        
        LiveWindow.addActuator("Manipulator", "Pitch Talon", pitchTalon);
        LiveWindow.addSensor("Manipulator", "Pitch encoder", pitchEnc);
        LiveWindow.addSensor("Manipulator", "Min pitch LS", minPitchLS);

    }

    public boolean source1() {
        return source1.get();
    }

    public boolean source2() {
        return source2.get();
    }

    public void setTargetPitchSpeed(double speed) {
        setPitchSpeedRaw(speed);
//        setPitchSpeedRaw(speed);
//        targetSpeed = speed;
    }
    
    public void updatePitchSpeed() {
        currSpeed = scale(targetSpeed, currSpeed, Constants.ManipulatorConst.pitchAccelerationThreshold, Constants.ManipulatorConst.pitchAccelerationScaling);
        double angle = pitchEnc.getDistance();
        if(Constants.ManipulatorConst.pitchEncLowest < angle && angle < Constants.ManipulatorConst.pitchEncHighest)
            setPitchSpeedRaw(currSpeed);
        else
            setPitchSpeedRaw(0);
    }
    
    public void setPitchSpeedRaw(double speed) {
        System.out.println(speed);
        pitchTalon.set(speed); 
    }
    
    private double scale(double val, double curr, double threshold, double scaling) {
	return (Math.abs(val-curr) < threshold)
		? val
                : (val*scaling + curr*(1-scaling)); 	//weighted average of previous and intended values
    }

    /**
     * @return answer in terms of ticks.
     */
    public int getPitchRaw() {
        return pitchEnc.get();
    }

    public double getPitchRads() {
        return pitchEnc.getDistance();
    }

    public void initDefaultCommand() {
//        setDefaultCommand(OI.manualPitch);
    }

    public boolean isMinPitch() {
        return !minPitchLS.get();
    }

    /**
     * calculatePitch calculates the shooter pitch
     *
     * @param distance - distance away from goal
     * @return the calculated pitch
     */
    public double calculatePitch(double distance) {
        return 30 * Math.PI / 180;  // TODO: please fix for obvious reasons
    }

    protected double returnPIDInput() {
        return pitchEnc.pidGet();
    }

    protected void usePIDOutput(double d) {
        pitchTalon.pidWrite(d);
    }
}