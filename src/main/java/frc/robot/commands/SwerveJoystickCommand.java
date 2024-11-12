// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsystem;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class SwerveJoystickCommand extends Command {

  private final SwerveSubsystem swerveSubsystem;
  private final Supplier<Double> xSupplier, ySupplier, turningSupplier, speedControl;

  private final SlewRateLimiter xLimiter;
  private final SlewRateLimiter yLimiter;
  private final SlewRateLimiter turnLimiter;

  public SwerveJoystickCommand(SwerveSubsystem swerveSubsystem, Supplier<Double> xSupplier, Supplier<Double> ySupplier, Supplier<Double> turningSupplier, Supplier<Double> speedControl) {
    this.swerveSubsystem = swerveSubsystem;
    this.xSupplier = xSupplier;
    this.ySupplier = ySupplier;
    this.turningSupplier = turningSupplier;
    this.speedControl = speedControl;

    xLimiter = new SlewRateLimiter(Constants.SwerveCostants.maxDriveAcceleration);
    yLimiter = new SlewRateLimiter(Constants.SwerveCostants.maxDriveAcceleration);
    turnLimiter = new SlewRateLimiter(Constants.SwerveCostants.maxAngularAcceleration);
    addRequirements(swerveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xSpeed = xSupplier.get();
    double ySpeed = ySupplier.get();
    double omega = turningSupplier.get();

    ChassisSpeeds cs = new ChassisSpeeds(xSpeed, ySpeed, omega);

    
    swerveSubsystem.setModuleStates(Constants.SwerveCostants.m_Kinematics.toSwerveModuleStates(cs)); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  
}