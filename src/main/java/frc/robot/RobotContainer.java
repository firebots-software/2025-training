// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XRPDrivetrain m_xrpDrivetrain = new XRPDrivetrain();
  Supplier<Double> joystickValX;
  Supplier<Double> joystickValY;
  private final DriveCommand m_autoCommand;
  private Joystick driverPS4;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driverPS4 = new Joystick(0);
    //TODO: Hint! axis 1 is W/S and axis 0 is A/S
    // Configure the button bindings
    joystickValX = () -> driverPS4.getRawAxis(1);
    joystickValY = () -> driverPS4.getRawAxis(0);

    m_autoCommand= new DriveCommand(m_xrpDrivetrain, joystickValX, joystickValY);
    configureBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureBindings() {
    m_xrpDrivetrain.setDefaultCommand(m_autoCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
