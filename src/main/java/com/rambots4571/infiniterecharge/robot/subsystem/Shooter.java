package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.rambots4571.infiniterecharge.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private static Shooter instance;
    private static VictorSPX ShooterMotor;
    private Shooter () {
    ShooterMotor = new VictorSPX(Constants.Shooter.ShooterMotor);
    }
}
