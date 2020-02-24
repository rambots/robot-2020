package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.rampage.joystick.DriveStick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RobotContainer {
    public static DriveStick leftStick = new DriveStick(
            Constants.Controller.leftStick);
    public static DriveStick rightStick = new DriveStick(
            Constants.Controller.rightStick);

    public RobotContainer() {
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}),
     * and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
//        leftStick.getButton4().toggle(
//                () -> Indexer.getInstance().setFeeder(0.75),
//                Indexer.getInstance()::stopFeeder);
//        leftStick.getButton6().toggle(
//                () -> Indexer.getInstance().setIntake(0.5),
//                Indexer.getInstance()::stopIntake);
//        leftStick.getButton3().toggle(
//                () -> Indexer.getInstance().setConveyor(0.5),
//                Indexer.getInstance()::stopConveyor);
        leftStick.getButton5().whenPressed(new CommandBase() {
            private Timer timer = new Timer();

            @Override
            public void initialize() {
                Music.getInstance().prev();
                timer.start();
            }

            @Override
            public boolean isFinished() {
                return timer.get() > 1;
            }

            @Override
            public void end(boolean interrupted) {
                timer.stop();
                timer.reset();
            }
        }.andThen(Music.getInstance()::play));

        leftStick.getButton6().whenPressed(new CommandBase() {
            private Timer timer = new Timer();

            @Override
            public void initialize() {
                Music.getInstance().skip();
                timer.start();
            }

            @Override
            public boolean isFinished() {
                return timer.get() > 1;
            }

            @Override
            public void end(boolean interrupted) {
                timer.stop();
                timer.reset();
            }
        }.andThen(Music.getInstance()::play));
        leftStick.getButton1().whenPressed(Music.getInstance()::toggle);
    }

    public void initTeleopCommands() {
//        (new RunCommand(() -> Shooter.getInstance().setPower(
//                RobotContainer.leftStick.getRawAxis(3)), Shooter.getInstance()))
//                .schedule();
    }
}
