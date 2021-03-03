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
    public void execute() {
        Color color = arm.getRawColor();
        SmartDashboard.putString(
                "raw rgb", String.format("rgb(%.3f, %.3f, %.3f)", color.red,
                                         color.green, color.blue));
        SmartDashboard.putString("Color Name?", arm.getColor().toString());
        SmartDashboard.putNumber("confidence", arm.getConfidence());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
