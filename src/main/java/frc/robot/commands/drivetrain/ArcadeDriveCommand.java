// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDriveCommand extends CommandBase {
  /** Creates a new ArcadeDriveCommand. */
  private DriveSubsystem m_driveSubsystem;
  private double m_fwd;
  private double m_rot;
  public ArcadeDriveCommand(DriveSubsystem driveSubsystem, double fwd, double rot) {
    // Use addRequirements() here to declare subsystem dependencies.
  m_driveSubsystem = driveSubsystem;
  m_fwd = fwd;
  m_rot = rot;

  addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveSubsystem.arcadeDrive(m_fwd, m_rot);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
