// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj.xrp.XRPRangefinder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRPDrivetrain extends SubsystemBase {

  // This is how we initalize motors for XRPs. Can you see how it is different from the `motors` activity?
  private final XRPMotor m_leftMotor = new XRPMotor(0);
  private final XRPMotor m_rightMotor = new XRPMotor(1);

  //This is used for one of the Challenge Problems!
  private final XRPRangefinder distanceSensor = new XRPRangefinder();

  /** Creates a new XRPDrivetrain. */
  public XRPDrivetrain() {
    m_leftMotor.setInverted(true);
  }

  public void arcadeDrive(double inputX,double inputY) {
    //TODO: The Goal is to use WASD to move the robot forward/backwards and turn left/right
    //Hint: You need inputs to move the motors!
    System.out.println(distanceSensor.getDistanceMeters());
    if(inputX != 0 && inputY != 0){
      double newX = (inputX /2);
      double newY = (inputY/2);
      m_leftMotor.set(newX-newY);
      m_rightMotor.set(newX + newY);
    }
    else if(inputX != 0){
      m_leftMotor.set(inputX);
      m_rightMotor.set(inputX);
    }
    else if(inputY != 0){
      m_leftMotor.set(-inputY);
      m_rightMotor.set(inputY);
    }

    if(distanceSensor.getDistanceMeters()<0.20) {
      m_leftMotor.set(1);
      m_rightMotor.set(1);
    }
    
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {
    //TODO: Log anything you think is useful
    //Use `SmartDashboard.putNumber(...);`
  }
}
