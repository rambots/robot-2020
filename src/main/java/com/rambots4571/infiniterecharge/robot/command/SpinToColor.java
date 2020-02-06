package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.rambots4571.infiniterecharge.robot.subsystem.Arm;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinToColor extends CommandBase {
    private Arm arm = Arm.getInstance();
    private ColorTarget target;
    private boolean canRun = false;

    public SpinToColor() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
                case 'B':
                    target = ColorTarget.Blue;
                    break;
                case 'G':
                    target = ColorTarget.Green;
                    break;
                case 'R':
                    target = ColorTarget.Red;
                    break;
                case 'Y':
                    target = ColorTarget.Yellow;
                    break;
                default:
                    target = ColorTarget.Unknown;
                    break;
            }
            if (target != ColorTarget.Unknown) canRun = true;
        } else {
            DriverStation.reportWarning("Game data not set", false);
        }
    }

    @Override
    public void execute() {
        arm.setWheelSpinner(1.0);
    }

    @Override
    public boolean isFinished() {
        return canRun == (arm.getColor() == target.getComplement());
    }

    @Override
    public void end(boolean interrupted) {
        arm.setWheelSpinner(0.0);
    }
}
