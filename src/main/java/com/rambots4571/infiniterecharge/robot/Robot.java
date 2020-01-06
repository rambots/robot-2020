package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.rampage.joystick.DriveStick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
    public final static DriveStick leftStick = new DriveStick(0);
    public final static DriveStick rightStick = new DriveStick(1);

    @Override
    public void robotInit() {}

    @Override
    public void disabledInit() {}

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {}
}
