// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSubsystem extends SubsystemBase {

  //TODO: The motors should be declared here!
  
  private static ShooterSubsystem instance;


  private ShooterSubsystem() {
    //TODO: Initalize the shooting motors to ports 34, 35

  }
  // This is a singleton pattern. Ensures only one instance of `RunMotorSubsystem` exists!
  // See if you can understand how it works!
  public static ShooterSubsystem getInstance(){
    if(instance == null){
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  public void shoot(){
    //TODO: set motor speed (what inputs do you need if any?)
    //Note that you have 2 shooter motors!

  }

  public void stop(){
    //TODO: stop motors (what inputs do you need if any?)
    //Note that you have 2 shooter motors!
    
  }

  @Override
  public void periodic() {
    //TODO: log motor speeds (same as below)
  }

  @Override
  public void simulationPeriodic() {
    //TODO: log motor speeds (same as above)

  }
}
