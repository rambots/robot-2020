package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.command.SpinWheelThreeTimes;
import com.rambots4571.rampage.joystick.DriveStick;
import com.rambots4571.rampage.joystick.Gamepad;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
    public final static DriveStick leftStick = new DriveStick(
            Constants.Controller.leftStick);
    public final static DriveStick rightStick = new DriveStick(
            Constants.Controller.rightStick);
    public final static Gamepad gamepad = new Gamepad(
            Constants.Controller.gamepad);

    @Override
    public void robotInit() {}

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {
        gamepad.getRightBumper().whenPressed(new SpinWheelThreeTimes());
    }
}
