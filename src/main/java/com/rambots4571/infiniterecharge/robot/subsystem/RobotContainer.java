package com.rambots4571.infiniterecharge.robot.subsystem;

import com.rambots4571.infiniterecharge.robot.Constants;
import com.rambots4571.rampage.joystick.DriveStick;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {
    public static final DriveStick leftStick = new DriveStick(
            Constants.Controller.leftStick);
    public static final DriveStick rightStick = new DriveStick(
            Constants.Controller.rightStick);

    public void initializeButtonBindings() {

    }

    public void initTeleopCommands() {
        (new RunCommand(() -> {
            Indexer.getInstance().setIntake(leftStick.getYAxis());
            Indexer.getInstance().setConveyor(rightStick.getYAxis());
        }, Indexer.getInstance())).schedule();
    }
}
