// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSubsystem extends SubsystemBase {
    TalonFX leftMotor;
    TalonFX rightMotor;
  
  private static ShooterSubsystem instance;


  private ShooterSubsystem() {
    leftMotor = new TalonFX(34);
    rightMotor = new TalonFX(35);
    rightMotor.setInverted(true);

  }
  // This is a singleton pattern. Ensures only one instance of `RunMotorSubsystem` exists!
  // See if you can understand how it works!
  public static ShooterSubsystem getInstance(){
    if(instance == null){
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  public void shoot(double speeeeeeed){
    //Note that you have 2 shooter motors!
    leftMotor.set(speeeeeeed);
    rightMotor.set(speeeeeeed);
  }

  public void stop(){
    //Note that you have 2 shooter motors!
    leftMotor.set(0);
    rightMotor.set(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Shooter Speed", leftMotor.get());
    SmartDashboard.putNumber("Right Shooter Speed", rightMotor.get());
  }

  @Override
  public void simulationPeriodic() {
    SmartDashboard.putNumber("Left Shooter Speed", leftMotor.get());
    SmartDashboard.putNumber("Right Shooter Speed", rightMotor.get());

  }
}
