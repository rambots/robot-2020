package com.rambots4571.infiniterecharge.robot;

import com.rambots4571.infiniterecharge.robot.subsystem.Drivetrain;
import com.rambots4571.rampage.joystick.DriveStick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;

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
     * and then
     * passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {}

    public void initTeleopCommands() {
        (new RunCommand(() -> {
            Drivetrain.getInstance().setSpeed(
                    leftStick.getYAxis(), rightStick.getYAxis());
        }, Drivetrain.getInstance())).schedule();
    }
}
