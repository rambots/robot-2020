package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.rambots4571.infiniterecharge.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
    private static Indexer instance;
    private WPI_TalonSRX intakeMotor = new WPI_TalonSRX(
            Constants.Indexer.intakeMotor);
    private WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(
            Constants.Indexer.conveyorMotor);
    private WPI_TalonSRX feederMotor = new WPI_TalonSRX(
            Constants.Indexer.feederMotor);

    private Indexer() {
        conveyorMotor.setInverted(true);
        feederMotor.setInverted(true);
    }

    public static Indexer getInstance() {
        if (instance == null) {
            synchronized (Indexer.class) {
                instance = new Indexer();
            }
        }
        return instance;
    }

    public void setIntake(double speed) {
        intakeMotor.set(speed);
    }

    public void setConveyor(double speed) {
        conveyorMotor.set(speed);
    }

    public void setFeeder(double speed) {
        feederMotor.set(speed);
    }
    public void stopConveyor() {
        setConveyor(0);
    }

    public void stopIntake() {
        setIntake(0);
    }

    public void stopFeeder() {
        setFeeder(0);
    }
}
