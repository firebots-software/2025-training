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

  }

  public void arcadeDrive() {
    //TODO: The Goal is to use WASD to move the robot forward/backwards and turn left/right
    //Hint: You need inputs to move the motors!
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
