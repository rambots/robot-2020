package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.subsystem.Pneumatics;
import com.rambots4571.rampage.joystick.DriveStick;

public class RobotContainer {
    public static DriveStick stick = new DriveStick(0);

    public RobotContainer() {
        configureBindings();
    }

    public void configureBindings() {
    }
}
