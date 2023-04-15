// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExtenderSubsystem;

public class ExtendArmCommand extends CommandBase {
  /** Creates a new ExtendArmCommand. */
  private ExtenderSubsystem m_extenderSubsystem;
  public ExtendArmCommand(ExtenderSubsystem extenderSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_extenderSubsystem = extenderSubsystem;

    addRequirements(m_extenderSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_extenderSubsystem.extendArm();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_extenderSubsystem.stopExtender();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
