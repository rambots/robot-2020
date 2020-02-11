package com.rambots4571.infiniterecharge.robot.subsystem;

import com.rambots4571.infiniterecharge.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private static Drivetrain instance;

    private CANSparkMax leftMaster =
            new CANSparkMax(
                    Constants.Drive.leftMaster,
                    CANSparkMaxLowLevel.MotorType.kBrushless);

    private CANSparkMax rightMaster =
            new CANSparkMax(
                    Constants.Drive.rightMaster,
                    CANSparkMaxLowLevel.MotorType.kBrushless);

    private Drivetrain() {
        CANSparkMax leftFollowerOne =
                new CANSparkMax(
                        Constants.Drive.leftFollowerOne,
                        CANSparkMaxLowLevel.MotorType.kBrushless);
        leftFollowerOne.follow(leftMaster);
        CANSparkMax leftFollowerTwo =
                new CANSparkMax(
                        Constants.Drive.leftFollowerTwo,
                        CANSparkMaxLowLevel.MotorType.kBrushless);
        leftFollowerTwo.follow(leftMaster);

        CANSparkMax rightFollowerOne =
                new CANSparkMax(
                        Constants.Drive.rightFollowerOne,
                        CANSparkMaxLowLevel.MotorType.kBrushless);
        rightFollowerOne.follow(rightMaster);
        CANSparkMax rightFollowerTwo =
                new CANSparkMax(
                        Constants.Drive.rightFollowerTwo,
                        CANSparkMaxLowLevel.MotorType.kBrushless);
        rightFollowerTwo.follow(rightMaster);

        rightMaster.setInverted(true);
    }

    public static Drivetrain getInstance() {
        synchronized (Drivetrain.class) {
            if (instance == null) instance = new Drivetrain();
        }
        return instance;
    }

    public void setSpeed(double left, double right) {
        leftMaster.set(left);
        rightMaster.set(right);
    }

    public void stop() {
        setSpeed(0, 0);
    }
}
