package com.rambots4571.infiniterecharge.robot.subsystem;

import com.rambots4571.infiniterecharge.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private static Arm instance;
    private ColorSensorV3 colorSensor;
    private CANSparkMax wheelSpinner;

    private Arm() {
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        wheelSpinner = new CANSparkMax(
                Constants.Arm.wheelMotor,
                CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public static Arm getInstance() {
        if (instance == null)
            synchronized (Arm.class) {
                instance = new Arm();
            }
        return instance;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addStringProperty("Color", getColor()::toString, null);
    }

    public void setWheelSpinner(double power) {
        wheelSpinner.set(power);
    }

    public Color getColor() {
        return colorSensor.getColor();
    }
}
