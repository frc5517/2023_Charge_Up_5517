// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PinchSubsystem extends SubsystemBase {
  /** Creates a new PinchSubsystem. */
  // Initialized the solenoid for the intake pinch
  private Solenoid pinchSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
  public PinchSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Intake pinch subsystem command
  public void pinchTrue() {
    pinchSolenoid.set(true);
  }
  public void pinchFalse() {
    pinchSolenoid.set(false);
  }

}
