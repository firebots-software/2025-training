// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Piper;
import dev.doglog.DogLog;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Shooter extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Piper m_piper;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Shooter(Piper piper) {
    m_piper = piper;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(piper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = 50;
    m_piper.spinShooter(speed);
    m_piper.spinPreShooter(speed/2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_piper.stopShooter();
    m_piper.stopPreShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  }
}
