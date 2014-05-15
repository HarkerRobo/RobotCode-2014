/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2014.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 * @author 1072
 */
public class Pneumatics extends Subsystem {
    
    private final Compressor compressor;
    private Relay spike;
    
    public Pneumatics() {
        compressor = new Compressor(8,8);
        compressor.start();
        
        //spike = new Relay(8);
        
        //LiveWindow.addSensor("Pneumatics", "Compressor", compressor);
    }
    
    public boolean getPressureSwitch() {
	//return compressor.getPressureSwitchValue();
        return false;
    }
    
    public void setPressureSwitch(int dir) {
        if(dir>0)
            compressor.setRelayValue(Relay.Value.kForward);
        else if(dir==0)
            compressor.setRelayValue(Relay.Value.kOff);
        else if(dir<0)
            compressor.setRelayValue(Relay.Value.kReverse);
//        if(dir>0)
//            spike.set(Relay.Value.kForward);
//        else if(dir==0)
//            spike.set(Relay.Value.kOff);
//        else if(dir<0)
//            spike.set(Relay.Value.kReverse);
    }
    
    public void initDefaultCommand() {
    }
}
