// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.XRPDrivetrain;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class DriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final XRPDrivetrain xrp;
  private Supplier<Double> joystickValX;
  private Supplier<Double> joystickValY;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(XRPDrivetrain subsystem, Supplier<Double> speedX, Supplier<Double> speedY) {
    xrp = subsystem;
    //TODO: Write the constructor.
    //Hint: We need suppliers as input
    joystickValX=speedX;
    joystickValY=speedY;


    //We add subsystem requirements since we dont want multiple commands using the same subsystems to operate
    //We only have one command here, so don't worry about it!
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO: Use suppliers to move the robot through the subsystem
    //You first need to make a function in the `XRPDrivetrain.java` subsystem to move the robot
    xrp.arcadeDrive(joystickValX.get(), joystickValY.get());
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
