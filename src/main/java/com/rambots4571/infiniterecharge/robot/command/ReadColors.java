package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.subsystem.Arm;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ReadColors extends CommandBase {
    private Arm arm = Arm.getInstance();

    public ReadColors() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Color color = arm.getColor();
        SmartDashboard.putNumber("Red", color.red);
        SmartDashboard.putNumber("Green", color.green);
        SmartDashboard.putNumber("Blue", color.blue);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
