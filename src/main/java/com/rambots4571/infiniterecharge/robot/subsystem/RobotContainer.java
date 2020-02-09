package com.rambots4571.infiniterecharge.robot.subsystem;

import com.rambots4571.infiniterecharge.robot.Constants;
import com.rambots4571.rampage.joystick.DriveStick;

public class RobotContainer {
    public static final DriveStick leftStick = new DriveStick(
            Constants.Controller.leftStick);
    public static final DriveStick rightStick = new DriveStick(
            Constants.Controller.rightStick);

    public void initializeButtonBindings() {
        leftStick.getButton6().toggle(
                () -> Indexer.getInstance().setIntake(0.5),
                Indexer.getInstance()::stopIntake);

        leftStick.getButton4().toggle(
                () -> Indexer.getInstance().setIntake(-0.5),
                Indexer.getInstance()::stopIntake);

        leftStick.getButton5().toggle(
                () -> Indexer.getInstance().setConveyor(0.5),
                Indexer.getInstance()::stopConveyor);
    }
}
