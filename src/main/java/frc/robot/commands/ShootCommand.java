// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ShootCommand extends Command {

  private static ShooterSubsystem shooterSubsystem;
  private Supplier<Double> speedSupplier;

  public ShootCommand(ShooterSubsystem shooterSub, Supplier<Double> speedSup) {
    //What inputs do you need?
    shooterSubsystem = shooterSub;
    speedSupplier = speedSup;
    addRequirements();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Run the shooters
    shooterSubsystem.shoot(speedSupplier.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      //What should you do on end?
      shooterSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
