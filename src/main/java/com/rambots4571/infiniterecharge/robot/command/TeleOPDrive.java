package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleOPDrive extends CommandBase {

    public TeleOPDrive() {
        addRequirements(RobotContainer.drivetrain);
    }

    @Override
    public void execute() {
        RobotContainer.drivetrain.setSpeed(RobotContainer.leftStick.getYAxis(),
                RobotContainer.rightStick.getYAxis());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
