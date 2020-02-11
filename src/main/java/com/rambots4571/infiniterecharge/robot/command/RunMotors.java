package com.rambots4571.infiniterecharge.robot.command;

import com.rambots4571.infiniterecharge.robot.subsystem.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunMotors extends CommandBase {
    private boolean isFinished = false;
    private Timer timer = new Timer();

    public RunMotors() {
        addRequirements(Drivetrain.getInstance());
    }

    @Override
    public void initialize() {
        timer.start();
    }

    @Override
    public void execute() {
        double time = timer.get();
        if (time < 5 * 60) { // 5 mins
            Drivetrain.getInstance().setSpeed(6. / 12, 0);
        } else if (time > 5 * 60 && time < 10 * 60) { // 5 < time < 10 mins
            Drivetrain.getInstance().setSpeed(10. / 12, 0);
        } else if (time > 10 * 60 && time < 15 * 60) { // 10 < time < 15 mins
            Drivetrain.getInstance().setSpeed(1, 0);
        } else if (time > 15 * 60) { // 15 < time
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void end(boolean interrupted) {
        Drivetrain.getInstance().stop();
        timer.stop();
    }
}
