package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.command.ReadColors;
import com.rambots4571.infiniterecharge.robot.command.TeleOPDrive;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

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
        (new TeleOPDrive()).schedule();
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }
}
