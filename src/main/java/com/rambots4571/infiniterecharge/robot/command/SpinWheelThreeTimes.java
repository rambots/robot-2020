package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.rambots4571.infiniterecharge.robot.subsystem.Arm;
import com.rambots4571.rampage.function.SwitchAction;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinWheelThreeTimes extends CommandBase {
    private Arm arm = Arm.getInstance();
    private ColorTarget colorTarget;
    private SwitchAction<ColorTarget> color;
    private int counter;

    public SpinWheelThreeTimes() {
        addRequirements(arm);
        color = new SwitchAction<>(arm::getColor);
    }

    @Override
    public void initialize() {
        colorTarget = arm.getColor();
    }

    @Override
    public void execute() {
        color.whenDiff(() -> {
            if (arm.getColor() == colorTarget) counter++;
        });
        arm.setWheelSpinner(0.5);
    }

    @Override
    public boolean isFinished() {
        return counter == 7;
    }

    @Override
    public void end(boolean interrupted) {
        arm.setWheelSpinner(0.0);
    }
}
