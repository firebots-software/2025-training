// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.SwerveJoystickCommand;
import frc.robot.subsystems.SwerveSubsystem;

/**
 * This class is where the bulk of the robot will be declared. We will store all our subsystem instances here!
 */
public class RobotContainer {
  private final SwerveSubsystem swerve = SwerveSubsystem.getInstance();
  private Supplier<Double> xSpeed, ySpeed, turningSpeed, r2Speed;

  private final CommandXboxController controller =
      new CommandXboxController(0);

  public RobotContainer() {
    xSpeed = () -> controller.getRawAxis(0);
    ySpeed = () -> -controller.getRawAxis(1);
    turningSpeed = () -> controller.getRawAxis(2);
    r2Speed = () -> controller.getRightTriggerAxis();

    configureBindings();
  }


  private void configureBindings() {
    //We will learn about Suppliers next time! Make sure to pass this as a parameter to the command!
    //TODO: Instantiate the Command here and set speed based on controller!
    SwerveJoystickCommand swerveJoystickCommand = new SwerveJoystickCommand(swerve, ySpeed, xSpeed, turningSpeed, r2Speed);

    swerve.setDefaultCommand(swerveJoystickCommand);
  }

  public Command getAutonomousCommand() {
    //IGNORE AUTONOMOUS COMMAND (NOT NEEDED FOR THIS PROJECT)
    return new Command() {
      
    };
  }
}
