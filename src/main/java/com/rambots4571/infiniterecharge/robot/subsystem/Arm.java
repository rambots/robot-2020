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

/**
 * Subsystem that is used to spin the control panel.
 * - {@link DoubleSolenoid} used to actuate the arm up in down.
 * - {@link ColorSensorV3} used to detect the colors.
 * - {@link WPI_TalonSRX} used to control the motor what spins the wheel.
 */
public class Arm extends SubsystemBase {
    private static Arm instance;
    private final WPI_TalonSRX wheelSpinner;
    private final ColorSensorV3 colorSensor;
    private final DoubleSolenoid piston;
    /**
     * store raw RGB values to compare later using {@link ColorMatch}.
     */
    private final Color blue = ColorMatch.makeColor(0.112, 0.426, 0.457);
    private final Color green = ColorMatch.makeColor(0.159, 0.589, 0.252);
    private final Color red = ColorMatch.makeColor(0.523, 0.341, 0.133);
    private final Color yellow = ColorMatch.makeColor(0.315, 0.565, 0.119);
    private final ColorMatch colorMatcher;
    private double confidence;

    private Arm() {
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        wheelSpinner = new WPI_TalonSRX(Constants.Arm.wheelMotor);
        wheelSpinner.setNeutralMode(NeutralMode.Brake);
        colorMatcher = new ColorMatch();
        // adding the colors the sensor should look for
        colorMatcher.addColorMatch(blue);
        colorMatcher.addColorMatch(green);
        colorMatcher.addColorMatch(red);
        colorMatcher.addColorMatch(yellow);
        colorMatcher.setConfidenceThreshold(0.93);

        new Compressor();
        piston = new DoubleSolenoid(
                Constants.Arm.pistonForward, Constants.Arm.pistonReverse);
    }

    public static synchronized Arm getInstance() {
        if (instance == null) instance = new Arm();
        return instance;
    }

    @Override
    public void periodic() {
        Color color = getRawColor();
        SmartDashboard.putString("RGB", String
                .format("rgb(%.3f, %.3f, %.3f)", color.red,
                        color.green, color.blue));
        SmartDashboard.putString("Detected Color", getColor().toString());
        SmartDashboard.putNumber("Confidence", getConfidence());
    }

    public void setWheelSpinner(double power) {
        wheelSpinner.set(power);
    }

    public void stopWheelSpinner() {
        setWheelSpinner(0);
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

    /**
     * Used to get the color it detects in an enum form.
     *
     * @return the color {@link ColorTarget}.
     */
    public ColorTarget getColor() {
        ColorMatchResult match = colorMatcher.matchColor(getRawColor());
        if (match == null) return ColorTarget.Unknown;
        confidence = match.confidence;
        if (match.color == blue) return ColorTarget.Blue;
        else if (match.color == green) return ColorTarget.Green;
        else if (match.color == red) return ColorTarget.Red;
        else if (match.color == yellow) return ColorTarget.Yellow;
        else return ColorTarget.Unknown;
    }

    /**
     * @return the raw color.
     */
    public Color getRawColor() {
        return colorSensor.getColor();
    }

    public double getConfidence() {
        return confidence;
    }
}
