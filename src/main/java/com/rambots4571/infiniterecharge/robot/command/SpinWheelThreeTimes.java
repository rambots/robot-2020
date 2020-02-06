package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.rambots4571.infiniterecharge.robot.subsystem.Arm;
import com.rambots4571.rampage.function.SwitchAction;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinWheelThreeTimes extends CommandBase {
    private Arm arm = Arm.getInstance();
    private ColorTarget colorTarget;
    private int counter;

    public SpinWheelThreeTimes() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        // storing the color the arm first sees
        colorTarget = arm.getColor();
        // this will increase the counter every time the color sensor detects
        // the starting color
        System.out.println("initial color: " + colorTarget);
        (new SwitchAction<>(arm::getColor)).whenActive(() -> {
            if (arm.getColor() == colorTarget) counter++;
            System.out.println("color: " + arm.getColor());
        });
    }

    @Override
    public void execute() {
        arm.setWheelSpinner(1.0);
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
