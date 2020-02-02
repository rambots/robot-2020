package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.subsystem.Pneumatics;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    RobotContainer container;

    @Override
    public void robotInit() {
        container = new RobotContainer();
        Pneumatics.getInstance();
    }

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
        RobotContainer.stick.getButton1().whenPressed(
                Pneumatics.getInstance()::togglePiston);
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }
}
