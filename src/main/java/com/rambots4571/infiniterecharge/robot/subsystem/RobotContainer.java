package com.rambots4571.infiniterecharge.robot.subsystem;

import com.rambots4571.infiniterecharge.robot.Constants;
import com.rambots4571.rampage.joystick.DriveStick;

public class RobotContainer {
    public static final DriveStick leftStick = new DriveStick(
            Constants.Controller.leftStick);
    public static final DriveStick rightStick = new DriveStick(
            Constants.Controller.rightStick);
}
