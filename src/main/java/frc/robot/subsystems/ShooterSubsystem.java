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
 
  private static ShooterSubsystem instance;
  private static TalonFX shooter1;
  private static TalonFX shooter2;




  private ShooterSubsystem() {
    //TODO: Initalize the shooting moto
    shooter1 = new TalonFX(34);
    shooter1 = new TalonFX(35);
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
    shooter1.set(speed);
    shooter2.set(speed);
    }


  public void stop(){
    shooter1.set(0);
    shooter2.set(0);
  }


  @Override
  public void periodic() {
    
  }


  @Override
  public void simulationPeriodic() {
    //TODO: log motor speeds (same as above)
    SmartDashboard.putNumber("Shooter1 speed:", shooter1.get());
    SmartDashboard.putNumber("Shooter2 speed:", shooter2.get());
    DogLog.log("Shooter1", shooter1.get());
     DogLog.log("Shooter2", shooter2.get());
  }
}



