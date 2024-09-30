// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSubsystem extends SubsystemBase {

  //The motors should be declared here!
  private final TalonFX m_shooterMotor1;
  private final TalonFX m_shooterMotor2;
  private static ShooterSubsystem instance;
  private double motorSpeed;


  private ShooterSubsystem() {
    //Initalize the shooting motors to ports 34, 35
    m_shooterMotor1 = new TalonFX(34);
    m_shooterMotor2 = new TalonFX(35);
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
    //set motor speed (what inputs do you need if any?)
    //Note that you have 2 shooter motors!
    this.motorSpeed = speed;
    m_shooterMotor1.set(speed);
    m_shooterMotor2.set(-speed);
  }

  public void stop(){
    //stop motors (what inputs do you need if any?)
    //Note that you have 2 shooter motors!
    m_shooterMotor1.set(0);
    m_shooterMotor2.set(0);
  }

  @Override
  public void periodic() {
    //log motor speeds (same as below)
    SmartDashboard.putNumber("ShooterSpeed", motorSpeed);
  }

  @Override
  public void simulationPeriodic() {
    //log motor speeds (same as above)
    SmartDashboard.putNumber("ShooterSpeed", motorSpeed);
  }
}
