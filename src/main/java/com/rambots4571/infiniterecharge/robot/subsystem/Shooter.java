package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.rambots4571.infiniterecharge.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private static Shooter instance;
    TalonFX shooterMotor;

    private Shooter() {
        shooterMotor = new TalonFX(Constants.Shooter.shooterMotor);
        shooterMotor.configFactoryDefault();
        shooterMotor.setNeutralMode(NeutralMode.Coast);

        TalonFX follower = new TalonFX(Constants.Shooter.follower);
        follower.configFactoryDefault();
        follower.follow(shooterMotor);
        follower.setNeutralMode(NeutralMode.Coast);
        follower.setInverted(InvertType.OpposeMaster);
    }

    public static synchronized Shooter getInstance() {
        if (instance == null) instance = new Shooter();
        return instance;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber(
                "shooter speed m/s", getSpeed(VelType.Tangential));
    }

    public double getSpeed(VelType type) {
        return type.getSpeed(getRawVel());
    }

    /**
     * @return the velocity in ticks/100 ms
     */
    public double getRawVel() {
        return shooterMotor.getSelectedSensorVelocity(0);
    }

    /**
     * ticks        1 rev        10 100ms       360 deg         pi
     * –––––––– * ––––––––––– * ––––––––––– * –––––––––––– * ––––––––––
     * 100 ms     4096 ticks       1 sec         1 rev        180 deg
     *
     * @return the angular velocity in rad/s
     */
    public double getAngularVel() {
        return getRawVel() / 4096.0 * 10.0 * 360.0 * (Math.PI / 180);
    }

    public void setSpeed(double value, VelType type) {
        shooterMotor.set(TalonFXControlMode.Velocity, type.convertToRaw(value));
    }

    public void setPower(double power) {
        shooterMotor.set(ControlMode.PercentOutput, power);
    }

    public enum VelType {
        Raw {
            @Override
            public double convertToRaw(double speedTicksPer100ms) {
                return speedTicksPer100ms;
            }

            @Override
            public double getSpeed(double ticksPer100ms) {
                return ticksPer100ms;
            }
        }, Angular {
            @Override
            public double convertToRaw(double speedRadPerSec) {
                return speedRadPerSec * (180.0 / Math.PI) / 360.0 / 10 * 4096.0;
            }

            /**
             * ticks        1 rev        10 100ms       360 deg         pi
             * –––––––– * ––––––––––– * ––––––––––– * –––––––––––– * ––––––––––
             * 100 ms     4096 ticks       1 sec         1 rev        180 deg
             *
             * @return the angular velocity in rad/s
             */
            @Override
            public double getSpeed(double ticksPer100ms) {
                return ticksPer100ms / 4096.0 * 10.0 * 360.0 * (Math.PI / 180);
            }
        }, Tangential {
            @Override
            public double convertToRaw(double speedMetersPerSec) {
                return Angular.convertToRaw(speedMetersPerSec /
                                            Units.inchesToMeters(
                                                    Constants.Shooter.wheelRadiusInches));
            }

            @Override
            public double getSpeed(double ticksPer100ms) {
                return Angular.getSpeed(ticksPer100ms) * Units.inchesToMeters(
                        Constants.Shooter.wheelRadiusInches);
            }
        };

        public abstract double convertToRaw(double speed);

        public abstract double getSpeed(double ticksPer100ms);
    }
}
