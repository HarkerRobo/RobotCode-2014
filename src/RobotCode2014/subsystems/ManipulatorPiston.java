/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.subsystems;

import RobotCode2014.Constants;
import RobotCode2014.wrappers.PneumaticsWrapper;
import RobotCode2014.wrappers.TalonWrapper;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 * @author me
 */
public class ManipulatorPiston extends Subsystem {

    private final PneumaticsWrapper launcherSolenoids;	//controls piston that shoots ball and unwinds winch
    private final TalonWrapper pistonTalon1;	//controls winch that pulls back the piston after shooting
    private final TalonWrapper pistonTalon2;	//controls winch that pulls back the piston after shooting
    private final DigitalInput pistonLS;        //determines whether launcher piston is completely retracted (limit switch)

    public ManipulatorPiston() {
        launcherSolenoids = new PneumaticsWrapper(1, 2);
        pistonTalon1 = new TalonWrapper(5);
        pistonTalon2 = new TalonWrapper(6);
        pistonLS = new DigitalInput(11);
        
        LiveWindow.addActuator("Manipulator", "Piston Talon 1", pistonTalon1);
        LiveWindow.addActuator("Manipulator", "Piston Talon 2", pistonTalon2);
        LiveWindow.addActuator("Manipulator", "Piston LS", pistonLS);
    }

    public void unlockPiston() {
        launcherSolenoids.set(false);
    }

    public void lockPiston() {
        launcherSolenoids.set(true);
    }

//    public void retractPistonSmoothly(double speed) {
//        //Regardless of how the speed is sent to retract piston, the piston needs to move in and therefore 
//        //must be negative.
//        double s = -Math.abs(speed);
//        //Difference between target speed and current speeds.
//        double diff1 = s - pistonTalon1.get();
//        double diff2 = s - pistonTalon2.get();
//        //Gradually arrive at the target speed by scaling the difference by a constant.
//        pistonTalon1.set(pistonTalon1.get() + diff1 * Constants.ManipulatorConst.retractionSpeedScaling);
//        pistonTalon2.set(pistonTalon2.get() + diff2 * Constants.ManipulatorConst.retractionSpeedScaling);
//        System.out.println("retract speed: " + pistonTalon1.get() + diff1 * Constants.ManipulatorConst.retractionSpeedScaling);
//    }

    public void retractPiston(double speed) {
        pistonTalon1.set(-Math.abs(speed));
        pistonTalon2.set(-Math.abs(speed));
    }

    public boolean isPistonRetracted() {
        return pistonLS.get();
    }

    public void initDefaultCommand() {
    }
}
