// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class RunMotorSubsystem extends SubsystemBase {

  //The motor is declared here!
  private TalonFX motor;
  private static RunMotorSubsystem instance;


  private RunMotorSubsystem() {
    //TODO: Initalize the motor on port 0
    motor = new TalonFX(0);
  }
  // This is a singleton pattern. Ensures only one instance of `RunMotorSubsystem` exists!
  // See if you can understand how it works!
  public static RunMotorSubsystem getInstance(){
    if(instance == null){
      instance = new RunMotorSubsystem();
    }
    return instance;
  }

  public void runMotor(double speed) {
    //TODO: set motor speed and log speed
    SmartDashboard.putNumber("motor speed", speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
