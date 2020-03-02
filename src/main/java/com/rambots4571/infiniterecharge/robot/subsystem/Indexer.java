package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.rambots4571.infiniterecharge.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
    private static Indexer instance;
    private WPI_TalonSRX intakeMotor;
    private WPI_TalonSRX conveyorMotor;
    private WPI_TalonSRX feederMotor;
    private DigitalInput intakeSensor;
    private DigitalInput conveyorSensor;
    private DigitalInput topSensor;
    private boolean currentIntake;
    private boolean currentChamber;
    private boolean currentTop;

    private Indexer() {
        intakeMotor = new WPI_TalonSRX(
                Constants.Indexer.intakeMotor);
        intakeMotor.setInverted(true);

        feederMotor = new WPI_TalonSRX(
                Constants.Indexer.feederMotor);

        conveyorMotor = new WPI_TalonSRX(
                Constants.Indexer.conveyorMotor);
        conveyorMotor.setInverted(true);

        intakeSensor = new DigitalInput(Constants.Indexer.intakeSensor);
        currentIntake = isCellInIntake();

        conveyorSensor = new DigitalInput(Constants.Indexer.conveyorSensor);
        currentChamber = isCellInChamber();

        topSensor = new DigitalInput(Constants.Indexer.topSensor);
        currentTop = isCellInTop();
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("IR Sensor", isCellInIntake());
    }

    public static synchronized Indexer getInstance() {
        if (instance == null) instance = new Indexer();
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

    public boolean isCellInIntake() {
        return !intakeSensor.get();
    }

    public boolean isCellInChamber() {
        return !conveyorSensor.get();
    }

    public boolean isCellInTop() {
        return !topSensor.get();
    }

    public boolean didBallEnterIntake() {
        boolean prev = currentIntake;
        currentIntake = isCellInIntake();
        return !prev && currentIntake;
    }

    public boolean didBallReachChamber() {
        boolean prev = currentChamber;
        currentChamber = isCellInChamber();
        return !prev && currentChamber;
    }

    public boolean didCellLeaveChamber() {
        boolean prev = currentChamber;
        currentChamber = isCellInChamber();
        return prev && !currentChamber;
    }

    public boolean didBallReachTop() {
        boolean prev = currentTop;
        currentTop = isCellInTop();
        return !prev && currentTop;
    }
}
