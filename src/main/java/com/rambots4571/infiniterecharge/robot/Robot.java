package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.subsystem.Indexer;
import com.rambots4571.infiniterecharge.robot.subsystem.RobotContainer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {}

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().clearButtons();
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void disabledPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        RobotContainer.leftStick.getButton6().toggle(() -> Indexer.getInstance().setIntake(0.5),)
    }

    @Override
    public void teleopPeriodic() {}
}
