// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */

  private final MotorControllerGroup m_leftMotors = 
  new MotorControllerGroup(
    new WPI_VictorSPX(DriveConstants.kLeftVictorID3),
    new WPI_VictorSPX(DriveConstants.kLeftVictorID4),
    new WPI_TalonSRX(DriveConstants.kLeftTalonID5));

  private final MotorControllerGroup m_rightMotors =
  new MotorControllerGroup(
    new WPI_VictorSPX(DriveConstants.kRightVictorD0),
    new WPI_VictorSPX(DriveConstants.kRightVictorID1),
    new WPI_TalonSRX(DriveConstants.kRightTalonID2));

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  
  public DriveSubsystem() {
    m_leftMotors.setInverted(true);
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void stop() {
    m_drive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_drive.arcadeDrive(RobotContainer.drController().getLeftY(), RobotContainer.drController().getRightX());

    if (RobotContainer.drController().leftBumper().getAsBoolean()) {
      m_drive.setMaxOutput(.3);
    }
    else if (RobotContainer.drController().rightBumper().getAsBoolean()) {
      m_drive.setMaxOutput(1);
    }
    else {
      m_drive.setMaxOutput(.7);
    }

  }
}
