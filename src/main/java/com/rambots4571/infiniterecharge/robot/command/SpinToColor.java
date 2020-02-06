package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.rambots4571.infiniterecharge.robot.subsystem.Arm;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinToColor extends CommandBase {
    private Arm arm = Arm.getInstance();
    private ColorTarget color;

    public SpinToColor() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
                case 'B':
                    color = ColorTarget.Blue;
                    break;
                case 'G':
                    color = ColorTarget.Green;
                    break;
                case 'R':
                    color = ColorTarget.Red;
                    break;
                case 'Y':
                    color = ColorTarget.Yellow;
                    break;
                default:
                    color = ColorTarget.Unknown;
                    break;
            }
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
        return arm.getColor() == color.getComplement();
    }
}
