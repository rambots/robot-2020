package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.rambots4571.infiniterecharge.robot.subsystem.Arm;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinWheelThreeTimes extends CommandBase {
    private Arm arm = Arm.getInstance();
    private ColorTarget colorTarget;
    private ColorTarget currentColor;
    private int counter;

    public SpinWheelThreeTimes() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        // storing the color the arm first sees
        colorTarget = arm.getColor();
        if (colorTarget == ColorTarget.Unknown) {
            System.out.println(
                    "spin three times command will not run, color unknown");
            cancel();
        }
        currentColor = colorTarget;
        // this will increase the counter every time the color sensor detects
        // the starting color
        System.out.println("initial color: " + colorTarget);
        System.out.println("starting execute() \n");
    }

    @Override
    public void execute() {
        ColorTarget prevColor = currentColor;
        currentColor = arm.getColor();
        if (prevColor != currentColor && currentColor == colorTarget) counter++;
        arm.setWheelSpinner(0.4);
        System.out.println(arm.getColor() + " counter: " + counter);
    }

    @Override
    public boolean isFinished() {
        // automatically stop the arm once the counter hits 7
        return counter == 7;
    }

    @Override
    public void end(boolean interrupted) {
        arm.setWheelSpinner(0.0);
    }
}
