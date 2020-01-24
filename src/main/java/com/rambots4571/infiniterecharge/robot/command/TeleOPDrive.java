package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.RobotContainer;
import com.rambots4571.infiniterecharge.robot.subsystem.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleOPDrive extends CommandBase {
    private Drivetrain drivetrain = Drivetrain.getInstance();

    public TeleOPDrive() {
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.setSpeed(RobotContainer.leftStick.getYAxis(),
                RobotContainer.rightStick.getYAxis());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
