package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.subsystem.Indexer;
import com.rambots4571.rampage.joystick.DriveStick;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {
    public static final DriveStick leftStick = new DriveStick(
            Constants.Controller.leftStick);
    public static final DriveStick rightStick = new DriveStick(
            Constants.Controller.rightStick);

    public void configureButtonBindings() {
        leftStick.getButton4().toggle(
                () -> Indexer.getInstance().setFeeder(1),
                Indexer.getInstance()::stopFeeder);
    }

    public void initTeleopCommands() {
        (new RunCommand(() -> {
            Indexer.getInstance().setIntake(
                    RobotContainer.leftStick.getYAxis());
            Indexer.getInstance().setConveyor(
                    RobotContainer.rightStick.getYAxis());
        }, Indexer.getInstance())).schedule();
    }
}
