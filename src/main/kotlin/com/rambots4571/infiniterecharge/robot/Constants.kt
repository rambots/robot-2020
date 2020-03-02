package com.rambots4571.infiniterecharge.robot

object Constants {
    object Controller {
        const val leftStick = 0
        const val rightStick = 1
        const val gamepad = 2
    }

    object Indexer {
        const val intakeMotor = 4
        const val feederMotor = 3
        const val conveyorMotor = 1

        const val intakeSensor = 0
        const val conveyorSensor = 1
        const val topSensor = 2
    }

    object Shooter {
        const val shooterMotor = 5
        const val follower = 6

        const val wheelRadiusInches = 4.0
    }
}