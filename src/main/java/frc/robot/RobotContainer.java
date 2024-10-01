// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ShootCommand; //You might need this import!
import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.Supplier;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot will be declared. We will store all our subsystem instances here!
 */
public class RobotContainer {
  private final ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();

  private final CommandXboxController controller =
      new CommandXboxController(0);

  public RobotContainer() {
    
    configureBindings();
  }


  private void configureBindings() {
    //We will learn about Suppliers next time! Make sure to pass this as a parameter to the command!
    //TODO: Instantiate the Command here and set speed based on controller!
    //WRITE CODE HERE!!
    DogLog.log("daniel-shoot-hw/controller", controller.getRawAxis(0));
    ShootCommand shootCmd = new ShootCommand(shooterSubsystem, () -> controller.getRawAxis(0));
  }

  public Command getAutonomousCommand() {
    //IGNORE AUTONOMOUS COMMAND (NOT NEEDED FOR THIS PROJECT)
    return new Command() {
      
    };
  }
}
