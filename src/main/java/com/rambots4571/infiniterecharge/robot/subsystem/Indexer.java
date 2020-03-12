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
    private DigitalInput chamberSensor;
    private DigitalInput topSensor;
    private boolean currentChamber;

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

        chamberSensor = new DigitalInput(Constants.Indexer.chamberSensor);
        currentChamber = isCellInChamber();

        topSensor = new DigitalInput(Constants.Indexer.topSensor);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("intake Sensor", isCellInIntake());
        SmartDashboard.putBoolean("chamber sensor", isCellInChamber());
        SmartDashboard.putBoolean("top sensor", isCellInTop());
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
        return !chamberSensor.get();
    }

    public boolean isCellInTop() {
        return !topSensor.get();
    }

    /**
     * @return true the moment the sensor goes false->true
     */
    public boolean didCellReachChamber() {
        boolean prev = currentChamber;
        currentChamber = isCellInChamber();
        return !prev && currentChamber;
    }
}
