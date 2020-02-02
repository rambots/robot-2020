package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.rambots4571.infiniterecharge.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
    private static Pneumatics instance;
    private DoubleSolenoid piston;

    private Pneumatics() {
        Compressor compressor = new Compressor(Constants.Pneumatics.compressor);
        compressor.setClosedLoopControl(true);
        piston = new DoubleSolenoid(
                Constants.Pneumatics.pistonForward,
                Constants.Pneumatics.pistonReverse);
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

    public void pushIn() {
        piston.set(DoubleSolenoid.Value.kReverse);
    }

    public void pushOut() {
        piston.set(DoubleSolenoid.Value.kForward);
    }

    public void toggle() {
        DoubleSolenoid.Value value =
                piston.get() == DoubleSolenoid.Value.kReverse ?
                DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse;
        piston.set(value);
    }
}
