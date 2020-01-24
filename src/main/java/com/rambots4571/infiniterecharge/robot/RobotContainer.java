package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.subsystem.Drivetrain;
import com.rambots4571.rampage.joystick.DriveStick;
import com.rambots4571.rampage.joystick.Gamepad;

public class RobotContainer {
    public final static DriveStick leftStick = new DriveStick(
            Constants.Controller.leftStick);
    public final static DriveStick rightStick = new DriveStick(
            Constants.Controller.rightStick);
    public final static Gamepad gamepad = new Gamepad(
            Constants.Controller.gamepad);

}
