// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.ManipulatorConstants;

public class ExtenderSubsystem extends SubsystemBase {
  /** Creates a new ExtenderSubsystem. */
  private CANSparkMax extenderSpark = new CANSparkMax(ManipulatorConstants.extenderSpark, MotorType.kBrushless);

  private DigitalInput limitSwitch =  new DigitalInput(0);

  String softLimitDisplay;

  RelativeEncoder extenderEncoder;
  private static SparkMaxPIDController extenderPID;

  public ExtenderSubsystem() {
    extenderSpark.setIdleMode(IdleMode.kBrake);
    extenderPID = extenderSpark.getPIDController();
    extenderEncoder = extenderSpark.getEncoder();
    extenderPID.setFeedbackDevice(extenderEncoder);
    // Intake extender PID loop initialization
    extenderPID.setP(0);
    extenderPID.setI(0);
    extenderPID.setD(0);
    extenderPID.setIZone(0);
    extenderPID.setFF(0);
    extenderPID.setOutputRange(-1, 1);

    midSoft();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Extender Position", extenderEncoder.getPosition());
    SmartDashboard.putData(limitSwitch);
    SmartDashboard.putString("Soft Limit", softLimitDisplay);

    if (RobotContainer.opController().a().getAsBoolean()) {
      retractArm();
    }
    else if (RobotContainer.opController().y().getAsBoolean()) {
      extendArm();
    }
    else {
      extenderSpark.stopMotor();
    }

  }

  // Intake extender extension subsystem command
  public void retractArm() {
    
      System.out.println("Limit switch" + limitSwitch.get() + ", Encoder" + extenderEncoder.getPosition());
      if (limitSwitch.get() == true) {
        System.out.println("LIMIT SWITCH HIT! Stopping arm motor");
        extenderSpark.stopMotor();
        extenderEncoder.setPosition(0);
      }
      else 
      {
        System.out.println("Starting arm motor");
        extenderSpark.set(1);
      } 
      // extenderSpark.set(1);
  }


  // Intake extender retract subsystem command
  public void extendArm() {
    extenderSpark.set(-1);
  }

  // Intake extender stop subsystem command
  public void stopExtender() {
    extenderSpark.stopMotor();
  }

  public void resetEncoder() {
    extenderEncoder.setPosition(0);
  }

  public void highSoft() {
    System.out.println("High goal soft limit enabled.");
    extenderSpark.setSoftLimit(SoftLimitDirection.kReverse, -400);
    extenderSpark.enableSoftLimit(SoftLimitDirection.kReverse, true);
    softLimitDisplay = "High Goal Soft Limit";
  }

  public void midSoft() {
    System.out.println("Mid goal soft limit enabled.");
    extenderSpark.setSoftLimit(SoftLimitDirection.kReverse, -325);
    extenderSpark.enableSoftLimit(SoftLimitDirection.kReverse, true);
    softLimitDisplay = "Mid Goal Soft Limit";
  }

}
