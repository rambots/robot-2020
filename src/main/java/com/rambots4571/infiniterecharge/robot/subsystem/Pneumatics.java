package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.rambots4571.infiniterecharge.robot.Constants;
import com.rambots4571.rampage.hardware.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
    private static Pneumatics instance;
    private DoubleSolenoid piston;
    private Solenoid singlePiston;

    private Pneumatics() {
        Compressor compressor = new Compressor(Constants.Pneumatics.compressor);
        compressor.setClosedLoopControl(true);
        piston = new DoubleSolenoid(
                Constants.Pneumatics.pistonForward,
                Constants.Pneumatics.pistonReverse);
        singlePiston = new Solenoid(Constants.Pneumatics.singlePiston);
        WPI_TalonSRX talon = new WPI_TalonSRX(4);
    }

    public static Pneumatics getInstance() {
        if (instance == null) {
            synchronized (Pneumatics.class) {
                instance = new Pneumatics();
            }
        }
        return instance;
    }

    public void pushInPiston() {
        piston.set(DoubleSolenoid.Value.kReverse);
    }

    public void pushOutPiston() {
        piston.set(DoubleSolenoid.Value.kForward);
    }

    public DoubleSolenoid.Value getPistonValue() {
        return piston.get();
    }

    public void setSinglePiston() {
        singlePiston.set(true);
    }

    public void releaseSinglePiston() {
        singlePiston.set(false);
    }

    public void toggleSinglePiston() {
        singlePiston.set(!singlePiston.get());
    }

    public void togglePiston() {
        piston.toggle();
    }
}
