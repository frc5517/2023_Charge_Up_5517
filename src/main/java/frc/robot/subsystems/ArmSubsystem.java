// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ManipulatorConstants;

public class ArmSubsystem extends SubsystemBase {
  
  // Initializes the Spark Max controllers for the NEOs 
  private static CANSparkMax liftSpark = new CANSparkMax(ManipulatorConstants.liftSpark, MotorType.kBrushless);
  
  // Initializing the encoders for the sparks
  RelativeEncoder liftEncoder;
  

  // Initializing the PID controllers for the Sparks
  private static SparkMaxPIDController liftPID;
  

  public ArmSubsystem() {
    // Setting the Idle mode to brake so the arm and extender don't move when not wanted
    liftSpark.setIdleMode(IdleMode.kBrake);
    

    // Getting the PID controllers for the Sparks
    liftPID = liftSpark.getPIDController();
    

    // Getting the encoders for the sparks
    liftEncoder = liftSpark.getEncoder();
    

    // Setting the encoders as a feedback device for the PID Loop
    liftPID.setFeedbackDevice(liftEncoder);
    

    // Intake lift PID loop initialization
    liftPID.setP(0);
    liftPID.setI(0);
    liftPID.setD(0);
    liftPID.setIZone(0);
    liftPID.setFF(0);
    liftPID.setOutputRange(-1, 1);

    
  }

  public void robotPeriodic() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Puts encoder data onto the dashboard
    SmartDashboard.putNumber("Lift Position", liftEncoder.getPosition());
    
  }

  public void resetEncoders() {
    liftSpark.restoreFactoryDefaults();
  }

  // Intake lift raise subsystem command
  public void raiseArm(double power) {
    liftSpark.set(power);
  }

  // Intake lift lower subsystem command
  public void lowerArm(double power) {
    liftSpark.set(-power);
  }

  // Intake lift stop subsystem command
  public void stopLift() {
    liftSpark.stopMotor();
  }

  
}
