package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.rambots4571.infiniterecharge.robot.Constants;
import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.rambots4571.rampage.hardware.DoubleSolenoid;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private DoubleSolenoid piston;
    private double confidence;

    private Arm() {
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        wheelSpinner = new WPI_TalonSRX(Constants.Arm.wheelMotor);
        wheelSpinner.setNeutralMode(NeutralMode.Brake);
        colorMatcher = new ColorMatch();
        colorMatcher.addColorMatch(blue);
        colorMatcher.addColorMatch(green);
        colorMatcher.addColorMatch(red);
        colorMatcher.addColorMatch(yellow);
        colorMatcher.setConfidenceThreshold(0.93);

        Compressor compressor = new Compressor();
        piston = new DoubleSolenoid(
                Constants.Arm.pistonForward, Constants.Arm.pistonReverse);
    }

    public static synchronized Arm getInstance() {
        if (instance == null) instance = new Arm();
        return instance;
    }

    @Override
    public void periodic() {
        Color color = getDetectedColor();
        SmartDashboard.putString("RGB", String
                .format("rgb(%.3f, %.3f, %.3f)", color.red,
                        color.green, color.blue));
        SmartDashboard.putString("Detected Color", getColor().toString());
        SmartDashboard.putNumber("Confidence", getConfidence());
    }

    public void setWheelSpinner(double power) {
        wheelSpinner.set(power);
    }

    public void pushIn() {
        piston.set(Value.kForward);
    }

    public void pushOut() {
        piston.set(Value.kReverse);
    }

    public void togglePiston() {
        piston.toggle();
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
