// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.RunMotorCommand; //You might need this import!
import frc.robot.subsystems.RunMotorSubsystem;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot will be declared. We will store all our subsystem instances here!
 */
public class RobotContainer {
  private final RunMotorSubsystem runMotorSubsystem = RunMotorSubsystem.getInstance();

  private final CommandXboxController m_driverController =
      new CommandXboxController(0);

  public RobotContainer() {
    
    configureBindings();
  }


  private void configureBindings() {
    //We will learn about Suppliers next time! Make sure to pass this as a parameter to the command!
    Supplier<Double> joystickInput = ()->m_driverController.getRawAxis(0);

    runMotorSubsystem.setDefaultCommand(getAutonomousCommand());
    //TODO: Instantiate RunMotorCommand and set default command here
    //WRITE CODE HERE!!

  }

  public Command getAutonomousCommand() {
    return new Command() {
      
    };
  }
}
