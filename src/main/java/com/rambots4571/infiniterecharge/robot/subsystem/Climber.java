package com.rambots4571.infiniterecharge.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.rambots4571.infiniterecharge.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
 private static Climber instance;
 private static VictorSPX ClimberMotor;
 private static VictorSPX HookMotor;

 private Climber (){
  ClimberMotor = new VictorSPX(Constants.Climber.ClimberMotor);

  HookMotor = new VictorSPX(Constants.Climber.HookMotor);

 }







}




