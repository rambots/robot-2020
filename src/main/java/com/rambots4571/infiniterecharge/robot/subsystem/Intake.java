package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.rambots4571.infiniterecharge.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
    private static Intake instance;
    private VictorSPX IntakeMotor;

    private Intake () {
        IntakeMotor = new VictorSPX(Constants.Intake.IntakeMotor);

    }


}
