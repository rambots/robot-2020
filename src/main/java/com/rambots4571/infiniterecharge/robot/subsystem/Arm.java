package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.rambots4571.infiniterecharge.robot.Constants;
import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private static Arm instance;
    private WPI_TalonSRX wheelSpinner;
    private ColorSensorV3 colorSensor;
    // TODO: find the values of the colors on the wheel using the read colors
    //  command
    private Color blue = ColorMatch.makeColor(0, 0, 0);
    private Color green = ColorMatch.makeColor(0, 0, 0);
    private Color red = ColorMatch.makeColor(0, 0, 0);
    private Color yellow = ColorMatch.makeColor(0, 0, 0);
    private ColorMatch colorMatcher;

    private Arm() {
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        wheelSpinner = new WPI_TalonSRX(Constants.Arm.wheelMotor);
        colorMatcher = new ColorMatch();
        colorMatcher.addColorMatch(blue);
        colorMatcher.addColorMatch(green);
        colorMatcher.addColorMatch(red);
        colorMatcher.addColorMatch(yellow);
    }

    public static Arm getInstance() {
        if (instance == null)
            synchronized (Arm.class) {
                instance = new Arm();
            }
        return instance;
    }

    public void setWheelSpinner(double power) {
        wheelSpinner.set(power);
    }

    public ColorTarget getColor() {
        ColorMatchResult match = colorMatcher.matchClosestColor(
                colorSensor.getColor());
        if (match.color == blue) return ColorTarget.Blue;
        else if (match.color == green) return ColorTarget.Green;
        else if (match.color == red) return ColorTarget.Red;
        else if (match.color == yellow) return ColorTarget.Yellow;
        else return ColorTarget.Unknown;
    }

    public Color getDetectedColor() {
        return colorSensor.getColor();
    }
}
