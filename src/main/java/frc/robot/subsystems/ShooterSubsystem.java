// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import dev.doglog.DogLog;


public class ShooterSubsystem extends SubsystemBase {

  //TODO: The motors should be declared here!
  private TalonFX m_leftShooterMotor;
  private TalonFX m_rightShooterMotor;
  
  private static ShooterSubsystem instance;


  private ShooterSubsystem() {
    //TODO: Initalize the shooting motors to ports 34, 35
    m_leftShooterMotor = new TalonFX(34);
    m_rightShooterMotor = new TalonFX(35);

  }

  
  // This is a singleton pattern. Ensures only one instance of `RunMotorSubsystem` exists!
  // See if you can understand how it works!
  public static ShooterSubsystem getInstance(){
    if(instance == null){
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  public void shoot(double speed){
    //TODO: set motor speed (what inputs do you need if any?)
    //Note that you have 2 shooter motors!
    m_leftShooterMotor.set(speed);
    m_rightShooterMotor.set(speed);
  }

  public void stop(){
    //TODO: stop motors (what inputs do you need if any?)
    //Note that you have 2 shooter motors!
    m_leftShooterMotor.stopMotor();
    m_rightShooterMotor.stopMotor();
  }

  @Override
  public void periodic() {
    //TODO: log motor speeds (same as below)
    SmartDashboard.putNumber("Left Shooter Motor Speed", m_leftShooterMotor.get());
    SmartDashboard.putNumber("Right Shooter Motor Speed", m_rightShooterMotor.get());
    DogLog.log("Shooter/Left Shooter Motor Speed", m_leftShooterMotor.get());
    DogLog.log("Shooter/Right Shooter Motor Speed", m_rightShooterMotor.get());
  }

  @Override
  public void simulationPeriodic() {
    //TODO: log motor speeds (same as above)
    SmartDashboard.putNumber("Left Shooter Motor Speed", m_leftShooterMotor.get());
    SmartDashboard.putNumber("Right Shooter Motor Speed", m_rightShooterMotor.get());
    DogLog.log("Shooter/Left Shooter Motor Speed", m_leftShooterMotor.get());
    DogLog.log("Shooter/Right Shooter Motor Speed", m_rightShooterMotor.get());
  }
}
