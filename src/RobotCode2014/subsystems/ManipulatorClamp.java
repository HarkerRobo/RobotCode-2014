/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.subsystems;

import RobotCode2014.wrappers.PneumaticsWrapper;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author me
 */
public class ManipulatorClamp extends Subsystem {

    private final PneumaticsWrapper clampSolenoids;	//opens and closes "clamp"

    public ManipulatorClamp() {
        clampSolenoids = new PneumaticsWrapper(3, 4);
    }

    public void openClamp() {
//        clampSolenoids.set(true);
    }

    public void closeClamp() {
//        clampSolenoids.set(false);
    }

    public boolean isClampOpen() {
        return clampSolenoids.get();
    }

    public void initDefaultCommand() {
    }
}
