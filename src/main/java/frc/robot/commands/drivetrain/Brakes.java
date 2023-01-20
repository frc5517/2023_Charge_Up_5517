// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Brakes extends CommandBase {
  /** Creates a new Brakes. */
  public Brakes() {
    // Use addRequirements() here to declare subsystem dependencies.
    DriveTrain.rightMotorID0.setNeutralMode(NeutralMode.Brake);
    DriveTrain.rightMotorID1.setNeutralMode(NeutralMode.Brake);
    DriveTrain.rightTalonID2.setNeutralMode(NeutralMode.Brake);
    DriveTrain.leftMotorID3.setNeutralMode(NeutralMode.Brake);
    DriveTrain.leftMotorID4.setNeutralMode(NeutralMode.Brake);
    DriveTrain.leftTalonID5.setNeutralMode(NeutralMode.Brake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveTrain.rightMotorID0.setNeutralMode(NeutralMode.Coast);
    DriveTrain.rightMotorID1.setNeutralMode(NeutralMode.Coast);
    DriveTrain.rightTalonID2.setNeutralMode(NeutralMode.Coast);
    DriveTrain.leftMotorID3.setNeutralMode(NeutralMode.Coast);
    DriveTrain.leftMotorID4.setNeutralMode(NeutralMode.Coast);
    DriveTrain.leftTalonID5.setNeutralMode(NeutralMode.Coast);
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
