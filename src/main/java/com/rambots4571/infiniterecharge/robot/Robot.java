package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.command.SpinToColor;
import com.rambots4571.infiniterecharge.robot.command.SpinWheelThreeTimes;
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
        //        (new ReadColors()).schedule();
        leftStick.getButton5().whenPressed(new SpinWheelThreeTimes(), false);
        leftStick.getButton6().whenPressed(new SpinToColor(), false);
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }
}
