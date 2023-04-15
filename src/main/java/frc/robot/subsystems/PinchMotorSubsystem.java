// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PinchMotorSubsystem extends SubsystemBase {
  /** Creates a new PinchMotorSubsystem. */
  private final WPI_VictorSPX pM10 = new WPI_VictorSPX(10);
  private final WPI_VictorSPX pM11 = new WPI_VictorSPX(11);
  public PinchMotorSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void IntakeIn(){
    pM10.set(ControlMode.PercentOutput, 1);
    pM11.set(ControlMode.PercentOutput, -1);
  }

  public void IntakeOut() {
    pM10.set(ControlMode.PercentOutput, -1);
    pM11.set(ControlMode.PercentOutput, 1);
  }

  public void stopMotors() {
    pM10.stopMotor();
    pM11.stopMotor();
  }

}
