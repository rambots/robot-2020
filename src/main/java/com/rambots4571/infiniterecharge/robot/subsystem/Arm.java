package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.rambots4571.infiniterecharge.robot.Constants;
import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private static Arm instance;
    private WPI_TalonSRX wheelSpinner;
    private ColorSensorV3 colorSensor;
    private Color blue = ColorMatch.makeColor(0.112, 0.426, 0.457);
    private Color green = ColorMatch.makeColor(0.159, 0.589, 0.252);
    private Color red = ColorMatch.makeColor(0.523, 0.341, 0.133);
    private Color yellow = ColorMatch.makeColor(0.315, 0.565, 0.119);
    private ColorMatch colorMatcher;
    private double confidence;

    private Arm() {
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        wheelSpinner = new WPI_TalonSRX(Constants.Arm.wheelMotor);
        colorMatcher = new ColorMatch();
        colorMatcher.addColorMatch(blue);
        colorMatcher.addColorMatch(green);
        colorMatcher.addColorMatch(red);
        colorMatcher.addColorMatch(yellow);
        colorMatcher.setConfidenceThreshold(0.93);
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
        Color color = getDetectedColor();
        builder.addStringProperty("RGB", () -> String
                .format("rgb(%.3f, %.3f, %.3f)", color.red,
                        color.green, color.blue), null);
        builder.addStringProperty("Detected Color", getColor()::toString, null);
        builder.addDoubleProperty("Confidence", this::getConfidence, null);
    }

    public void setWheelSpinner(double power) {
        wheelSpinner.set(power);
    }

    public ColorTarget getColor() {
        ColorMatchResult match = colorMatcher.matchColor(getDetectedColor());
        if (match == null) return ColorTarget.Unknown;
        confidence = match.confidence;
        if (match.color == blue) return ColorTarget.Blue;
        else if (match.color == green) return ColorTarget.Green;
        else if (match.color == red) return ColorTarget.Red;
        else if (match.color == yellow) return ColorTarget.Yellow;
        else return ColorTarget.Unknown;
    }

    public Color getDetectedColor() {
        return colorSensor.getColor();
    }

    public double getConfidence() {
        return confidence;
    }
}
