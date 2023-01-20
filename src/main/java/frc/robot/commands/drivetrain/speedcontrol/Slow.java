// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain.speedcontrol;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Slow extends CommandBase {
  /** Creates a new Slow. */
  public Slow() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_drivetrain.slow();
    
    double forwardSpeed = RobotContainer.xboxController.getRawAxis(1);
    double rotationSpeed = RobotContainer.xboxController.getRawAxis(4);

    RobotContainer.m_drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
    
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
