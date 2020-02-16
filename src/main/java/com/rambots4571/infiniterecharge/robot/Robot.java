package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.command.SpinToColor;
import com.rambots4571.infiniterecharge.robot.command.SpinWheelThreeTimes;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import static com.rambots4571.infiniterecharge.robot.RobotContainer.leftStick;

public class Robot extends TimedRobot {
    private RobotContainer container;

    @Override
    public void robotInit() {
        container = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().clearButtons();
    }

    @Override
    public void disabledPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        container.initTeleopCommands();
        leftStick.getButton5().whenPressed(new SpinWheelThreeTimes(), false);
        leftStick.getButton6().whenPressed(new SpinToColor(), false);
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }
}
