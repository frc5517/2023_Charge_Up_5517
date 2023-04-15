// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.AutoBalance;
import frc.robot.subsystems.DriveSubsystem;

public class AutoBalanceCommand extends CommandBase {
  /** Creates a new AutoBalanceCommand. */
  private AutoBalance m_autoBalance;
  private DriveSubsystem m_driveSubsystem;
  private double m_speedMultiplier;
  public AutoBalanceCommand(DriveSubsystem driveSubsystem, AutoBalance autoBalance, double speedMultiplier) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_autoBalance = autoBalance;
    m_driveSubsystem = driveSubsystem;
    m_speedMultiplier = speedMultiplier;

    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = m_autoBalance.autoBalanceRoutine() * m_speedMultiplier;
    m_driveSubsystem.arcadeDrive(speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
