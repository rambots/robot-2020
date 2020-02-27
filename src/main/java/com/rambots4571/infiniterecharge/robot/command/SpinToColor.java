package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.component.ColorTarget;
import com.rambots4571.infiniterecharge.robot.subsystem.Arm;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinToColor extends CommandBase {
    private Arm arm = Arm.getInstance();
    private ColorTarget target;

    public SpinToColor() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        // get the game specified message from the driver station.
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        // set the target color based on the message.
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
                    // if doesn't match the command will cancel.
                    cancel();
                    break;
            }
        } else {
            // if there is no message the command will cancel.
            DriverStation.reportWarning("Game data not set", false);
            cancel();
        }
    }

    @Override
    public void execute() {
        // the arm wheel will spin until isFinished() complete.
        arm.setWheelSpinner(0.5);
    }

    @Override
    public boolean isFinished() {
        return arm.getColor() == target.getComplement();
    }

    @Override
    public void end(boolean interrupted) {
        arm.stopWheelSpinner();
    }
}
