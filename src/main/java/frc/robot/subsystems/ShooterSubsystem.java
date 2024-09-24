// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSubsystem extends SubsystemBase {

  //TODO: The motors should be declared here!
  
  private static ShooterSubsystem instance;
  private static TalonFX shooterM1;
  private static TalonFX shooterM2;
  private double speed;


  private ShooterSubsystem() {
    //TODO: Initalize the shooting motors to ports 34, 35
    shooterM1 = new TalonFX(34);
    shooterM2 = new TalonFX(35);
  }
  // This is a singleton pattern. Ensures only one instance of `RunMotorSubsystem` exists!
  // See if you can understand how it works!
  public static ShooterSubsystem getInstance(){
    if(instance == null){
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  public void shoot(double s){
    //TODO: set motor speed (what inputs do you need if any?)
    //Note that you have 2 shooter motors!
    this.speed = s;
    shooterM1.set(this.speed);
    shooterM2.set(this.speed);
  }

  public void stop(){
    //TODO: stop motors (what inputs do you need if any?)
    //Note that you have 2 shooter motors!
    shooterM1.set(0);
    shooterM2.set(0);
  }

  @Override
  public void periodic() {
    //TODO: log motor speeds (same as below)
    SmartDashboard.putNumber("Shooter speed", this.speed);
  }

  @Override
  public void simulationPeriodic() {
    //TODO: log motor speeds (same as above)
    SmartDashboard.putNumber("Shooter speed", this.speed);
  }
}
