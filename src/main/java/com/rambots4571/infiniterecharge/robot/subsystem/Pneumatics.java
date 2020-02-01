package com.rambots4571.infiniterecharge.robot.subsystem;

import com.rambots4571.infiniterecharge.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
    private static Pneumatics instance;
    private DoubleSolenoid piston;

    private Pneumatics() {
        piston = new DoubleSolenoid(
                Constants.Pneumatics.pistonForward,
                Constants.Pneumatics.pistonReverse);
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
