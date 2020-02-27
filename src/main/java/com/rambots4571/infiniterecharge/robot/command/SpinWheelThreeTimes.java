package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.rambots4571.infiniterecharge.robot.subsystem.Arm;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinWheelThreeTimes extends CommandBase {
    private Arm arm = Arm.getInstance();
    private ColorTarget initialColor;
    private ColorTarget currentColor;
    private int counter;

    public SpinWheelThreeTimes() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        // storing the color the arm first sees
        initialColor = arm.getColor();
        // if the color is unknown, the command will cancel.
        if (initialColor == ColorTarget.Unknown) {
            System.out.println(
                    "spin three times command will not run, color unknown");
            cancel();
        }
        currentColor = initialColor;
    }

    @Override
    public void execute() {
        ColorTarget prevColor = currentColor;
        currentColor = arm.getColor();
        // when the color changes, and it is the same color as the initial
        // color, increment counter.
        if (prevColor != currentColor && currentColor == initialColor) counter++;
        arm.setWheelSpinner(0.4);
    }

    @Override
    public boolean isFinished() {
        // stop the arm once the counter hits 7 (3.5 revolutions)
        return counter == 7;
    }

    @Override
    public void end(boolean interrupted) {
        arm.stopWheelSpinner();
    }
}
