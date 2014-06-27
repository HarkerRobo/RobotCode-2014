/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 * @author me
 */
public class ManipulatorCollector extends Subsystem {

    private final Relay collectorRelay;	//controls collector wheel

    public ManipulatorCollector() {
        collectorRelay = new Relay(5);

        LiveWindow.addActuator("Manipulator", "Collector spike", collectorRelay);
    }

    public void startCollector() {
        collectorRelay.set(Relay.Value.kReverse);
    }

    public void stopCollector() {
        collectorRelay.set(Relay.Value.kOff);
    }

    public void initDefaultCommand() {
    }

    public void ejectFromClamp() {
        collectorRelay.set(Relay.Value.kForward);
    }
}
