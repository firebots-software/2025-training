// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.hardware.TalonFX;

import dev.doglog.DogLog;

import com.ctre.phoenix6.controls.VelocityVoltage;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Piper extends SubsystemBase {
  private TalonFX shooter1, shooter2;
  private TalonFX preshooterMotor; 
  private TalonFX intakeMotor; 
  private DigitalInput noteSwitch;
  private static Piper piperInstance;
  
  public Piper() {
    // Shooter
    shooter1 = new TalonFX(35);
    shooter2 = new TalonFX(34);
    shooter1.setInverted(true);
    shooter2.setInverted(false);

    Slot0Configs s0c = new Slot0Configs().withKP(0).withKI(0).withKD(0).withKG(0).withKV(0).withKA(0);
    CurrentLimitsConfigs clc =
        new CurrentLimitsConfigs()
            .withStatorCurrentLimitEnable(true)
            .withStatorCurrentLimit(20);

    shooter1.getConfigurator().apply(s0c);
    shooter2.getConfigurator().apply(s0c);
    shooter1.getConfigurator().apply(clc);
    shooter2.getConfigurator().apply(clc);

    // PreShooter
    preshooterMotor = new TalonFX(32); 

    preshooterMotor.getConfigurator().apply(clc);
    

    intakeMotor = new TalonFX(33);

    intakeMotor.getConfigurator().apply(clc);

    //note detector

    noteSwitch = new DigitalInput(1);

  }
  public boolean isNotesThere() {
    return noteSwitch.get();
  }
  
  //Shooter Functions
  public void spinShooter(double speed) {

    VelocityVoltage VV = new VelocityVoltage(speed * 1);
;
    shooter1.setControl(VV);
    shooter2.setControl(VV);
  }

  public void stopShooter() {
    VelocityVoltage VV = new VelocityVoltage(0);
    shooter1.setControl(VV);
    shooter2.setControl(VV);
  }

  // PreShooter Functions
  public void spinPreShooter(double speed) {

    VelocityVoltage VV = new VelocityVoltage(speed * 1);
;
    preshooterMotor.setControl(VV);
  }

  public void stopPreShooter() {
    preshooterMotor.stopMotor();
  }

  //Intake Functions
  public void spinIntake(double speed) {
    VelocityVoltage VV = new VelocityVoltage(speed);
;
    intakeMotor.setControl(VV);
  }

  public void stopIntake() {
    intakeMotor.stopMotor();
  }


  
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    DogLog.log();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public static Piper getInstance() {
    if(piperInstance == null){
      piperInstance = new Piper();
    }

    return piperInstance;
  }
}
