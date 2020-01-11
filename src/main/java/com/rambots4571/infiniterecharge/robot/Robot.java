package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.command.ReadColors;
import com.rambots4571.rampage.joystick.DriveStick;
import com.rambots4571.rampage.joystick.Gamepad;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

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
    public void teleopInit() {
        (new ReadColors()).schedule();
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }
}
