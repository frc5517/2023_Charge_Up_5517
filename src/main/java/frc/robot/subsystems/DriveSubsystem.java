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

  WPI_VictorSPX rM0 = new WPI_VictorSPX(DriveConstants.kRightVictorD0);
  WPI_VictorSPX rM1 = new WPI_VictorSPX(DriveConstants.kRightVictorID1);
  WPI_TalonSRX rT2 = new WPI_TalonSRX(DriveConstants.kRightTalonID2);
  MotorControllerGroup m_rightMotors = new MotorControllerGroup(rM0, rM1, rT2);

  WPI_VictorSPX lM3 = new WPI_VictorSPX(DriveConstants.kLeftVictorID3);
  WPI_VictorSPX lM4 = new WPI_VictorSPX(DriveConstants.kLeftVictorID3);
  WPI_TalonSRX lT5 = new WPI_TalonSRX(DriveConstants.kLeftTalonID5);
  MotorControllerGroup m_leftMotors = new MotorControllerGroup(lM3, lM4, lT5);

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

  public void TeleopPeriodic() {
    // This method will be called once per scheduler run
    double rampUpTime = 3;
    int timeoutMs = 15;
    rM0.configOpenloopRamp(rampUpTime, timeoutMs);
    rM1.configOpenloopRamp(rampUpTime, timeoutMs);
    rT2.configOpenloopRamp(rampUpTime, timeoutMs);
    lM3.configOpenloopRamp(rampUpTime, timeoutMs);
    lM4.configOpenloopRamp(rampUpTime, timeoutMs);
    lT5.configOpenloopRamp(rampUpTime, timeoutMs);


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
